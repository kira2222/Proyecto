package co.com.msservielectrogas.repository;

import co.com.msservielectrogas.dto.WarrantyWithOrderServiceDTO;
import co.com.msservielectrogas.entity.Warranty;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IWarrantyRepository extends JpaRepository<Warranty, Long> {
    // JpaRepository ya incluye el método findAll, no necesitas definir nada adicional aquí.
}
