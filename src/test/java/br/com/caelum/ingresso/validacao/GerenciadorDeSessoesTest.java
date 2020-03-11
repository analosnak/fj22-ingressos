package br.com.caelum.ingresso.validacao;

import java.math.BigDecimal;
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
	private LocalTime horario;
	private Sessao sessaoNova;

	@Before
	public void preparaCenario() {
		sala = new Sala("Sala 1", BigDecimal.TEN);
		filme = new Filme("Avengers", Duration.ofMinutes(120), "SCI-FI", BigDecimal.TEN);
		horario = LocalTime.parse("10:00:00");
		sessaoNova = new Sessao(sala, filme, horario);
	}
	
	@Test
	public void deveCaberSeSalaEstiverVazia() {
		// prepara cenário
		List<Sessao> sessoesDaSala = Collections.emptyList();
		
		// executa lógica
		GerenciadorDeSessoes gds = new GerenciadorDeSessoes();
		boolean coube = gds.cabe(sessaoNova, sessoesDaSala);
		
		// verifica resultado com o esperado
		Assert.assertTrue(coube);
	}
	
	@Test
	public void naoDeveCaberSeSessaoNovaComecaDentroDaExistente() {
		// prepara cenário
		LocalTime horarioDasNove = LocalTime.parse("09:00:00");
		Sessao sessaoExistente = new Sessao(sala, filme, horarioDasNove );
		List<Sessao> sessoesDaSala = Arrays.asList(sessaoExistente);
		
		//executa lógica
		GerenciadorDeSessoes gds = new GerenciadorDeSessoes();
		boolean coube = gds.cabe(sessaoNova, sessoesDaSala);
		
		// verifica resultado
		Assert.assertFalse(coube);
	}
	

}
