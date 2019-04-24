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

    public List<Sessao> findAllBySala(Sala sala) {
        Query query = manager.createQuery("select sessao from Sessao sessao " + "where sessao.sala = :paramSala",
                Sessao.class);
        query.setParameter("paramSala", sala);
        List<Sessao> sessoes = query.getResultList();
        return sessoes;
    }

    public List<Sessao> findAllByFilme(Filme filme) {
        Query query = manager.createQuery("select sessao from Sessao sessao " + "where sessao.filme = :paramFilme",
                Sessao.class);
        query.setParameter("paramFilme", filme);
        List<Sessao> sessoes = query.getResultList();
        return sessoes;
    }

}
