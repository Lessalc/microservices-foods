package com.lessalc.Pedidos.dto;

import com.lessalc.Pedidos.model.ItemDoPedido;

public record ItemDoPedidoDto(
        Long id,
        Integer quantidade,
        String descricao
) {
    public ItemDoPedidoDto(ItemDoPedido item){
        this(item.getId(), item.getQuantidade(), item.getDescricao());
    }
}
