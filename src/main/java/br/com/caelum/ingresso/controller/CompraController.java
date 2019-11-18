package br.com.caelum.ingresso.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.ingresso.dao.LugarDao;
import br.com.caelum.ingresso.dao.SessaoDao;
import br.com.caelum.ingresso.model.Carrinho;
import br.com.caelum.ingresso.model.Ingresso;
import br.com.caelum.ingresso.model.form.IngressosForm;

@Controller
public class CompraController {

	@Autowired
	private Carrinho carrinho;
	@Autowired
	private SessaoDao sessaoDao;
	@Autowired
	private LugarDao lugarDao;

	@PostMapping("compra/ingressos")
	public ModelAndView finaliza(IngressosForm form) {
		ModelAndView mav = new ModelAndView("redirect:/compra");

		List<Ingresso> ingressosCompletos = form.pegaIngressosCompletos(lugarDao, sessaoDao);

		// jogar ingressos no carrinho
//		for (Ingresso ing : ingressosCompletos) {
//			carrinho.adiciona(ing);
//		}
		ingressosCompletos.forEach(carrinho::adiciona);

		return mav;
	}

	@GetMapping("compra")
	public ModelAndView telaPagamento() {
		ModelAndView mav = new ModelAndView("compra/pagamento");
		mav.addObject("carrinho", carrinho);

		return mav;
	}
}
