package br.com.caelum.ingresso.validacao;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;

public class ValidadorDeSessaoTest {
    private Sessao sessao;

    @Before
    public void preparaCenario() {
        Sala sala = new Sala("Sala 1");
        Filme filme = new Filme("Avengers", Duration.ofMinutes(120), "SCI-FI");
        LocalTime horario = LocalTime.of(10, 0);
        sessao = new Sessao(sala, filme, horario);
    }

    @Test
    public void deveCaberSeListaVazia() {
        // prepara situacao
        ArrayList<Sessao> listaVazia = new ArrayList<Sessao>();

        // executar logica
        ValidadorDeSessao validadorDeSessao = new ValidadorDeSessao();
        boolean resultado = validadorDeSessao.cabe(sessao, listaVazia);

        // verificar resultado
        Assert.assertTrue(resultado);
    }

}
