package br.com.caelum.ingresso.validacao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.caelum.ingresso.model.Sessao;

@Component
public class GerenciadorDeSessoes {
	public boolean cabe(Sessao sessaoNova, List<Sessao> sessoesDaSala) {
		if (terminaNoDiaSeguinte(sessaoNova)) {
			return false;
		}
		
		// sessaoNova não pode ter conflito com nenhuma sessaoExistente (da lista)
		
		// temConflito() não pode ser verdade pra nenhum elemento da lista
		return sessoesDaSala.stream()
				.noneMatch(   sessaoExistente -> temConflito(sessaoNova, sessaoExistente)  );
		
		
//		for (Sessao sessaoExistente : sessoesDaSala) {
//			if (temConflito(sessaoNova, sessaoExistente)) {
//				return false;
//			}
//		}
//	
//		return true;
	}

	
	
	
	
	
	
	
	
	
	
	private boolean terminaNoDiaSeguinte(Sessao sessaoNova) {
		LocalDate hoje = LocalDate.now();
		LocalDateTime inicioSessaoNova = sessaoNova.getHorario().atDate(hoje);
		LocalDateTime fimSessaoNova = inicioSessaoNova.plus(sessaoNova.getFilme().getDuracao());
		
		LocalTime ultimoHorarioDoDia = LocalTime.MAX;
		LocalDateTime ultimoHorarioDeHoje = LocalDateTime.of(hoje, ultimoHorarioDoDia);

		if (fimSessaoNova.isAfter(ultimoHorarioDeHoje)) {
			return true;
		}
		
		return false;
	}

	private boolean temConflito(Sessao sessaoNova, Sessao sessaoExistente) {
		LocalTime inicioSessaoNova = sessaoNova.getHorario();
		LocalTime fimSessaoNova = sessaoNova.getHorario().plus(sessaoNova.getFilme().getDuracao());
		LocalTime inicioSessaoExistente = sessaoExistente.getHorario();
		LocalTime fimSessaoExistente = sessaoExistente.getHorario().plus(sessaoExistente.getFilme().getDuracao());
		
		boolean sessaoNovaComecaDepois = inicioSessaoNova.isAfter(fimSessaoExistente);
		boolean sessaoNovaTerminaAntes = fimSessaoNova.isBefore(inicioSessaoExistente);
		
		if (sessaoNovaComecaDepois) {
			return false;
		}
		if (sessaoNovaTerminaAntes) {
			return false;
		}
		
		return true;
	}
}
