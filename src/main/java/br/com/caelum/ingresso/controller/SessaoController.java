package br.com.caelum.ingresso.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.ingresso.dao.FilmeDao;
import br.com.caelum.ingresso.dao.SalaDao;
import br.com.caelum.ingresso.dao.SessaoDao;
import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.form.SessaoForm;

@Controller
public class SessaoController {
	
	@Autowired
	private FilmeDao filmeDao;
	@Autowired
	private SalaDao salaDao;
	@Autowired
	private SessaoDao sessaoDao;
	
	@GetMapping("admin/sessao")
	public ModelAndView formulario(
			@RequestParam("salaId") Integer id, SessaoForm sessaoForm) {
		ModelAndView mav = new ModelAndView("sessao/sessao");
		
		Sala sala = this.salaDao.findOne(id);
		List<Filme> filmes = this.filmeDao.findAll();
		mav.addObject("filmes", filmes);
		mav.addObject("sala", sala);
		mav.addObject("form", sessaoForm);
		
		return mav;
	}
	
	
	
	@Transactional
	@PostMapping("admin/sessao")
	public ModelAndView salva(@Valid SessaoForm sessaoForm,
								BindingResult result) {
		if (result.hasErrors()) {
			return formulario(sessaoForm.getSalaId(), sessaoForm);
		}
		
		Sessao sessao = 
				sessaoForm.criaSessao(salaDao, filmeDao);
		
		sessaoDao.salva(sessao);
		
		return new ModelAndView("redirect:/admin/sala/"+
		sessaoForm.getSalaId() +"/sessoes/");
	}
	
	
	
	
}
