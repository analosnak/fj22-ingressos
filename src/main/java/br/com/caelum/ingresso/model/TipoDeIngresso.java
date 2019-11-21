package br.com.caelum.ingresso.model;

import java.math.BigDecimal;

import br.com.caelum.ingresso.model.descontos.Descontao;
import br.com.caelum.ingresso.model.descontos.DescontoFuncionario;
import br.com.caelum.ingresso.model.descontos.DescontoIdoso;
import br.com.caelum.ingresso.model.descontos.DescontoMeia;
import br.com.caelum.ingresso.model.descontos.SemDesconto;

public enum TipoDeIngresso {
	INTEIRA(new SemDesconto()), 
	MEIA(new DescontoMeia()), 
	IDOSO(new DescontoIdoso()),
	FUNCIONARIO(new DescontoFuncionario());
	
	private Descontao desconto;
	
	private TipoDeIngresso(Descontao desconto) {
		this.desconto = desconto;
	}

	public String getDescricao() {
		return desconto.getDescricao();
	}

	public BigDecimal aplicaDesconto(BigDecimal preco) {
		return desconto.aplicaDesconto(preco);
	}
	
}
