package br.com.caelum.ingresso.model;

import java.math.BigDecimal;

import br.com.caelum.ingresso.desconto.Desconto;
import br.com.caelum.ingresso.desconto.DescontoEstudante;
import br.com.caelum.ingresso.desconto.DescontoIdoso;
import br.com.caelum.ingresso.desconto.SemDesconto;

public enum TipoDeIngresso {

	INTEIRA(new SemDesconto()),
	MEIA(new DescontoEstudante()),
	IDOSO(new DescontoIdoso());
	
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
