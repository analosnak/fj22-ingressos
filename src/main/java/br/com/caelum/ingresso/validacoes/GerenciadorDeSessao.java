package br.com.caelum.ingresso.validacoes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Stream;

import br.com.caelum.ingresso.model.Sessao;

public class GerenciadorDeSessao {

	public boolean cabe(Sessao sessaoNova, List<Sessao> sessoesDaSala) {
		if (terminaAmanha(sessaoNova)) {
			return false;
		}
		
		return sessoesDaSala.stream().noneMatch(sessaoExistente -> temConflito(sessaoNova, sessaoExistente));

	}

	private boolean terminaAmanha(Sessao sessaoNova) {
		LocalDate hoje = LocalDate.now();
		LocalDateTime terminoSessaoNovaComDia = horarioTerminoSessaoComDia(sessaoNova);
		LocalTime ultimoHorario = LocalTime.MAX;
		LocalDateTime ultimoHorarioDoDia = LocalDateTime.of(hoje, ultimoHorario );
		if (terminoSessaoNovaComDia.isAfter(ultimoHorarioDoDia )) {
			return true;
		}
		
		return false;
	}

	private boolean temConflito(Sessao sessaoNova, Sessao sessaoExistente) {
		LocalTime inicioSessaoNova = sessaoNova.getHorario();
		LocalTime terminoSessaoNova = horarioTerminoSessao(sessaoNova);
		LocalTime inicioSessaoExistente = sessaoExistente.getHorario();
		LocalTime terminoSessaoExistente = horarioTerminoSessao(sessaoExistente);
		
		boolean sessaoNovaTerminaAntes = terminoSessaoNova.isBefore(inicioSessaoExistente);
		boolean sessaoNovaIniciaDepois = inicioSessaoNova.isAfter(terminoSessaoExistente);
		
		if (sessaoNovaTerminaAntes || sessaoNovaIniciaDepois) {
			return false;
		}
			
		return true;
	}

	private LocalTime horarioTerminoSessao(Sessao sessaoNova) {
		return sessaoNova.getHorario()
				.plus(sessaoNova.getFilme().getDuracao());
	}
	

	private LocalDateTime horarioTerminoSessaoComDia(Sessao sessaoNova) {
		LocalDate hoje = LocalDate.now();
		
		return sessaoNova.getHorario().atDate(hoje)
				.plus(sessaoNova.getFilme().getDuracao());
	}

}
