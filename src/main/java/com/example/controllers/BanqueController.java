package com.example.controllers;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.DAO.ClientRepository;
import com.example.DAO.CompteRepository;
import com.example.entities.Client;
import com.example.entities.Compte;
import com.example.entities.CompteCourant;
import com.example.entities.CompteEpargne;
import com.example.entities.Operation;
import com.example.metier.BanqueMetierImpl;
import com.example.metier.IBanqueMetier;
@Controller
public class BanqueController {
@Autowired
	private BanqueMetierImpl banqueMetier;
	
	
	/*
	 * @GetMapping(value="/consulterCompte") public String consulterCompte(Model
	 * model, String codeCompte,
	 * 
	 * @RequestParam(name="page", defaultValue="0") int page,
	 * 
	 * @RequestParam(name="size", defaultValue="5") int size){
	 * model.addAttribute("codeCompte", codeCompte);
	 * 
	 * return "home/compteShow"; }
	 */
	
	
	
	@GetMapping(value="/consulterCompte1")
	public String consulter(Model model, String codeCompte,
			@RequestParam(name="page", defaultValue="0") int page,
			@RequestParam(name="size", defaultValue="5") int size){
		model.addAttribute("codeCompte", codeCompte);
		try{
			Compte cp = banqueMetier.consulterCompte(codeCompte);
			model.addAttribute("compte", cp);
			Page<Operation> pageOperations= banqueMetier.listOperation(codeCompte, page, size);
			model.addAttribute("listOperations", pageOperations.getContent());
			int[] pages = new int [pageOperations.getTotalPages()];
			model.addAttribute("pages",pages);
		} catch (Exception e) {
			model.addAttribute("exception",e);
		}
		return "home/operation";
	}
	@GetMapping(value="/addOperation")
	public  String addCommand (Model model) {
		//ipRepository.addProduit(p);
		 return "home/operation";

	}
	@RequestMapping(value="/saveOperation",method=RequestMethod.POST)
	public String saveOperation(Model model,String typeOperation,String codeCompte,double montant,String codeCompte2) {
		try {
			if(typeOperation.equals("versement")) {
				banqueMetier.verser(codeCompte, montant);
			}
			else if(typeOperation.equals("retrait")) {
				banqueMetier.retirer(codeCompte, montant);
			}
			else { // Autrement il s'agit d'un virement. 
				banqueMetier.virement(codeCompte, codeCompte2, montant);
			}
		}catch(Exception e) {
			model.addAttribute("error", e);
			return "redirect:/consulterCompte1?codeCompte="+codeCompte+"&error="+e.getMessage();
		}
		
		return "redirect:/consulterCompte1?codeCompte="+codeCompte;
	}
}
