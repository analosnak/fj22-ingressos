package br.com.caelum.ingresso.model.form;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.caelum.ingresso.controller.TipoDeIngresso;
import br.com.caelum.ingresso.dao.LugarDao;
import br.com.caelum.ingresso.dao.SessaoDao;
import br.com.caelum.ingresso.model.Ingresso;
import br.com.caelum.ingresso.model.Lugar;
import br.com.caelum.ingresso.model.Sessao;

public class IngressosForm {

	private List<Ingresso> ingressos;

	public List<Ingresso> getIngressos() {
		return ingressos;
	}

	public void setIngressos(List<Ingresso> ingressos) {
		this.ingressos = ingressos;
	}

	public List<Ingresso> pegaIngressosCompletos(
			LugarDao lugarDao, SessaoDao sessaoDao) {
		
		
		
		return ingressos.stream().map(ingressoIncompleto -> {
		
		Integer sessaoId = ingressoIncompleto.getSessao().getId();
		Sessao sessao = sessaoDao.findOne(sessaoId);
		
		Integer lugarId = ingressoIncompleto.getLugar().getId();
		Lugar lugar = lugarDao.findOne(lugarId);
		TipoDeIngresso tipoDeIngresso = ingressoIncompleto.getTipoDeIngresso();
		
		return new Ingresso(sessao, lugar, tipoDeIngresso);
		}).collect(Collectors.toList());
	}
}
