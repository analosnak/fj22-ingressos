package br.com.caelum.ingresso.validacao;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;

public class GerenciadorDeSessoesTest {
	private Filme filme;
	private Sala sala;
	private LocalTime horario;

	@Before
	public void preparaCenario() {
		filme = new Filme("Filme 1", Duration.ofMinutes(120), "Comedio", BigDecimal.ONE);
		sala = new Sala("Sala 1", BigDecimal.ONE);
		horario = LocalTime.of(10, 0);
	}

	@Test
	public void deveInserirSessaoNaListaVazia() {

		Sessao sessaoNova = new Sessao(filme, sala, horario);

		List<Sessao> sessoes = Collections.emptyList();

		// testa a logica
		GerenciadorDeSessoes gerenciador = new GerenciadorDeSessoes();
		boolean coube = gerenciador.cabeNaSala(sessaoNova, sessoes);

		// verifica o resultado com o esperado
		Assert.assertTrue(coube);
	}
}
