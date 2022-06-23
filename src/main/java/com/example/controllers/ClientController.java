package com.example.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.DAO.*;
import com.example.entities.Client;
@RequestMapping("/client/")

@Controller
public class ClientController {
	private final ClientRepository crep;
@Autowired
	public ClientController(ClientRepository crep) {
		super();
		this.crep = crep;
	}
@GetMapping(value = "index")
 public String home(Model model)
{ model.addAttribute("clients", crep.findAll());
	return "home/index"; }

@GetMapping(value="add")
public  String addCommand (Model model) {
	//ipRepository.addProduit(p);
	
    model.addAttribute("client", new Client());
	 return "home/addClient";

}
@PostMapping("add")
public String addClient(@Valid Client clt,
BindingResult result, Model model) {
if (result.hasErrors()) {
return "home/addClient";
}
crep.save(clt);
return "redirect:index";
}
@GetMapping("edit/{code}")
public String showProviderFormToUpdate(@PathVariable("code") Long code, Model model) {
Client clt = crep.findById(code)
.orElseThrow(()->new IllegalArgumentException("Invalid client Id:" + code));
model.addAttribute("client", clt);
return "home/updateClient";
}
@PostMapping("update")
public String updateProvider(@Valid Client clt, BindingResult result, Model model) {
crep.save(clt);
return "redirect:index";
}
@GetMapping("delete/{code}")
public String deleteProvider(@PathVariable("code") long code, Model model)
{
Client clt = crep.findById(code)
.orElseThrow(()-> new IllegalArgumentException("Invalid client Id:" + code));
System.out.println("suite du programme...");
crep.delete(clt);
 return "redirect:../index";
}
}
