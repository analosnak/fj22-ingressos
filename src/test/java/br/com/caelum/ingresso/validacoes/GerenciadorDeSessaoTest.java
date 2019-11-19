package br.com.caelum.ingresso.validacoes;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;

public class GerenciadorDeSessaoTest {
	
	private Sala sala;
	private Filme filme;
	private LocalTime horario;
	private Sessao sessao;
	
	@Before
	public void modeloDeCenario() {
		sala = new Sala("Sala 1");
		filme = new Filme("Joker", Duration.ofMinutes(120), "SCI-FI");
		horario = LocalTime.parse("20:00:00");
		sessao = new Sessao(sala, filme, horario);
	}

	@Test
	public void sessaoDeveCaberSeListaEstiverVazia() {
		// definir cenário
		List<Sessao> sessoesDaSala = Collections.emptyList();
		
		// executa lógica
		GerenciadorDeSessao gds = new GerenciadorDeSessao();
		boolean coube = gds.cabe(sessao, sessoesDaSala);
		
		// verificar se resultado é o esperado
		Assert.assertTrue(coube);
	}
	
	@Test
	public void sessaoNaoDeveCaberSeComecarDentroDeSessaoExistente() {
		// definir cenário
		LocalTime horarioDasDezenove = LocalTime.parse("19:00:00");
		Sessao sessaoExistente = new Sessao(sala, filme, horarioDasDezenove = LocalTime.parse("19:00:00"));
		List<Sessao> sessoesDaSala = Arrays.asList(sessaoExistente);
		
		// executa lógica
		GerenciadorDeSessao gds = new GerenciadorDeSessao();
		boolean coube = gds.cabe(sessao, sessoesDaSala);
		
		// verificar se resultado é o esperado
		Assert.assertFalse(coube);
	}
	
	
	
	
	
}
