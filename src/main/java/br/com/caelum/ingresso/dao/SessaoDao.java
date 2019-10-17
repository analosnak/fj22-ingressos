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
	
	public List<Sessao> findAllBySala(Integer salaId) {
		Query query = 
				manager.createQuery("select s "
			+ "from Sessao s where s.sala.id = :paramSala");
		query.setParameter("paramSala", salaId);
		return query.getResultList();
	}

	public List<Sessao> findAllByFilme(Integer filmeId) {
		Query query = 
				manager.createQuery("select s "
			+ "from Sessao s where s.filme.id = :paramFilme");
		query.setParameter("paramFilme", filmeId);
		return query.getResultList();
	}

	public Sessao findOne(Integer id) {
		Sessao sessao = manager.find(Sessao.class, id);
		return sessao;
	}
}
