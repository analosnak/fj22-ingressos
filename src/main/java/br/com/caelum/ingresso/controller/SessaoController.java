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
import br.com.caelum.ingresso.validacoes.GerenciadorDeSessao;

@Controller
public class SessaoController {
	@Autowired
	private FilmeDao filmeDao;
	@Autowired
	private SalaDao salaDao;
	@Autowired
	private SessaoDao sessaoDao;
	
	@GetMapping("admin/sessao")
	public ModelAndView form(@RequestParam("salaId") Integer idSala,
								SessaoForm sessaoForm) {
		ModelAndView modelAndView = new ModelAndView("sessao/sessao");

		Sala sala = salaDao.findOne(idSala);
		modelAndView.addObject("sala", sala);
		
		List<Filme> filmes = filmeDao.findAll();
		modelAndView.addObject("filmes", filmes);
		
		modelAndView.addObject("form", sessaoForm);

		return modelAndView;
	}
	
	@Transactional
	@PostMapping("admin/sessao")
	public ModelAndView salva(@Valid SessaoForm sessaoForm,
								BindingResult result) {
		
		if (result.hasErrors()) {
			return form(sessaoForm.getSalaId(), sessaoForm);
		}
				
		// preencher a sessao
		Sessao sessao = sessaoForm.toSessao(salaDao, filmeDao);
		
		// validar horario da sessao
		List<Sessao> sessoesDaSala = sessaoDao.buscaSessoesDaSala(sessao.getSala());
		
		GerenciadorDeSessao gds = new GerenciadorDeSessao();
		if (! gds.cabe(sessao, sessoesDaSala)) {
			return form(sessaoForm.getSalaId(), new SessaoForm());
		}
		
		// chamar sessaoDao pra salvar sessao no BD
		sessaoDao.salva(sessao);
		
		ModelAndView modelAndView = 
				new ModelAndView("redirect:/admin/sala/"+sessaoForm.getSalaId()+"/sessoes/");

		return modelAndView;
	}
	
	
	
	

}
