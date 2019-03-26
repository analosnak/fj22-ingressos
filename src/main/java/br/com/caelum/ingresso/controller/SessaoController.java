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

	@GetMapping("admin/sessao")
	public ModelAndView form(@RequestParam("salaId") Integer salaId) {
		ModelAndView mav = new ModelAndView("sessao/sessao");
		List<Filme> filmes = filmeDao.findAll();
		mav.addObject("filmes", filmes);

		Sala sala = salaDao.findOne(salaId);
		mav.addObject("sala", sala);
		return mav;
	}

	@Transactional
	@PostMapping("admin/sessao")
	public ModelAndView salva(@Valid SessaoForm sessaoForm, BindingResult result) {
		if (result.hasErrors()) {
			return form(sessaoForm.getSalaId());
		}
		Sessao sessao = sessaoForm.toSessao(salaDao, filmeDao);
		List<Sessao> sessoes = sessaoDao.findAllBySala(sessao.getSala().getId());

		GerenciadorDeSessoes gerenciador = new GerenciadorDeSessoes();
		if (gerenciador.cabeNaSala(sessao, sessoes)) {

			sessaoDao.salva(sessao);

			ModelAndView mav = new ModelAndView("redirect:sala/" + sessao.getSala().getId() + "/sessoes/");
			return mav;
		}
		return form(sessaoForm.getSalaId());
	}
}
