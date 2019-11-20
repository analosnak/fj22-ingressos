package br.com.caelum.ingresso.model.descontos;

import java.math.BigDecimal;

public class DescontoIdoso implements Descontao {

	public BigDecimal aplicaDesconto(BigDecimal preco) {
		return preco.subtract(new BigDecimal("5.0"));
	}
}
