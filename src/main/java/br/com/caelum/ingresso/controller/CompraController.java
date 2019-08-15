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
import br.com.caelum.ingresso.model.Ingresso;
import br.com.caelum.ingresso.model.form.CarrinhoForm;

@Controller
public class CompraController {
	@Autowired
	private Carrinho carrinho;
	@Autowired
	private SessaoDao sessaoDao;
	@Autowired
	private LugarDao lugarDao;
	@Autowired
	private CompraDao compraDao;
	
	
	@PostMapping("/compra/ingressos")
	public ModelAndView processaCompra(CarrinhoForm carrinhoForm) {
		ModelAndView modelAndView = new ModelAndView("redirect:/compra");
		
		// pegar os ingressos preenchidos do formulario
		List<Ingresso> ingressosCompletos =
				carrinhoForm.toIngressosCompletos(sessaoDao, lugarDao);
		
		// armazenar ingressos no carrinho
		ingressosCompletos.forEach(carrinho::adiciona);
				
		return modelAndView;
	}
	
	@GetMapping("/compra")
	public ModelAndView checkout(Cartao cartao) {
		ModelAndView modelAndView = new ModelAndView("compra/pagamento");
		
		modelAndView.addObject("carrinho", carrinho);

		return modelAndView;
	}
	
	@PostMapping("/compra/comprar")
	@Transactional
	public ModelAndView comprar(@Valid  Cartao cartao, BindingResult result){
	    ModelAndView modelAndView = new ModelAndView("redirect:/");

	    if (cartao.isValido()){
	        compraDao.save(carrinho.toCompra());
	        this.carrinho.limpa();
	    }else{
	        result.rejectValue("vencimento", null, "Vencimento inválido");
	        return checkout(cartao);
	    }


	    return modelAndView;
	}

}
