package com.lessalc.Pedidos.repository;

import com.lessalc.Pedidos.model.Pedido;
import com.lessalc.Pedidos.model.Status;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Query(value = "SELECT p FROM Pedido p LEFT JOIN FETCH p.itens WHERE p.id = :id")
    Pedido porIdComItens(Long id);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE Pedido p SET p.status = :status WHERE p = :pedido")
    void atualizaStatus(Status status, Pedido pedido);
}
