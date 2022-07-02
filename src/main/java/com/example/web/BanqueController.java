package com.example.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entities.Compte;
import com.example.demo.entities.Operation;
import com.example.demo.service.IBanqueService;

@Controller
public class BanqueController {
	@Autowired
	private IBanqueService banqueService;
	@Autowired
	private HttpSession session;
	@RequestMapping("/index")
    public String index() {
    	return ("comptes");
    }
	@RequestMapping("/consulterCompte")
    public String consulter(Model model ,Long codeCompte) {
		model.addAttribute("codeCompte", codeCompte);
		try {
			Compte c =banqueService.ConsulterCompte(codeCompte);
			model.addAttribute("compte",c);
			Page<Operation> pageOperations=banqueService.listOperation(codeCompte, 0, 4);
			model.addAttribute("listOperations",pageOperations.getContent());
		}catch(Exception e) {
			model.addAttribute("exception",e);
			System.out.println("exception");
			
		}
    	return ("comptes");
    }
	@RequestMapping("/ListComptes")
	public String Comptes(Model model) {
		//cette methode va nous permettre d'afficher toute les comptes exsistant
		List<Compte> l = banqueService.ListCompte();
		model.addAttribute("listeComptes",l);
		return "ListeComptes";
		
	}
	@RequestMapping("/saveOperation")
	public String saveOperation(Model model,String Type_Operation,Long codeCompte,Long codeCompte2,double montant) {
		try {
			if(Type_Operation.equals("VERS")) {
				banqueService.verser(codeCompte,montant );
			}
			if(Type_Operation.equals("RET")) {
				banqueService.retirer(codeCompte, montant);
			}
			if(Type_Operation.equals("VIR")) {
				if(codeCompte==codeCompte2) {
					session.setAttribute("Exception3","Operation impossible");
				}
				banqueService.vierement(codeCompte, codeCompte2, montant);
			}
		}catch(Exception e) {
			model.addAttribute("Exception2",e);
			System.out.println(e);
			return "redirect:/consulterCompte?codeCompte="+codeCompte+"&Exception2="+e.getMessage();

		}
		
		//car dans le formulaire de consultation on travail avec get
		return "redirect:/consulterCompte?codeCompte="+codeCompte;
	}
}
