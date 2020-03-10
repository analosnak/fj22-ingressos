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
import br.com.caelum.ingresso.validacao.GerenciadorDeSessoes;

@Controller
public class SessaoController {
	@Autowired
	private FilmeDao filmeDao;
	@Autowired
	private SalaDao salaDao;
	@Autowired
	private SessaoDao sessaoDao;
	@Autowired
	private GerenciadorDeSessoes gds;

	@GetMapping("admin/sessao")
	public ModelAndView form(@RequestParam("salaId") Integer idSala, SessaoForm sessaoForm) {
		ModelAndView modelAndView = new ModelAndView("sessao/sessao");
		
		//disponibilizar sala
		Sala sala = salaDao.findOne(idSala);
		modelAndView.addObject("sala", sala);
		
		//disponibilizar filmes
		List<Filme> filmes = filmeDao.findAll();
		modelAndView.addObject("filmes", filmes);
		
		// disponibilizar dados do form
		modelAndView.addObject("form", sessaoForm);
		
		return modelAndView;
	}
	
	@Transactional
	@PostMapping("admin/sessao")
	public ModelAndView salva(@Valid SessaoForm sessaoForm, BindingResult result) {
		if(result.hasErrors()) {
			return form(sessaoForm.getSalaId(), sessaoForm);
		}
		
		// pegar dados da requisição e popular sessao
		Sessao sessao = sessaoForm.toSessao(salaDao, filmeDao);
		
		// validar horario da sessao
		List<Sessao> sessoesDaSala = sessaoDao.listaSessoesDaSala(sessaoForm.getSalaId());
		if (! gds.cabe(sessao, sessoesDaSala)) {
			return form(sessaoForm.getSalaId(), sessaoForm);
		}
		
		// salva sessao no banco de dados
		sessaoDao.salva(sessao);
		
		// vai pra lista de sessoes da sala 
		return new ModelAndView("redirect:/admin/sala/"+ sessaoForm.getSalaId() + "/sessoes/");
	}
	
	
	
	
	
	
	
	
	
}
