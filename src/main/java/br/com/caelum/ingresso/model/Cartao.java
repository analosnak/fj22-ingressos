package br.com.caelum.ingresso.model;

import java.time.YearMonth;

public class Cartao {
    private String cartaoDeCredito;
    private String cvv;
    private YearMonth validade;

    public String getCartaoDeCredito() {
        return cartaoDeCredito;
    }

    public void setCartaoDeCredito(String cartaoDeCredito) {
        this.cartaoDeCredito = cartaoDeCredito;
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
