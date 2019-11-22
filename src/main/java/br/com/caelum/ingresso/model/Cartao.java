package br.com.caelum.ingresso.model;

import java.time.YearMonth;

import javax.validation.constraints.NotNull;

public class Cartao {
	@NotNull
	private Integer cartaoDeCredito;
	@NotNull
	private Integer cvv;
	@NotNull
	private YearMonth vencimento;
	
	public Integer getCartaoDeCredito() {
		return cartaoDeCredito;
	}
	public void setCartaoDeCredito(Integer cartaoDeCredito) {
		this.cartaoDeCredito = cartaoDeCredito;
	}
	public Integer getCvv() {
		return cvv;
	}
	public void setCvv(Integer cvv) {
		this.cvv = cvv;
	}
	public YearMonth getVencimento() {
		return vencimento;
	}
	public void setVencimento(YearMonth vencimento) {
		this.vencimento = vencimento;
	}
	public boolean isValido() {
		return vencimento.isAfter(YearMonth.now());
	}
	
	
}
