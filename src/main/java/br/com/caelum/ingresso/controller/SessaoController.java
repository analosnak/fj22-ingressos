package br.com.caelum.ingresso.controller;

import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
	public ModelAndView form(@RequestParam("salaId") Integer idDaSala) {
		ModelAndView modelAndView = new ModelAndView("sessao/sessao");
		
		Sala sala = salaDao.findOne(idDaSala);
		modelAndView.addObject("sala", sala);
		
		List<Filme> filmes = filmeDao.findAll();
		
		modelAndView.addObject("filmes", filmes);
		
		return modelAndView;
	}
	
	
	
	
	
	
	
	
	
	
	@PostMapping("admin/sessao")
	@Transactional
	public ModelAndView salva(SessaoForm sessaoForm) {
		
		LocalTime horario = sessaoForm.getHorario();
		
		Integer salaId = sessaoForm.getSalaId();
		Sala sala = salaDao.findOne(salaId);
		
		Integer filmeId = sessaoForm.getFilmeId();
		Filme filme = filmeDao.findOne(filmeId);
		
		Sessao sessao = new Sessao(horario, sala, filme);
		
		sessaoDao.save(sessao);
		
		ModelAndView modelAndView = new ModelAndView("redirect:/admin/sala/1/sessoes/");
		
		return modelAndView; 
	}
	
}
