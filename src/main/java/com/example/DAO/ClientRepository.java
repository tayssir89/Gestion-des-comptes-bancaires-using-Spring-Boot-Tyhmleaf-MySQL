package com.example.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.entities.Client;
@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
	@Query("select c from Client c where c.code =?1 ")
	public Client findByCode(Long code);
}
