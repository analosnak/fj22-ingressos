package br.com.caelum.ingresso.validacao;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;

public class GerenciadorDeSessoesTest {
	@Test
	public void deveInserirSessaoNaListaVazia() {
		// prepara cenario
		Filme filme = new Filme("Filme 1", Duration.ofMinutes(120), "Comedio");
		Sala sala = new Sala("Sala 1");
		LocalTime horario = LocalTime.of(10, 0);
		Sessao sessaoNova = new Sessao(filme, sala, horario);

		List<Sessao> sessoes = Collections.emptyList();

		// testa a logica
		GerenciadorDeSessoes gerenciador = new GerenciadorDeSessoes();
		boolean coube = gerenciador.cabeNaSala(sessaoNova, sessoes);

		// verifica o resultado com o esperado
		Assert.assertTrue(coube);
	}
}
