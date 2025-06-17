package com.example.backend.models;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    private LocalDateTime dataCriacao;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemPedido> itens = new ArrayList<>();

    private BigDecimal total;

    public Pedido() {
    }

    public Pedido(Long id, Cliente cliente, LocalDateTime dataCriacao,
                  List<ItemPedido> itens, BigDecimal total) {
        this.id = id;
        this.cliente = cliente;
        this.dataCriacao = dataCriacao;
        this.itens = itens;
        this.total = total;
    }

    public Long getId() { 
        return id; 
    }
    
    public void setId(Long id) { 
        this.id = id; 
    }
    
    public Cliente getCliente() { 
        return cliente; 
    }
    
    public void setCliente(Cliente cliente) { 
        this.cliente = cliente; 
    }
    
    public LocalDateTime getDataCriacao() { 
        return dataCriacao; 
    }
    
    public void setDataCriacao(LocalDateTime dataCriacao) { 
        this.dataCriacao = dataCriacao; 
    }
    
    public List<ItemPedido> getItens() { 
        return itens; 
    }
    
    public void setItens(List<ItemPedido> itens) { 
        this.itens = itens; 
    }
    
    public BigDecimal getTotal() { 
        return total; 
    }
    
    public void setTotal(BigDecimal total) { 
        this.total = total; 
    }
}
