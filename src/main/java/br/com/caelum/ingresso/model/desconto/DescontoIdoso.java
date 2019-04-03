package br.com.caelum.ingresso.model.desconto;

import java.math.BigDecimal;

public class DescontoIdoso implements Desconto {

	@Override
	public BigDecimal calculaDesconto(BigDecimal precoOriginal) {
		if (precoOriginal.subtract(new BigDecimal("10.0")).signum() == -1) {
			return new BigDecimal("0.0");
		}

		return precoOriginal.subtract(new BigDecimal("10.0"));
	}

	@Override
	public String getDescricao() {
		return "IDOSO";
	}

}
