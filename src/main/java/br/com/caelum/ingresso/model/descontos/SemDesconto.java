package br.com.caelum.ingresso.model.descontos;

import java.math.BigDecimal;

public class SemDesconto implements Descontao {

	@Override
	public BigDecimal aplicaDesconto(BigDecimal preco) {
		return preco;
	}

}
