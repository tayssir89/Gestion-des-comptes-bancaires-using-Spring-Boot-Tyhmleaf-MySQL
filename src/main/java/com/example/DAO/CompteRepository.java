package com.example.DAO;

import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.entities.Compte;
@Repository
public interface CompteRepository extends JpaRepository<Compte, String> {
	@Query("select c from Compte c where c.codeCompte =?1 ")
	public Compte findByCodeCompte(String codeCompte);
	public  Page<Compte> findByCodeCompte(String codeCompte,Pageable pageable) ;

}
