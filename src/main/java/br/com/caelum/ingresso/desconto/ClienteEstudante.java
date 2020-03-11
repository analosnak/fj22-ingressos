package br.com.caelum.ingresso.desconto;

import java.math.BigDecimal;

public class ClienteEstudante implements Cliente {

	@Override
	public BigDecimal descontaValor(BigDecimal precoOriginal) {
		return precoOriginal.divide(new BigDecimal("2.0"));
	}

}
