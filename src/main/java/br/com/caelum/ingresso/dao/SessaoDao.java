package br.com.caelum.ingresso.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.caelum.ingresso.model.Sessao;

@Repository
public class SessaoDao {
	@PersistenceContext
	private EntityManager manager;
	
	
	public void salva(Sessao sessao) {
		manager.persist(sessao);
	}
	
	public List<Sessao> buscaSessoesDaSala(Integer salaId) {
		Query query = manager.createQuery(
				"select s from Sessao s where s.sala.id = :id");
		query.setParameter("id", salaId);
		
		return query.getResultList();
	}
}
