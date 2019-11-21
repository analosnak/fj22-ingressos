package br.com.caelum.ingresso.model.descontos;

import java.math.BigDecimal;

public interface Descontao {

	public BigDecimal aplicaDesconto(BigDecimal preco);
	
	public String getDescricao();

}
