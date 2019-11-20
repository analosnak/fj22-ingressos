package br.com.caelum.ingresso.model.descontos;

import java.math.BigDecimal;

public class DescontoMeia implements Descontao {

	public BigDecimal aplicaDesconto(BigDecimal preco) {
		return preco.divide(new BigDecimal("2.0"));
	}
}
