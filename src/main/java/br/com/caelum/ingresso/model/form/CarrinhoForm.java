package br.com.caelum.ingresso.model.form;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import br.com.caelum.ingresso.dao.LugarDao;
import br.com.caelum.ingresso.dao.SessaoDao;
import br.com.caelum.ingresso.model.Ingresso;
import br.com.caelum.ingresso.model.Lugar;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.TipoDeIngresso;

public class CarrinhoForm {	
	private List<Ingresso> ingressos;

	public List<Ingresso> getIngressos() {
		return ingressos;
	}

	public void setIngressos(List<Ingresso> ingressos) {
		this.ingressos = ingressos;
	}
	
	public List<Ingresso> toIngressosCompletos(SessaoDao sessaoDao, LugarDao lugarDao) {
		Stream<Ingresso> streamIngressoCompletos = 
				ingressos.stream().map(ingressoIncompleto -> {
			Integer sessaoId = ingressoIncompleto.getSessao().getId();
			Sessao sessao = sessaoDao.findOne(sessaoId);
			
			Integer lugarId = ingressoIncompleto.getLugar().getId();
			Lugar lugar = lugarDao.findOne(lugarId);
			
			TipoDeIngresso tipoDeIngresso =
					ingressoIncompleto.getTipoDeIngresso();
			
			return new Ingresso(sessao, tipoDeIngresso, lugar);
		});
		return streamIngressoCompletos.collect(Collectors.toList());
	}
}
