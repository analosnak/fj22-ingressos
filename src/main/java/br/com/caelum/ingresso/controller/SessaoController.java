package br.com.caelum.ingresso.controller;

import java.time.LocalTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
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

	@GetMapping("admin/sessao")
	public ModelAndView form(@RequestParam("salaId") Integer idSala, SessaoForm sessaoForm) {
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
	public ModelAndView salva(@Valid SessaoForm sessaoForm, BindingResult result) {
		if (result.hasErrors()) {
			return form(sessaoForm.getSalaId(), sessaoForm);
		}

		Sessao sessao = sessaoForm.toSessao(filmeDao, salaDao);

		List<Sessao> sessoesDaSala = sessaoDao.buscaSessoesDaSala(sessaoForm.getSalaId());
		
		// valida sessao na sala
		GerenciadorDeSessoes gds = new GerenciadorDeSessoes(sessoesDaSala);
		if (gds.cabe(sessao)) {
			sessaoDao.salva(sessao);

			return new ModelAndView("redirect:/admin/sala/" + sessaoForm.getSalaId() + "/sessoes");
		} else {
			return form(sessaoForm.getSalaId(), sessaoForm);
		}
	}

}
