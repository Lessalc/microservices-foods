package com.lessalc.pagamentos.model;

import com.lessalc.pagamentos.dto.PagamentoDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name="pagamentos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull @Positive
    private BigDecimal valor;

    @NotBlank @Size(max = 100)
    private String nome;

    @NotBlank @Size(max = 19)
    private String numero;

    @NotBlank @Size(max = 7)
    private String expiracao;

    @NotBlank @Size(min = 3, max = 3)
    private String codigo;

    @Enumerated(EnumType.STRING) @NotNull
    private Status status;

    @NotNull
    private Long pedidoId;

    @NotNull
    private Long formaDePagamentoId;

    public Pagamento(PagamentoDto dto) {
        this.id = dto.id();
        this.valor = dto.valor();
        this.nome = dto.nome();
        this.numero = dto.numero();
        this.expiracao = dto.expiracao();
        this.codigo = dto.codigo();
        this.status = dto.status();
        this.pedidoId = dto.pedidoId();
        this.formaDePagamentoId = dto.formaDePagamentoId();
    }
}
