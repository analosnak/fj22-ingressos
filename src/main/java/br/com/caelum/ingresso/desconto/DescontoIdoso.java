package br.com.caelum.ingresso.desconto;

import java.math.BigDecimal;

public class DescontoIdoso implements Desconto{

	@Override
	public BigDecimal descontaValor(BigDecimal precoOriginal) {
		return precoOriginal.subtract(new BigDecimal("10.0"));
	}

}
