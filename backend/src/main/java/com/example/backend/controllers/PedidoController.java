package com.example.backend.controllers;

import com.example.backend.dto.ItemPedidoDTO;
import com.example.backend.dto.PedidoDTO;
import com.example.backend.models.Cliente;
import com.example.backend.models.ItemPedido;
import com.example.backend.models.Pedido;
import com.example.backend.models.Vinil;
import com.example.backend.repository.ClienteRepository;
import com.example.backend.repository.PedidoRepository;
import com.example.backend.repository.VinilRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private VinilRepository vinilRepository;

    @GetMapping
    public List<Pedido> getAllPedidos() {
        return pedidoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> getPedidoById(@PathVariable Long id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        return pedido.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createPedido(@RequestBody PedidoDTO pedidoDTO) {
        try {
            Cliente cliente = clienteRepository.findById(pedidoDTO.getClienteId())
                    .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

            Pedido pedido = new Pedido();
            pedido.setCliente(cliente);
            pedido.setDataCriacao(LocalDateTime.now());
            pedido.setPrevisaoEntrega(LocalDateTime.now().toLocalDate().plusDays(7));

            List<ItemPedido> itens = new ArrayList<>();
            BigDecimal total = BigDecimal.ZERO;

            for (ItemPedidoDTO itemDTO : pedidoDTO.getItens()) {
                Vinil vinil = vinilRepository.findById(itemDTO.getProdutoId())
                        .orElseThrow(() -> new RuntimeException("Produto não encontrado: " + itemDTO.getProdutoId()));

                ItemPedido item = new ItemPedido();
                item.setVinil(vinil);
                item.setQuantidade(itemDTO.getQuantidade());
                item.setPrecoUnitario(itemDTO.getPrecoUnitario());
                item.setPedido(pedido);

                itens.add(item);
                total = total.add(itemDTO.getPrecoUnitario()
                        .multiply(BigDecimal.valueOf(itemDTO.getQuantidade())));
            }

            pedido.setItens(itens);
            pedido.setTotal(total);

            Pedido pedidoSalvo = pedidoRepository.save(pedido);
            
            pedidoSalvo.getCliente().getId(); // Garante que o cliente está carregado
            pedidoSalvo.getItens().forEach(item -> {
                item.getVinil().getId(); // Garante que os vinis estão carregados
            });
            
            return ResponseEntity.ok(pedidoSalvo);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao criar pedido: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> updatePedido(@PathVariable Long id, @RequestBody Pedido pedidoDetails) {
        Optional<Pedido> optionalPedido = pedidoRepository.findById(id);

        if (optionalPedido.isPresent()) {
            Pedido pedido = optionalPedido.get();
            pedido.setCliente(pedidoDetails.getCliente());
            pedido.setDataCriacao(pedidoDetails.getDataCriacao());

            // Atualiza itens: substitui a lista toda
            pedido.getItens().clear();
            pedidoDetails.getItens().forEach(item -> item.setPedido(pedido));
            pedido.getItens().addAll(pedidoDetails.getItens());

            // Atualiza total
            BigDecimal total = pedido.getItens().stream()
                    .map(item -> item.getPrecoUnitario().multiply(BigDecimal.valueOf(item.getQuantidade())))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            pedido.setTotal(total);

            Pedido pedidoAtualizado = pedidoRepository.save(pedido);
            
            // Carrega explicitamente os relacionamentos necessários
            pedidoAtualizado.getCliente().getId();
            pedidoAtualizado.getItens().forEach(item -> item.getVinil().getId());
            
            return ResponseEntity.ok(pedidoAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePedido(@PathVariable Long id) {
        if (pedidoRepository.existsById(id)) {
            pedidoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}