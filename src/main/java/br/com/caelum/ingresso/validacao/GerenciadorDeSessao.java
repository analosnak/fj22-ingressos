package br.com.caelum.ingresso.validacao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import br.com.caelum.ingresso.model.Sessao;

@Component
public class GerenciadorDeSessao {
	public boolean cabe(Sessao sessaoNova, 
			List<Sessao> sessoes) {
		
		if (terminaNoDiaSeguinte(sessaoNova)) {
			return false;
		}
		
		return sessoes.stream().noneMatch(sessaoExistente ->
				horarioIsConflitante(sessaoExistente, sessaoNova));
		
	}

	
	
	
	private boolean terminaNoDiaSeguinte(Sessao sessaoNova) {
		LocalTime horarioTermino = calculaTerminoSessao(sessaoNova);
		LocalDate hoje = LocalDate.now();
		LocalDateTime horarioTerminoDoDia = horarioTermino.atDate(hoje);
		LocalDateTime ultimoInstanteDoDia = LocalDateTime.of(hoje, LocalTime.MAX);
		
		if (horarioTerminoDoDia.isAfter(ultimoInstanteDoDia)) {
			return true;
		}
		return false;
	}




	
	
	private boolean horarioIsConflitante(Sessao sessaoExistente, Sessao sessaoNova) {
		LocalTime terminoSessaoNova =
				calculaTerminoSessao(sessaoNova);
		LocalTime terminoSessaoExistente =
				calculaTerminoSessao(sessaoExistente);
		
		LocalTime inicioSessaoNova = sessaoNova.getHorario();
		LocalTime inicioSessaoExistente = sessaoExistente.getHorario();
		
		boolean sessaoNovaTerminaAntes = 
				terminoSessaoNova.isBefore(inicioSessaoExistente);
		boolean sessaoNovaComecaDepois = 
				inicioSessaoNova.isAfter(terminoSessaoExistente);
		
		
		if (sessaoNovaTerminaAntes || sessaoNovaComecaDepois) {
			return false;
		}
		
		return true;
	}




	private LocalTime calculaTerminoSessao(Sessao sessao) {
		return sessao.getHorario().plus(
				sessao.getFilme().getDuracao());
	}
	
	
	
	
	
	
}
