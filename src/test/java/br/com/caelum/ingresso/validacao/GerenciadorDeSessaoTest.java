package br.com.caelum.ingresso.validacao;

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
	private Sessao sessaoNova;
	
	@Before
	public void preparaCenario() {
		sala = new Sala("Sala 1");
		filme = new Filme("Filme 1", Duration.ofMinutes(120), "SCI-FI");
		horario = LocalTime.parse("10:00:00");
		sessaoNova = new Sessao(sala, filme, horario);
	}
	
	@Test
	public void deveCaberSeListaEstiverVazia() {
		
		GerenciadorDeSessao gerenciador = new GerenciadorDeSessao();
		
		//prepara cenário

		List<Sessao> sessoes = Collections.emptyList();
		
		// logica a testar
		boolean coube = gerenciador.cabe(sessaoNova, sessoes);
		
		// checa resultado da lógica
		Assert.assertTrue(coube);
	}
}
