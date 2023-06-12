package com.lessalc.pagamentos.dto;

import com.lessalc.pagamentos.model.Pagamento;
import com.lessalc.pagamentos.model.Status;
import java.math.BigDecimal;

public record PagamentoDto(
        Long id,
        BigDecimal valor,
        String nome,
        String numero,
        String expiracao,
        String codigo,
        Status status,
        Long pedidoId,
        Long formaDePagamentoId
) {
    public PagamentoDto(Pagamento pagamento){
        this(pagamento.getId(), pagamento.getValor(), pagamento.getNome(), pagamento.getNumero(),
                pagamento.getExpiracao(), pagamento.getCodigo(), pagamento.getStatus(), pagamento.getPedidoId(),
                pagamento.getFormaDePagamentoId());
    }

}
