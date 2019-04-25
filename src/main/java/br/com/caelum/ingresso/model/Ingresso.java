package br.com.caelum.ingresso.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Ingresso {
    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    private Sessao sessao;
    private BigDecimal preco;
    @ManyToOne
    private Lugar lugar;
    @Enumerated(EnumType.STRING)
    private TipoDeIngresso tipoDeIngresso;

    /**
     * @deprecated só pro hibernate
     */
    @Deprecated
    public Ingresso() {
    }

    public Ingresso(Sessao sessao, Lugar lugar, TipoDeIngresso tipoDeIngresso) {
        this.sessao = sessao;
        this.lugar = lugar;
        this.tipoDeIngresso = tipoDeIngresso;
        this.preco = tipoDeIngresso.calculaDesconto(sessao.getPreco());
    }

    public Sessao getSessao() {
        return sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Lugar getLugar() {
        return lugar;
    }

    public void setLugar(Lugar lugar) {
        this.lugar = lugar;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TipoDeIngresso getTipoDeIngresso() {
        return tipoDeIngresso;
    }

    public void setTipoDeIngresso(TipoDeIngresso tipoDeIngresso) {
        this.tipoDeIngresso = tipoDeIngresso;
    }

}
