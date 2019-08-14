package br.com.caelum.ingresso.desconto;

import java.math.BigDecimal;

public interface Desconto {

	public BigDecimal aplicaDesconto(BigDecimal preco);

}
