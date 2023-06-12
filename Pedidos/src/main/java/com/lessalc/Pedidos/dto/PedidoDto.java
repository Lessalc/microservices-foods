package com.lessalc.Pedidos.dto;

import com.lessalc.Pedidos.model.ItemDoPedido;
import com.lessalc.Pedidos.model.Pedido;
import com.lessalc.Pedidos.model.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDto {
    private Long id;
    private LocalDateTime dataHora;
    private Status status;
    private List<ItemDoPedidoDto> itens = new ArrayList<>();

    public PedidoDto(Pedido pedido){
        this.id = pedido.getId();
        this.dataHora = pedido.getDataHora();
        this.status = pedido.getStatus();
        this.itens = pedido.getItens().stream().map(ItemDoPedidoDto::new).collect(Collectors.toList());
    }

    public List<ItemDoPedido> getItensDoPedido(){
        return this.getItens().stream().map(ItemDoPedido::new).collect(Collectors.toList());
    }
}
