package br.com.caelum.ingresso.model.desconto;

import java.math.BigDecimal;

public class DescontoParaBancos implements Desconto {

	@Override
	public BigDecimal aplicaDesconto(BigDecimal precoOriginal) {
		return precoOriginal.multiply(new BigDecimal("0.7"));
	}

	@Override
	public String getDescricao() {
		return "BANCOS";
	}

}
