package com.example.demo.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QPageRequest;

import com.example.demo.entities.Compte;

public interface CompteRepository extends JpaRepository<Compte, Long>{

	

}
