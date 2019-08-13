package br.com.caelum.ingresso.model.form;

import java.time.LocalTime;

import javax.validation.constraints.NotNull;

import br.com.caelum.ingresso.dao.FilmeDao;
import br.com.caelum.ingresso.dao.SalaDao;
import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;

public class SessaoForm {
	@NotNull
	private Integer salaId;
	@NotNull
	private LocalTime horario;
	@NotNull
	private Integer filmeId;
	
	
	public Integer getSalaId() {
		return salaId;
	}
	public void setSalaId(Integer salaId) {
		this.salaId = salaId;
	}
	public LocalTime getHorario() {
		return horario;
	}
	public void setHorario(LocalTime horario) {
		this.horario = horario;
	}
	public Integer getFilmeId() {
		return filmeId;
	}
	public void setFilmeId(Integer filmeId) {
		this.filmeId = filmeId;
	}
	
	public Sessao toSessao(SalaDao salaDao, FilmeDao filmeDao) {
		LocalTime horario = this.getHorario();
		
		Integer salaId = this.getSalaId();
		Sala sala = salaDao.findOne(salaId);
		
		Integer filmeId = this.getFilmeId();
		Filme filme = filmeDao.findOne(filmeId);
		
		Sessao sessao = new Sessao(horario, sala, filme);
		return sessao;
	}
	
}
