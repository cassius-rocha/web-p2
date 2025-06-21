package com.example.backend.controllers;

import com.example.backend.models.Pedido;
import com.example.backend.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    // GET /api/pedidos - listar todos
    @GetMapping
    public List<Pedido> getAllPedidos() {
        return pedidoRepository.findAll();
    }

    // GET /api/pedidos/{id} - buscar por id
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> getPedidoById(@PathVariable Long id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        return pedido.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/pedidos - criar pedido com itens
    @PostMapping
    public Pedido createPedido(@RequestBody Pedido pedido) {
        // Define data de criação se não fornecida
        if (pedido.getDataCriacao() == null) {
            pedido.setDataCriacao(LocalDateTime.now());
        }

        if (pedido.getPrevisaoEntrega() == null) {
            pedido.setPrevisaoEntrega(pedido.getDataCriacao().toLocalDate().plusDays(7));
        }

        // Calcula total se não fornecido
        if (pedido.getTotal() == null) {
            BigDecimal total = pedido.getItens().stream()
                .map(item -> item.getPrecoUnitario().multiply(BigDecimal.valueOf(item.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
            pedido.setTotal(total);
        }

        // Garantir que cada item tenha referência para o pedido (essencial para cascade funcionar)
        pedido.getItens().forEach(item -> item.setPedido(pedido));

        return pedidoRepository.save(pedido);
    }

    // PUT /api/pedidos/{id} - atualizar pedido e seus itens
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

            return ResponseEntity.ok(pedidoRepository.save(pedido));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE /api/pedidos/{id} - deletar pedido
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