package br.com.caelum.ingresso.desconto;

import java.math.BigDecimal;

public interface Cliente {
	
	public BigDecimal descontaValor(BigDecimal precoOriginal);
}
