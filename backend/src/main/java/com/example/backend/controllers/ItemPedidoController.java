package com.example.backend.controllers;

import com.example.backend.models.ItemPedido;
import com.example.backend.repository.ItemPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/itens-pedido")
public class ItemPedidoController {

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    // GET /api/itens-pedido - listar todos os itens
    @GetMapping
    public List<ItemPedido> getAllItensPedido() {
        return itemPedidoRepository.findAll();
    }

    // GET /api/itens-pedido/{id} - buscar item por ID
    @GetMapping("/{id}")
    public ResponseEntity<ItemPedido> getItemPedidoById(@PathVariable Long id) {
        Optional<ItemPedido> item = itemPedidoRepository.findById(id);
        return item.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/itens-pedido - criar novo item
    @PostMapping
    public ItemPedido createItemPedido(@RequestBody ItemPedido itemPedido) {
        return itemPedidoRepository.save(itemPedido);
    }

    // PUT /api/itens-pedido/{id} - atualizar item
    @PutMapping("/{id}")
    public ResponseEntity<ItemPedido> updateItemPedido(@PathVariable Long id, @RequestBody ItemPedido itemDetails) {
        Optional<ItemPedido> optionalItem = itemPedidoRepository.findById(id);

        if (optionalItem.isPresent()) {
            ItemPedido item = optionalItem.get();
            item.setPedido(itemDetails.getPedido());
            item.setVinil(itemDetails.getVinil());
            item.setQuantidade(itemDetails.getQuantidade());
            item.setPrecoUnitario(itemDetails.getPrecoUnitario());

            return ResponseEntity.ok(itemPedidoRepository.save(item));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE /api/itens-pedido/{id} - deletar item
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItemPedido(@PathVariable Long id) {
        if (itemPedidoRepository.existsById(id)) {
            itemPedidoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}