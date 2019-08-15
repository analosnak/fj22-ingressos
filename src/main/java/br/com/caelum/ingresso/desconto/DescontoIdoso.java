package br.com.caelum.ingresso.desconto;

import java.math.BigDecimal;

public class DescontoIdoso implements Desconto {
	@Override
	public BigDecimal aplicaDesconto(BigDecimal precoOriginal) {
		return precoOriginal.subtract(new BigDecimal("5.0"));
	}

	@Override
	public String getDescricao() {
		return "IDOSO";
	}
}
