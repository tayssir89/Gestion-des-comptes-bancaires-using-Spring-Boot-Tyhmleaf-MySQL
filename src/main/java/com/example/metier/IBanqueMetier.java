package com.example.metier;

import org.springframework.data.domain.Page;

import com.example.entities.*;

public interface IBanqueMetier {
	public Compte consulterCompte(String codeCompte);
	public void verser(String codeCompte, double montant);
	public void retirer(String codeCompte, double montant);
	public void virement(String codeCompte, String codeCpte2, double montant);
	public Page<Operation> listOperation(String codeCompte, int page, int size);
}
