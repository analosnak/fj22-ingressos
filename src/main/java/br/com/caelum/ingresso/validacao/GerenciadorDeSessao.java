package br.com.caelum.ingresso.validacao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import br.com.caelum.ingresso.model.Sessao;

public class GerenciadorDeSessao {

	private List<Sessao> sessoesDaSala;

	public GerenciadorDeSessao(List<Sessao> sessoesDaSala) {
		this.sessoesDaSala = sessoesDaSala;
	}

	public boolean cabe(Sessao sessaoNova) {
		if (terminaNoDiaSeguinte(sessaoNova)) {
			return false;
		}
		
		return sessoesDaSala.stream().noneMatch(sessaoExist -> temConflito(sessaoNova, sessaoExist));
	}
	
	
	
	
	
	
	
	
	
	

	private boolean terminaNoDiaSeguinte(Sessao sessao) {
		LocalDate hoje = LocalDate.now();
		LocalDateTime horarioComDia = sessao.getHorario().atDate(hoje);
		
		LocalDateTime terminoSessaoComDia = 
				horarioComDia.plus(sessao.getFilme().getDuracao());
		LocalDateTime ultimoHorarioDeHoje =
				LocalDateTime.of(hoje, LocalTime.MAX);
		if (terminoSessaoComDia.isAfter(ultimoHorarioDeHoje)) {
			return true;
		}
		return false;
	}

	private boolean temConflito(Sessao sessaoNova, Sessao sessaoExist) {
		LocalTime inicioNova = sessaoNova.getHorario();
		LocalTime terminoNova = 
				sessaoNova.getHorario().plus(sessaoNova.getFilme().getDuracao());
		LocalTime inicioExist = sessaoExist.getHorario();
		LocalTime terminoExist = sessaoExist.getHorario().plus(sessaoExist.getFilme().getDuracao());
		
		boolean novaTerminaAntesInicioExist = terminoNova.isBefore(inicioExist);
		boolean novaComecaDepoisFimExist = inicioNova.isAfter(terminoExist);
		
		if (novaTerminaAntesInicioExist || novaComecaDepoisFimExist) {
			return false;
		}
		return true;
	}
}
