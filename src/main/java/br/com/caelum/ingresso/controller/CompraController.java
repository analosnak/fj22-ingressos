package br.com.caelum.ingresso.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.ingresso.dao.LugarDao;
import br.com.caelum.ingresso.dao.SessaoDao;
import br.com.caelum.ingresso.model.Carrinho;
import br.com.caelum.ingresso.model.Ingresso;
import br.com.caelum.ingresso.model.Lugar;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.form.CarrinhoForm;

@Controller
public class CompraController {
	@Autowired
	private SessaoDao sessaoDao;
	@Autowired
	private LugarDao lugarDao;
	@Autowired
	private Carrinho carrinho;
	
	@PostMapping("/compra/ingressos")
	public ModelAndView processaCompra(CarrinhoForm carrinhoForm) {
		ModelAndView modelAndView = new ModelAndView("redirect:/compra");
		
		List<Ingresso> ingressosCompletos = 
				carrinhoForm.preencheIngressos(sessaoDao, lugarDao);
		
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
}
