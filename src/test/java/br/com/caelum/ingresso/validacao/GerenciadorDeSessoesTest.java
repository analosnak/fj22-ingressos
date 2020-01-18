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

public class GerenciadorDeSessoesTest {
	
	private Sala sala;
	private Filme filme;
	private LocalTime horarioDasDezoito;
	private Sessao sessaoNova;

	@Before
	public void preparaCenario() {
		sala = new Sala("Sala 1");
		filme = new Filme("MIB", Duration.ofMinutes(120), "SCI_FI");
		horarioDasDezoito = LocalTime.parse("18:00:00");
		sessaoNova = new Sessao(sala, filme, horarioDasDezoito);

	}

	@Test
	public void deveCaberSeSalaEstiverVazia() {
		// preparação do cenário
		List<Sessao> salaVazia = Collections.emptyList();
		GerenciadorDeSessoes gds = new GerenciadorDeSessoes(salaVazia);

		// lógica pra testar
		boolean coube = gds.cabe(sessaoNova);

		// verificação
		Assert.assertTrue(coube);
	}
	
	@Test
	public void deveCaberSeSessaoNovaTerminaAntes() {
		// prepara cenario
		LocalTime horario = LocalTime.parse("21:00:00");
		Sessao sessaoExistente = new Sessao(sala, filme, horario);
		List<Sessao> sessoes = Arrays.asList(sessaoExistente);
				
		GerenciadorDeSessoes gds = new GerenciadorDeSessoes(sessoes);
		Assert.assertTrue( gds.cabe(sessaoNova));
		
	}
}
