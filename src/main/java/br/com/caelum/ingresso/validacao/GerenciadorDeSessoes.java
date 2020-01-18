package br.com.caelum.ingresso.validacao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Stream;

import br.com.caelum.ingresso.model.Sessao;

public class GerenciadorDeSessoes {

	private List<Sessao> sessoes;

	public GerenciadorDeSessoes(List<Sessao> sessoes) {
		this.sessoes = sessoes;
	}
	
	public boolean cabe(Sessao sessaoNova) {
		if (terminaAmanha(sessaoNova)) {
			return false;
		}
		
		return sessoes.stream()
				.noneMatch(sessaoExistente -> 
				isConflitante(sessaoNova, sessaoExistente));
		
//		for (Sessao sessaoExistente : sessoes) {
//			if (isConflitante(sessaoNova, sessaoExistente)) {
//				return false;
//			}
//		}
//		return true;
	}
	
	
	
	

	private boolean terminaAmanha(Sessao sessaoNova) {
		LocalDate hoje = LocalDate.now();
		LocalDateTime horarioInicioComDia = sessaoNova.getHorario().atDate(hoje);
		LocalDateTime horarioTerminoComDia = 
				horarioInicioComDia.plus(sessaoNova.getFilme().getDuracao());
		
		LocalDateTime ultimoHorarioDeHoje = LocalDateTime.of(hoje, LocalTime.MAX);
		
		if (horarioTerminoComDia.isBefore(ultimoHorarioDeHoje)) {
			return false;			
		}
		
		return true;
	}

	private boolean isConflitante(Sessao sessaoNova, Sessao sessaoExistente) {
		LocalTime inicioSessaoNova = sessaoNova.getHorario();
		LocalTime terminoSessaoNova = 
				sessaoNova.getHorario().plus(sessaoNova.getFilme().getDuracao());
		LocalTime inicioSessaoExistente = sessaoExistente.getHorario();
		LocalTime terminoSessaoExistente = 
				sessaoExistente.getHorario().plus(sessaoExistente.getFilme().getDuracao());
		
		boolean terminaAntes = 
				terminoSessaoNova.isBefore(inicioSessaoExistente);
		
		boolean comecaDepois = 
				inicioSessaoNova.isAfter(terminoSessaoExistente);
		
		if (terminaAntes || comecaDepois) {
			return false;
		}
		return true;
	}
}
