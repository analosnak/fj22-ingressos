package br.com.caelum.ingresso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.ingresso.dao.LugarDao;
import br.com.caelum.ingresso.dao.SessaoDao;
import br.com.caelum.ingresso.model.Carrinho;
import br.com.caelum.ingresso.model.Ingresso;
import br.com.caelum.ingresso.model.form.CarrinhoForm;

@Controller
public class CompraController {
    @Autowired
    private LugarDao lugarDao;
    @Autowired
    private SessaoDao sessaoDao;
    @Autowired
    private Carrinho carrinho;

    @PostMapping("/compra/ingressos")
    public ModelAndView pegaIngressos(CarrinhoForm carrinhoForm) {
        List<Ingresso> ingressos = carrinhoForm.pegaIngressos(sessaoDao, lugarDao);
        carrinho.adiciona(ingressos);

        ModelAndView modelAndView = new ModelAndView("compra/pagamento");
        modelAndView.addObject("carrinho", carrinho);

        return modelAndView;
    }
}
