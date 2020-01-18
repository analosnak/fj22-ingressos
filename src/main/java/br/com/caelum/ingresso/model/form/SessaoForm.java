package br.com.caelum.ingresso.model.form;

import java.time.LocalTime;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.caelum.ingresso.dao.FilmeDao;
import br.com.caelum.ingresso.dao.SalaDao;
import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;

public class SessaoForm {
	@NotNull
	private Integer salaId;
	@NotNull
	private Integer filmeId;
	@NotNull
	private LocalTime horario;
	
	public Integer getSalaId() {
		return salaId;
	}
	public void setSalaId(Integer salaId) {
		this.salaId = salaId;
	}
	public Integer getFilmeId() {
		return filmeId;
	}
	public void setFilmeId(Integer filmeId) {
		this.filmeId = filmeId;
	}
	public LocalTime getHorario() {
		return horario;
	}
	public void setHorario(LocalTime horario) {
		this.horario = horario;
	}
	public Sessao toSessao(FilmeDao filmeDao, SalaDao salaDao) {
		LocalTime horario = this.getHorario();
		Filme filme = filmeDao.findOne(this.getFilmeId());	
		Sala sala = salaDao.findOne(this.getSalaId());
		
		Sessao sessao = new Sessao(sala, filme, horario);
		return null;
	}
}
