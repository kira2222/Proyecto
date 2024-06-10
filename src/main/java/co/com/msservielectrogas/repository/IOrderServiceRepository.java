package co.com.msservielectrogas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.com.msservielectrogas.dto.OrderServiceDetailsDTO;
import co.com.msservielectrogas.entity.Clients;
import co.com.msservielectrogas.entity.OrderService;
import java.util.Optional;


@Repository
public interface IOrderServiceRepository extends JpaRepository<OrderService, Long> {
    List<OrderService> findByOrderId(Long orderId);
    @Query("SELECT new co.com.msservielectrogas.dto.OrderServiceDetailsDTO(os, o, s, t) " +
    "FROM OrderService os " +
    "INNER JOIN os.order o " +
    "INNER JOIN os.service s " +
    "INNER JOIN os.technician t " +
    "WHERE os.order.id = :orderId")
List<OrderServiceDetailsDTO> findDetailedByOrderId(@Param("orderId") Long orderId);
}