package co.com.msservielectrogas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.com.msservielectrogas.entity.Clients;

@Repository
public interface IClientRepository  extends JpaRepository<Clients, Integer>{
	
	boolean existsByPhone(String phone);
	
	boolean existsByAddress(String address);
	
	boolean existsByDocument(String document);
	
	Clients findById(Long id);

	Clients findByPhone(String phone);
	
	Clients findByPhoneOrDocument(String phone, String document);
	
	Clients findByPhoneOrDocumentOrAddress(String phone, String document, String address);
	
    @Query("SELECT c FROM Clients c WHERE LOWER(c.names) LIKE LOWER(CONCAT('%', :search, '%'))")
    List<Clients> searchClients(String search);
}
