package br.com.caelum.ingresso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.ingresso.dao.CompraDao;
import br.com.caelum.ingresso.dao.LugarDao;
import br.com.caelum.ingresso.dao.SessaoDao;
import br.com.caelum.ingresso.model.Carrinho;
import br.com.caelum.ingresso.model.Cartao;
import br.com.caelum.ingresso.model.Compra;
import br.com.caelum.ingresso.model.Ingresso;
import br.com.caelum.ingresso.model.form.CarrinhoForm;

@Controller
public class CompraController {
    @Autowired
    private LugarDao lugarDao;
    @Autowired
    private SessaoDao sessaoDao;
    @Autowired
    private CompraDao compraDao;
    @Autowired
    private Carrinho carrinho;

    @PostMapping("/compra/ingressos")
    public ModelAndView pegaIngressos(CarrinhoForm carrinhoForm) {
        List<Ingresso> ingressos = carrinhoForm.pegaIngressos(sessaoDao, lugarDao);
        carrinho.adiciona(ingressos);

        return formPagamento();
    }

    @GetMapping("compra")
    public ModelAndView formPagamento() {
        ModelAndView modelAndView = new ModelAndView("compra/pagamento");
        modelAndView.addObject("carrinho", carrinho);

        return modelAndView;
    }

    @PostMapping("/compra/comprar")
    @Transactional
    public ModelAndView processaCompra(Cartao cartao) {
        ModelAndView modelAndView = new ModelAndView("redirect:/");

        // pega dados usuario(não faremos agora)
        // pega dados carrinho (é atributo da classe)

        if (cartao.isValido()) {
            System.out.println("cartão válido!!!");
            compraDao.save(new Compra(carrinho.getIngressos()));
            carrinho.limpa();
            // mensagem pro usuário
        }
        return modelAndView;
    }
}
