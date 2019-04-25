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

    public List<Ingresso> pegaIngressos(SessaoDao sessaoDao, LugarDao lugarDao) {
        return ingressos.stream().map(ingressoIncompleto -> {
            Sessao sessao = sessaoDao.findOne(ingressoIncompleto.getSessao().getId());
            Lugar lugar = lugarDao.findOne(ingressoIncompleto.getLugar().getId());
            TipoDeIngresso tipoDeIngresso = ingressoIncompleto.getTipoDeIngresso();
            Ingresso ingressoCompleto = new Ingresso(sessao, lugar, tipoDeIngresso);
            return ingressoCompleto;
        }).collect(Collectors.toList());
    }
}
