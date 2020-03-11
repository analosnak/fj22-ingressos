package br.com.caelum.ingresso.desconto;

import java.math.BigDecimal;

public class SemDesconto implements Desconto {

	@Override
	public BigDecimal descontaValor(BigDecimal precoOriginal) {
		return precoOriginal;
	}

}
