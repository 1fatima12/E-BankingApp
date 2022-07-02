package com.example.demo.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class Client implements Serializable{
	@Id @GeneratedValue
	private Long code;
	private String nom;
	private String email;
	//il existe ici une association : un client a plusieurs comptes 
	//et puisque c'est une association bidirectionnelle on va faire mappedBy
	@OneToMany(mappedBy="client" , fetch =FetchType.LAZY)
	private Collection<Compte> comptes;
	public Long getCode() {
		return code;
	}
	public void setCode(Long code) {
		this.code = code;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Collection<Compte> getComptes() {
		return comptes;
	}
	public void setComptes(Collection<Compte> comptes) {
		this.comptes = comptes;
	}
	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Client(String nom, String email) {
		super();
		this.nom = nom;
		this.email = email;
	}

}
