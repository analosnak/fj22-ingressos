package br.com.caelum.ingresso.model.descontos;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Ingresso;
import br.com.caelum.ingresso.model.Lugar;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.TipoDeIngresso;

public class DescontoTest {

    @Test
    public void naoDeveConcederDescontoParaIngressoNormal() {

        Sala sala = new Sala("Eldorado - IMAX", new BigDecimal("20.5"));
        Filme filme = new Filme("Rogue One", Duration.ofMinutes(120),
                        "SCI-FI", new BigDecimal("12"));
        Sessao sessao = new Sessao(sala, filme, LocalTime.parse("10:00:00"));
        Lugar lugar = new Lugar("A", 1);
		Ingresso ingresso = new Ingresso(sessao, lugar , TipoDeIngresso.INTEIRA);

        BigDecimal precoEsperado = new BigDecimal("32.50");

        Assert.assertEquals(precoEsperado, ingresso.getPreco());

    }
    
    @Test
    public void deveConcederDescontoDe5ReaisParaIngressosDeIdosos() {

        Sala sala = new Sala("Eldorado - IMAX", new BigDecimal("20.5"));
        Filme filme = new Filme("Rogue One", Duration.ofMinutes(120),
                        "SCI-FI", new BigDecimal("12"));
        Sessao sessao = new Sessao(sala, filme, LocalTime.parse("10:00:00"));
        Lugar lugar = new Lugar("A", 1);

        Ingresso ingresso = new Ingresso(sessao, lugar, TipoDeIngresso.IDOSO);

        BigDecimal precoEsperado = new BigDecimal("27.50");

        Assert.assertEquals(precoEsperado, ingresso.getPreco());

    }

    @Test
    public void deveConcederDescontoDe50PorcentoParaIngressoDeEstudante() {

        Sala sala = new Sala("Eldorado - IMAX", new BigDecimal("20.5"));
        Filme filme = new Filme("Rogue One", Duration.ofMinutes(120),
                        "SCI-FI", new BigDecimal("12"));
        Sessao sessao = new Sessao(sala, filme, LocalTime.parse("10:00:00"));
        Lugar lugar = new Lugar("A", 1);
        Ingresso ingresso = new Ingresso(sessao, lugar, TipoDeIngresso.MEIA);

        BigDecimal precoEsperado = new BigDecimal("16.25");

        Assert.assertEquals(precoEsperado, ingresso.getPreco());

    }

}

