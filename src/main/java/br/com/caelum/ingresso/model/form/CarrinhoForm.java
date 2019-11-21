package br.com.caelum.ingresso.model.form;

import java.util.List;
import java.util.stream.Collectors;

import br.com.caelum.ingresso.dao.LugarDao;
import br.com.caelum.ingresso.dao.SessaoDao;
import br.com.caelum.ingresso.model.Ingresso;
import br.com.caelum.ingresso.model.Lugar;
import br.com.caelum.ingresso.model.Sessao;

public class CarrinhoForm {
	private List<Ingresso> ingressos; // ingressos incompletos 

	public List<Ingresso> preencheIngressos(SessaoDao sessaoDao, LugarDao lugarDao) {
		return ingressos.stream()
		.map(ingressoIncompleto -> {
		Sessao sessao = sessaoDao.findOne(ingressoIncompleto.getSessao().getId());
		Lugar lugar = lugarDao.findOne(ingressoIncompleto.getLugar().getId());
		return new Ingresso(sessao, lugar, ingressoIncompleto.getTipoDeIngresso());
		}).collect(Collectors.toList());
	}
	
	public List<Ingresso> getIngressos() {
		return ingressos;
	}

	public void setIngressos(List<Ingresso> ingressos) {
		this.ingressos = ingressos;
	}
}
