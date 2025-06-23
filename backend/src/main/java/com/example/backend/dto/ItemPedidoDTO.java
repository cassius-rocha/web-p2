package com.example.backend.dto;

import java.math.BigDecimal;

public class ItemPedidoDTO {
    private Long produtoId;
    private Integer quantidade;
    private BigDecimal precoUnitario;

    

    /**
     * @return Long return the produtoId
     */
    public Long getProdutoId() {
        return produtoId;
    }

    /**
     * @param produtoId the produtoId to set
     */
    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    /**
     * @return Integer return the quantidade
     */
    public Integer getQuantidade() {
        return quantidade;
    }

    /**
     * @param quantidade the quantidade to set
     */
    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * @return BigDecimal return the precoUnitario
     */
    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    /**
     * @param precoUnitario the precoUnitario to set
     */
    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

}
