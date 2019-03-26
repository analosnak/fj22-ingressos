package br.com.caelum.ingresso.validacao;

import java.util.List;

import br.com.caelum.ingresso.model.Sessao;

public class GerenciadorDeSessoes {

	public boolean cabeNaSala(Sessao sessaNova, List<Sessao> sessoes) {

		return sessoes.stream().noneMatch(sessaoExistente -> temConflito(sessaNova, sessaoExistente));

		// for (Sessao sessaoExistente : sessoes) {
		// if (temConflito(sessaNova, sessaoExistente)) {
		// return false;
		// }
		// }
		// return true;
	}

	private boolean temConflito(Sessao sessaoNova, Sessao sessaoExistente) {
		boolean sessaoNovaTerminaAntes = sessaoNova.getHorarioTermino().isBefore(sessaoExistente.getHorario());
		boolean sessaoNovaComecaDepois = sessaoNova.getHorario().isAfter(sessaoExistente.getHorarioTermino());

		if (sessaoNovaTerminaAntes || sessaoNovaComecaDepois) {
			return false; // nao tem conflito
		}
		return true; // tem conflito
	}

}
