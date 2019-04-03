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
	private CompraDao compraDao;
	@Autowired
	private Carrinho carrinho;

	@PostMapping("compra/ingressos")
	public ModelAndView processaCompra(CarrinhoForm carrinhoForm) {
		ModelAndView modelAndView = new ModelAndView("redirect:/compra");

		List<Ingresso> completos = carrinhoForm.toIngressos(sessaoDao, lugarDao);

		completos.forEach(carrinho::adiciona);

		return modelAndView;
	}

	@GetMapping("compra")
	public ModelAndView compra() {
		ModelAndView modelAndView = new ModelAndView("compra/pagamento");
		modelAndView.addObject("carrinho", carrinho);

		return modelAndView;
	}

	@Transactional
	@PostMapping("compra/comprar")
	public ModelAndView paga(@Valid Cartao cartao, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView("redirect:/");

		Compra compra = carrinho.toCompra();

		if (cartao.isValido()) {

			// tira dinheiro (chama outro serviço)

			compraDao.salva(compra);
			carrinho.limpa();

			// mostra mensagem sucesso
		}
		return modelAndView;
	}
}
