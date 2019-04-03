package br.com.caelum.ingresso.model.form;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import br.com.caelum.ingresso.dao.LugarDao;
import br.com.caelum.ingresso.dao.SessaoDao;
import br.com.caelum.ingresso.model.Ingresso;
import br.com.caelum.ingresso.model.Lugar;
import br.com.caelum.ingresso.model.Sessao;

public class CarrinhoForm {

	private List<Ingresso> ingressos;

	public List<Ingresso> getIngressos() {
		return ingressos;
	}

	public void setIngressos(List<Ingresso> ingressos) {
		this.ingressos = ingressos;
	}

	public List<Ingresso> toIngressos(SessaoDao sessaoDao, LugarDao lugarDao) {
		Stream<Ingresso> stream = this.getIngressos().stream().map(incompleto -> {
			Sessao sessao = sessaoDao.findOne(incompleto.getSessao().getId());
			Lugar lugar = lugarDao.findOne(incompleto.getLugar().getId());
			Ingresso completo = new Ingresso(sessao, incompleto.getTipoDeIngresso(), lugar);
			return completo;
		});
		return stream.collect(Collectors.toList());
	}

}
