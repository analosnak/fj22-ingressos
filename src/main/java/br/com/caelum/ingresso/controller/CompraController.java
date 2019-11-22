package br.com.caelum.ingresso.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
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
	private SessaoDao sessaoDao;
	@Autowired
	private LugarDao lugarDao;
	@Autowired
	private Carrinho carrinho;
	@Autowired
	private CompraDao compraDao;

	@PostMapping("/compra/ingressos")
	public ModelAndView processaCompra(CarrinhoForm carrinhoForm) {
		ModelAndView modelAndView = new ModelAndView("redirect:/compra");

		List<Ingresso> ingressosCompletos = carrinhoForm.preencheIngressos(sessaoDao, lugarDao);

		carrinho.adiciona(ingressosCompletos);

		return modelAndView;
	}

	@GetMapping("compra")
	public ModelAndView checkout() {
		ModelAndView modelAndView = new ModelAndView("compra/pagamento");

		System.out.println(carrinho.getIngressos().size());
		modelAndView.addObject("carrinho", carrinho);

		return modelAndView;
	}

	@Transactional
	@PostMapping("/compra/comprar")
	public ModelAndView fazCompra(@Valid Cartao cartao, BindingResult result) {
		ModelAndView modelAndView;

		if (cartao.isValido()) {
			modelAndView = new ModelAndView("redirect:/");
			// envia pra processa pagamento
			Compra compra = new Compra(carrinho);
			compraDao.salva(compra);
		} else {
			result.rejectValue("vencimento",null, "Seu cartão tá vencido!");
			modelAndView = new ModelAndView("redirect:/compra");
		}
		return modelAndView;
	}
}
