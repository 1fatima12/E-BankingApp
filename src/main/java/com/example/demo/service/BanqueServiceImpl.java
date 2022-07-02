package com.example.demo.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.CompteRepository;
import com.example.demo.dao.OperationRepository;
import com.example.demo.entities.Compte;
import com.example.demo.entities.CompteCourant;
import com.example.demo.entities.Operation;
import com.example.demo.entities.Retrait;
import com.example.demo.entities.Versement;
@Service
@Transactional
public class BanqueServiceImpl implements IBanqueService{
	 @Autowired
private CompteRepository compterepository;
	 @Autowired
private OperationRepository operationrepository;
	@Override
	public Compte ConsulterCompte(Long codeCompte) {
		Compte cp = compterepository.findById(codeCompte).get();

	//	Compte cp = compterepository.getById(codeCompte);
		if(cp==null) throw new RuntimeException("compte introuvable");
		return cp;
		
	}

	@Override
	public void verser(Long codeCompte, double montant) {
		Compte cp=ConsulterCompte(codeCompte);
		Versement v= new Versement(new Date(),montant,cp);
		operationrepository.save(v);
		cp.setSolde(cp.getSolde()+montant);
		compterepository.save(cp);
	}

	@Override
	public void retirer(Long codeCompte, double montant) {
		Compte cp=ConsulterCompte(codeCompte);
		double indicateur=0;
		if(cp instanceof CompteCourant)
			indicateur=((CompteCourant) cp).getDecouvert();
		if(cp.getSolde()+indicateur<montant) {			
			throw new RuntimeException("le solde insuffisant");
        }
		Retrait v= new Retrait(new Date(),montant,cp);
		operationrepository.save(v);
		cp.setSolde(cp.getSolde()-montant);
		if(cp.getSolde()<0) {
			throw new RuntimeException("le solde insuffisant");

		}
		compterepository.save(cp);		
	}

	@Override
	public void vierement(Long codeCompte1, Long codeCompte2, double montant) {
        retirer(codeCompte1,montant);
        verser(codeCompte2,montant);
	}

	@Override
	public Page<Operation> listOperation(Long codeCompte, int page, int size) {
		// TODO Auto-generated method stub
		//retur{n operationrepository.listOperation(codeCompte, new QPageRequest(page, size));
		return operationrepository.listOperation(codeCompte, new QPageRequest(page, size));
	}
	

	@Override
	public List<Compte> ListCompte() {
		// TODO Auto-generated method stub
		return compterepository.findAll();
	}}
