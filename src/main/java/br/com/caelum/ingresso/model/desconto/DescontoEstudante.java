package br.com.caelum.ingresso.model.desconto;

import java.math.BigDecimal;

public class DescontoEstudante implements Desconto {
	public BigDecimal aplicaDesconto(BigDecimal precoOriginal) {
		return precoOriginal.multiply(new BigDecimal("0.5"));
	}
}
