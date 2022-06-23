package com.example.controllers;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.DAO.ClientRepository;
import com.example.DAO.CompteRepository;
import com.example.entities.Client;
import com.example.entities.Compte;
import com.example.entities.CompteCourant;
import com.example.entities.CompteEpargne;
import com.example.metier.CompteMetierImpl;
import com.example.metier.IBanqueMetier;
import com.example.metier.ICompteMetier;

@Controller
public class CompteController {
	@Autowired(required=false)
	private CompteMetierImpl compteMetier;
	@Autowired
	private ClientRepository crep;
	@Autowired
	private CompteRepository coep;
	
	@RequestMapping(value="/")
	public String home(){
		
		return "redirect:/consulterCompte1";
	}
	@GetMapping(value="/consulterCompte") public String search(Model model,
			  
			  @RequestParam(name="page" ,defaultValue= "0") int page,
			  
			  @RequestParam(name="codeCompte",defaultValue="") String codeCompte) {
			 
			 
			  Page<Compte> pageProduits =
					  coep.findByCodeCompte(codeCompte,PageRequest.of(page,4)); 
			  int pageCount = pageProduits.getTotalPages();
					  int[] pages = new int[pageCount];
			  for(int i=0;i<pageCount;i++) 
				  pages[i]=i; model.addAttribute("pages",pages);
			  model.addAttribute("codeCompte",codeCompte); 
			  model.addAttribute("pagecourante",page);
			  model.addAttribute("pageproduits", pageProduits); 
				return "home/compteShow";
			  }
	@GetMapping(value="addC")
	public  String addCommand (Model model) {
		//ipRepository.addProduit(p);
model.addAttribute("client", crep.findAll());
		 return "home/addCompte";

	}
	
	@RequestMapping(value="/save-project",method=RequestMethod.POST)
	public String createNewAccount(Model model,String codeCompte, String nom,  String email, String typeCompte,
			String decouvert, String taux, String solde, @RequestParam(name = "Provider_id") Long code) {
		
		System.out.println("=============::"+typeCompte+"========::=======");
		if(typeCompte.contentEquals("compteCourant")) {
			compteMetier.courant(codeCompte, nom, email, typeCompte, decouvert, taux, solde, code);
		}
		
		if(typeCompte.contentEquals("compteEpargne")) {
			compteMetier.epargne(codeCompte, nom, email, typeCompte, decouvert, taux, solde, code);

			
		}
		
		return "redirect:/home";
	}
	
	
	
}
