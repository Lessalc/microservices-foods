package com.lessalc.Pedidos.service;

import com.lessalc.Pedidos.dto.PedidoDto;
import com.lessalc.Pedidos.dto.StatusDto;
import com.lessalc.Pedidos.model.Pedido;
import com.lessalc.Pedidos.model.Status;
import com.lessalc.Pedidos.repository.PedidoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    public List<PedidoDto> obterTodos() {
        return repository.findAll().stream().map(pedido -> new PedidoDto(pedido)).collect(Collectors.toList());
    }

    public PedidoDto obterPorId(Long id) {
        Pedido pedido = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        return new PedidoDto(pedido);
    }

    public PedidoDto criarPedido(PedidoDto dto) {
        dto.setDataHora(LocalDateTime.now());
        dto.setStatus(Status.REALIZADO);
        Pedido pedido = new Pedido(dto);
        pedido.getItens().forEach(item -> item.setPedido(pedido));

        Pedido save = repository.save(pedido);
        return new PedidoDto(pedido);

    }

    public PedidoDto atualizaStatus(Long id, StatusDto dto) {

        Pedido pedido = repository.porIdComItens(id);
        if(pedido == null)
            throw new EntityNotFoundException();
        pedido.setStatus(dto.status());
        repository.atualizaStatus(dto.status(), pedido);
        return new PedidoDto(pedido);
    }

    public void aprovaPagamentoPedido(Long id) {
        Pedido pedido = repository.porIdComItens(id);
        if(pedido == null)
            throw new EntityNotFoundException();
        pedido.setStatus(Status.PAGO);
        repository.atualizaStatus(Status.PAGO, pedido);
    }
}
