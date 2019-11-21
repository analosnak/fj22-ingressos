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
	
	public void salva(Sessao sessao) {
		manager.persist(sessao);
	}
	
	public List<Sessao> buscaSessoesDaSala(Sala sala) {
		String sql = "select s from Sessao s where s.sala = :salaParam";
		Query query = manager.createQuery(sql, Sessao.class);
		query.setParameter("salaParam", sala);
		return query.getResultList();
	}

	public List<Sessao> buscaSessoesDoFilme(Filme filme) {
		String sql = "select s from Sessao s where s.filme = :filmeParam";
		Query query = manager.createQuery(sql, Sessao.class);
		query.setParameter("filmeParam", filme);
		return query.getResultList();
	}
	
	public Sessao findOne(Integer id) {
		return manager.find(Sessao.class, id);
	}
	
	
	
	
	
	
	


	public List<Sessao> lista() {
		String sql = "select s from Sessao s";
		Query query = manager.createQuery(sql, Sessao.class);
		return query.getResultList();		
	}

	public void atualiza(Sessao sessao) {
		manager.merge(sessao);
	}

}
