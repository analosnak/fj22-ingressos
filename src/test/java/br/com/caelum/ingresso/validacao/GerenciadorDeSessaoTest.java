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

public class GerenciadorDeSessaoTest {
	
	private Sala sala;
	private Filme filme;
	private LocalTime horario;
	private Sessao sessaoNova;
	
	private Filme rogueOne;
	private Sala sala3D;
	private Sessao sessaoDasDez;
	private Sessao sessaoDasTreze;
	private Sessao sessaoDasDezoito;

	@Before
	public void preparaSessoes(){

		sala = new Sala("Sala 1", BigDecimal.ZERO);
		filme = new Filme("Filme 1", Duration.ofMinutes(120), "SCI-FI", BigDecimal.ZERO);
		horario = LocalTime.parse("10:00:00");
		sessaoNova = new Sessao(sala, filme, horario);
		
		this.rogueOne = new Filme("Rogue One", Duration.ofMinutes(120), "SCI-FI", BigDecimal.ZERO);
		this.sala3D = new Sala("Sala 3D", BigDecimal.ZERO);

		this.sessaoDasDez = new Sessao(sala3D, rogueOne, LocalTime.parse("10:00:00"));
		this.sessaoDasTreze = new Sessao(sala3D, rogueOne, LocalTime.parse("13:00:00"));
		this.sessaoDasDezoito = new Sessao(sala3D, rogueOne, LocalTime.parse("18:00:00"));
	}

	@Test
	public void garanteQueNaoDevePermitirSessaoNoMesmoHorario() {

		List<Sessao> sessoes = Arrays.asList(sessaoDasDez);
		GerenciadorDeSessao gerenciador = new GerenciadorDeSessao();
		Assert.assertFalse(gerenciador.cabe(sessaoDasDez, sessoes));
	}

	@Test
	public void garanteQueNaoDevePermitirSessoesTerminandoDentroDoHorarioDeUmaSessaoJaExistente() {
		List<Sessao> sessoes = Arrays.asList(sessaoDasDez);
		Sessao sessao = new Sessao(sala3D, rogueOne, sessaoDasDez.getHorario().minusHours(1));
		GerenciadorDeSessao gerenciador = new GerenciadorDeSessao();
		Assert.assertFalse(gerenciador.cabe(sessao, sessoes));

	}

	@Test
	public void garanteQueNaoDevePermitirSessoesIniciandoDentroDoHorarioDeUmaSessaoJaExistente() {
		List<Sessao> sessoesDaSala = Arrays.asList(sessaoDasDez);
		GerenciadorDeSessao gerenciador = new GerenciadorDeSessao();
		Sessao sessao = new Sessao(sala3D, rogueOne, sessaoDasDez.getHorario().plusHours(1));
		Assert.assertFalse(gerenciador.cabe(sessao, sessoesDaSala));

	}

	@Test
	public void garanteQueDevePermitirUmaInsercaoEntreDoisFilmes(){
		List<Sessao> sessoes = Arrays.asList(sessaoDasDez, sessaoDasDezoito);
		GerenciadorDeSessao gerenciador = new GerenciadorDeSessao();
		Assert.assertTrue(gerenciador.cabe(sessaoDasTreze, sessoes));

	}

	@Test
	public void garanteQueDeveNaoPermitirUmaSessaoQueTerminaNoProximoDia() {
		List<Sessao> sessoes = Collections.emptyList();
		GerenciadorDeSessao gerenciador = new GerenciadorDeSessao();
		Sessao sessaoQueTerminaAmanha = new Sessao(sala3D,  rogueOne, LocalTime.parse("23:00:00"));
		Assert.assertFalse(gerenciador.cabe(sessaoQueTerminaAmanha, sessoes));

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
