package com.example.metier;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.DAO.ClientRepository;
import com.example.DAO.CompteRepository;
import com.example.entities.Client;
import com.example.entities.Compte;
import com.example.entities.CompteCourant;
import com.example.entities.CompteEpargne;
import com.example.entities.Versement;
@Service
public class CompteMetierImpl implements ICompteMetier {
@Autowired
private ClientRepository crep;
@Autowired
private CompteRepository compteRepository;
	@Override
	public Client saveclient(@RequestParam(name = "Provider_id")Long code) {
		Client clt = crep.findByCode(code);
		if(clt == null) throw new RuntimeException("Client introuvable");
		return clt;
	
	}
	public void courant(String codeCompte, String nom,  String email, String typeCompte,
			String decouvert, String taux, String solde, Long code) {
		double _solde = Double.parseDouble(solde);

		Client cp = saveclient(code);
		double _decouvert = Double.parseDouble(decouvert);

		CompteCourant cc = new CompteCourant(codeCompte, new Date(),_solde, cp, _decouvert);
		compteRepository.save(cc);
		
		 
	}
	public void epargne(String codeCompte, String nom,  String email, String typeCompte,
			String decouvert, String taux, String solde, Long code) {
		double _solde = Double.parseDouble(solde);

		Client cp = saveclient(code);
		double _taux = Double.parseDouble(taux);

		CompteEpargne ce = new CompteEpargne(codeCompte, new Date(),_solde, cp, _taux);
		compteRepository.save(ce);
		
		
	}

}
