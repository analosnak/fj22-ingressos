package br.com.caelum.ingresso.model;

import java.math.BigDecimal;

import br.com.caelum.ingresso.model.desconto.Desconto;
import br.com.caelum.ingresso.model.desconto.DescontoEstudante;
import br.com.caelum.ingresso.model.desconto.DescontoIdoso;
import br.com.caelum.ingresso.model.desconto.SemDesconto;

public enum TipoDeIngresso {
    ESTUDANTE(new DescontoEstudante()), IDOSO(new DescontoIdoso()), INTEIRA(new SemDesconto());

    Desconto desconto;

    private TipoDeIngresso(Desconto desconto) {
        this.desconto = desconto;
    }

    public String getDescricao() {
        return desconto.getDescricao();
    }

    public BigDecimal calculaDesconto(BigDecimal preco) {
        return desconto.calculaDesconto(preco);
    }
}