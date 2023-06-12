package com.lessalc.pagamentos.service;

import com.lessalc.pagamentos.dto.PagamentoDto;
import com.lessalc.pagamentos.model.Pagamento;
import com.lessalc.pagamentos.model.Status;
import com.lessalc.pagamentos.repository.PagamentoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository repository;

    public Page<PagamentoDto> obterTodos(Pageable paginacao){
        return repository
                .findAll(paginacao)
                .map(PagamentoDto::new);
    }

    public PagamentoDto obterPorId(Long id){
        Pagamento pagamento = repository.findById(id).orElseThrow(() ->new EntityNotFoundException());
        return new PagamentoDto(pagamento);
    }

    public PagamentoDto criarPagamento(PagamentoDto dto){
        Pagamento pagamento = new Pagamento(dto);
        pagamento.setStatus(Status.CRIADO);
        repository.save(pagamento);
        return new PagamentoDto(pagamento);
    }

    public PagamentoDto atualizarPagamento(Long id, PagamentoDto dto){
        Pagamento pagamento = new Pagamento(dto);
        pagamento.setId(id);
        pagamento = repository.save(pagamento);
        return new PagamentoDto(pagamento);
    }

    public void excluirPagamento(Long id){
        repository.deleteById(id);
    }
}
