package br.com.caelum.ingresso.model.form;

import java.time.LocalTime;

public class SessaoForm {
	private Integer salaId;
	private LocalTime horario;
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
	
}
