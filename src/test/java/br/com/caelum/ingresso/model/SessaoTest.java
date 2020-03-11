package br.com.caelum.ingresso.model;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

import org.junit.Assert;
import org.junit.Test;

public class SessaoTest {

	@Test
	public void precoDaSessaoDeveSomarPrecoDoFilmeEDaSala() {
		Sala sala = new Sala("Sala 1", new BigDecimal("30.0"));
		Filme filme = new Filme("Avengers", Duration.ofMinutes(120), "SCI-FI", BigDecimal.TEN);
		
		Sessao sessao = new Sessao(sala, filme, LocalTime.parse("10:00:00"));
		
		Assert.assertEquals(new BigDecimal("40.00"), sessao.getPreco());
	}
}
