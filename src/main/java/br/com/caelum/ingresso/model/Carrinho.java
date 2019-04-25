package br.com.caelum.ingresso.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class Carrinho {
    private List<Ingresso> ingressos = new ArrayList<Ingresso>();

    public List<Ingresso> getIngressos() {
        return ingressos;
    }

    public void adiciona(List<Ingresso> ingressos) {
        this.ingressos.addAll(ingressos);
    }

    public BigDecimal getTotal() {
        return ingressos.stream().map(Ingresso::getPreco).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
    }
}
