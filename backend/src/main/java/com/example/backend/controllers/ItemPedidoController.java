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

    @GetMapping
    public List<ItemPedido> getAllItensPedido() {
        return itemPedidoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemPedido> getItemPedidoById(@PathVariable Long id) {
        Optional<ItemPedido> item = itemPedidoRepository.findById(id);
        return item.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ItemPedido createItemPedido(@RequestBody ItemPedido itemPedido) {
        return itemPedidoRepository.save(itemPedido);
    }

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