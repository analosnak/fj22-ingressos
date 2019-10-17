package br.com.caelum.ingresso.controller;

import java.math.BigDecimal;

import br.com.caelum.ingresso.model.desconto.Desconto;
import br.com.caelum.ingresso.model.desconto.DescontoEstudante;
import br.com.caelum.ingresso.model.desconto.DescontoIdoso;
import br.com.caelum.ingresso.model.desconto.DescontoParaBancos;
import br.com.caelum.ingresso.model.desconto.SemDesconto;

public enum TipoDeIngresso {
	INTEIRA(new SemDesconto()), 
	ESTUDANTE(new DescontoEstudante()),
	IDOSO(new DescontoIdoso()),
	BANCO(new DescontoParaBancos());
	
	private Desconto desconto;

	private TipoDeIngresso(Desconto desconto) {
		this.desconto = desconto;
	}
	
	public String getDescricao() {
		return desconto.getDescricao();
	}

	public BigDecimal aplicaDesconto(BigDecimal preco) {
		return desconto.aplicaDesconto(preco);
	}
}
