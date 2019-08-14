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

public class GerenciadorDeSessaoTest {
	
	private Filme filme;
	private Sala sala;
	private LocalTime horario;
	private Sessao sessao;

	@Before
	public void preparaCenario() {
		filme = new Filme("Rei Leao", Duration.ofMinutes(60), "SCI-FI", BigDecimal.ZERO);
		sala = new Sala("Sala 1", BigDecimal.ZERO);
		horario = LocalTime.parse("11:00:00");
		sessao = new Sessao(horario, sala, filme);
	}
	
	@Test
	public void sessaoDeveCaberSeListaEstiverVazia() {
		//prepara cenario
		List<Sessao> sessoes = Collections.emptyList();	
		
		// chama a lógica
		GerenciadorDeSessao gerenciador = new GerenciadorDeSessao(sessoes);
		boolean resultado = gerenciador.cabe(sessao);
		
		// checar se resultado é o esperado
		Assert.assertTrue(resultado);
	}

}
