package com.example.backend.models;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @ManyToOne(optional = false)
    @JoinColumn(name = "vinil_id")
    private Vinil vinil;

    private Integer quantidade;

    @Column(precision = 10, scale = 2)
    private BigDecimal precoUnitario;

    public ItemPedido() {
    }

    public ItemPedido(Long id, Pedido pedido, Vinil vinil,
                      Integer quantidade, BigDecimal precoUnitario) {
        this.id = id;
        this.pedido = pedido;
        this.vinil = vinil;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
    }

    public Long getId() { 
        return id; 
    }
    
    public void setId(Long id) { 
        this.id = id; 
    }
    
    public Pedido getPedido() { 
        return pedido; 
    }
    
    public void setPedido(Pedido pedido) { 
        this.pedido = pedido; 
    }
    
    public Vinil getVinil() { 
        return vinil; 
    }
    
    public void setVinil(Vinil vinil) { 
        this.vinil = vinil; 
    }
    
    public Integer getQuantidade() { 
        return quantidade; 
    }
    
    public void setQuantidade(Integer quantidade) { 
        this.quantidade = quantidade; 
    }
    
    public BigDecimal getPrecoUnitario() { 
        return precoUnitario; 
    }
    
    public void setPrecoUnitario(BigDecimal precoUnitario) { 
        this.precoUnitario = precoUnitario; 
    }
}