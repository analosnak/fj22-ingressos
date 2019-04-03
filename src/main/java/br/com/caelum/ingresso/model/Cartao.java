package br.com.caelum.ingresso.model;

import java.time.YearMonth;

import javax.validation.constraints.NotNull;

public class Cartao {
	@NotNull
	private String numero;
	@NotNull
	private String cvv;
	@NotNull
	private YearMonth validade;

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public YearMonth getValidade() {
		return validade;
	}

	public void setValidade(YearMonth validade) {
		this.validade = validade;
	}

	public boolean isValido() {
		return validade.isAfter(YearMonth.now());
	}

}
