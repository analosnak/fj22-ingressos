package br.com.caelum.ingresso.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;

@Repository
public class SessaoDao {
	
	@PersistenceContext
	private EntityManager manager;

	public void save(Sessao sessao) {
		manager.persist(sessao);
	}
	
	public List<Sessao> listaSessoesDaSala(Sala sala) {
		String sql = "select s from Sessao s where s.sala = :paramSala";
		Query query = manager.createQuery(sql);
		query.setParameter("paramSala", sala);
		List<Sessao> sessoes = query.getResultList();
		return sessoes;
	}
	
	public List<Sessao> listaSessoesDoFilme(Filme filme) {
		String sql = "select s from Sessao s where s.filme = :filme";
		Query query = manager.createQuery(sql);
		query.setParameter("filme", filme);
		return query.getResultList();
	}
	
	public Sessao findOne(Integer id) {
		return manager.find(Sessao.class, id);
	}
}





