package com.example.backend.dto;

import java.util.List;

public class PedidoDTO {
    private Long clienteId;
    private List<ItemPedidoDTO> itens;

    

    /**
     * @return Long return the clienteId
     */
    public Long getClienteId() {
        return clienteId;
    }

    /**
     * @param clienteId the clienteId to set
     */
    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    /**
     * @return List<ItemPedidoDTO> return the itens
     */
    public List<ItemPedidoDTO> getItens() {
        return itens;
    }

    /**
     * @param itens the itens to set
     */
    public void setItens(List<ItemPedidoDTO> itens) {
        this.itens = itens;
    }

}
