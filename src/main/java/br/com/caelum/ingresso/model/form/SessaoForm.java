package br.com.caelum.ingresso.model.form;

import java.time.LocalTime;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.caelum.ingresso.dao.FilmeDao;
import br.com.caelum.ingresso.dao.SalaDao;
import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;

public class SessaoForm {
	@NotNull
	@DateTimeFormat(pattern="HH:mm")
	private LocalTime horario;
	@NotNull
	private Integer salaId;
	@NotNull
	private Integer filmeId;
	
	public LocalTime getHorario() {
		return horario;
	}
	public void setHorario(LocalTime horario) {
		this.horario = horario;
	}
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
	public Sessao toSessao(SalaDao salaDao, FilmeDao filmeDao) {
		Integer salaId = this.getSalaId();
		Sala sala = salaDao.findOne(salaId);
		
		Integer filmeId = this.getFilmeId();
		Filme filme = filmeDao.findOne(filmeId);
		
		LocalTime horario = this.getHorario();
		Sessao sessao = new Sessao(sala, filme, horario);
		return sessao;
	}
}
