package br.com.caelum.ingresso.model.desconto;

import java.math.BigDecimal;

public class DescontoIdoso implements Desconto {
	public BigDecimal aplicaDesconto(BigDecimal precoOriginal) {
		return precoOriginal.multiply(new BigDecimal("0.5"));
	}

	@Override
	public String getDescricao() {
		return "IDOSO";
	}
}
