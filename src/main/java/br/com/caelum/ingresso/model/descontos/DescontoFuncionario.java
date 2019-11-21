package br.com.caelum.ingresso.model.descontos;

import java.math.BigDecimal;

public class DescontoFuncionario implements Descontao {

	@Override
	public BigDecimal aplicaDesconto(BigDecimal preco) {
		return BigDecimal.ZERO;
	}

	@Override
	public String getDescricao() {
		return "FUNCIONARIO";
	}

}
