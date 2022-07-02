package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.example.demo.entities.Compte;
import com.example.demo.entities.Operation;

public interface IBanqueService {
	public Compte ConsulterCompte(Long codeCompte) ;
	public void verser(Long codeCompte, double montant);
	public void retirer(Long codeCompte, double montant);
	public void vierement(Long codeCompte1, Long codeCompte2,double montant);
	public Page<Operation> listOperation (Long codeCompte,int page , int size);
	public List<Compte> ListCompte();

}
