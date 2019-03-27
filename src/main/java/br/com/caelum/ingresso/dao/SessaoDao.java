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
	EntityManager manager;

	public void salva(Sessao sessao) {
		manager.persist(sessao);
	}

	public List<Sessao> findAllBySala(Integer salaId) {
		Query query = manager.createQuery("select sessao from Sessao sessao where sessao.sala.id = :idSala");
		query.setParameter("idSala", salaId);
		List<Sessao> sessoes = query.getResultList();
		return sessoes;
	}

	public List<Sessao> findAllByFilme(Integer filmeId) {
		Query query = manager.createQuery("select sessao from Sessao sessao " + "where sessao.filme.id = :idFilme");
		query.setParameter("idFilme", filmeId);
		return query.getResultList();
	}
}
