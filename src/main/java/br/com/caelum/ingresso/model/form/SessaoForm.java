package br.com.caelum.ingresso.model.form;

import java.time.LocalTime;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.caelum.ingresso.dao.FilmeDao;
import br.com.caelum.ingresso.dao.SalaDao;
import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;

public class SessaoForm {
	@NotNull
	private LocalTime horario;
	@NotNull
	private Integer salaId;
	@NotNull
	private Integer filmeId;
	
	public Sessao criaSessao(SalaDao salaDao, FilmeDao filmeDao) {	
		Sala sala = salaDao.findOne(this.getSalaId());
		
		Filme filme = filmeDao.findOne(this.getFilmeId());
		
		Sessao sessao = new Sessao(sala, filme, horario);
		sessao.setFilme(filme);
		
		return sessao;
	}
	
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
}
