package br.com.caelum.ingresso.teste;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.validacao.GerenciadorDeSessoes;

public class TestaGerenciadorDeSessoes {
	public static void main(String[] args) {
		// preparação do cenário
		List<Sessao> salaVazia = Collections.emptyList();
		
		GerenciadorDeSessoes gds = 
				new GerenciadorDeSessoes(salaVazia);
		
		Sala sala = new Sala("Sala 1");
		Filme filme = new Filme("MIB", Duration.ofMinutes(120), "SCI_FI");
		LocalTime horario = LocalTime.parse("18:00:00");
		Sessao sessaoNova = new Sessao(sala, filme, horario);
		
		// lógica pra testar
		boolean coube = gds.cabe(sessaoNova);
		
		// verificação
		System.out.println(coube);
		
		
	}
}
