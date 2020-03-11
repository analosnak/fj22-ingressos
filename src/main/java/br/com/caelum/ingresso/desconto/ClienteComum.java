package br.com.caelum.ingresso.desconto;

import java.math.BigDecimal;

public class ClienteComum implements Cliente {

	@Override
	public BigDecimal descontaValor(BigDecimal precoOriginal) {
		return precoOriginal;
	}

}
