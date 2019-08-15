package br.com.caelum.ingresso.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.ingresso.dao.FilmeDao;
import br.com.caelum.ingresso.dao.SalaDao;
import br.com.caelum.ingresso.dao.SessaoDao;
import br.com.caelum.ingresso.model.Carrinho;
import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.ImagemDoFilme;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.TipoDeIngresso;
import br.com.caelum.ingresso.model.form.SessaoForm;
import br.com.caelum.ingresso.validacao.GerenciadorDeSessao;
import br.com.caelum.ingresso.webservice.OmdbClient;

@Controller
public class SessaoController {
	@Autowired
	private FilmeDao filmeDao;
	@Autowired
	private SalaDao salaDao;
	@Autowired
	private SessaoDao sessaoDao;
	@Autowired
	private OmdbClient omdbClient;
	@Autowired
	private Carrinho carrinho;
	
	@GetMapping("admin/sessao")
	public ModelAndView form(@RequestParam("salaId") Integer idDaSala, SessaoForm sessaoForm) {
		ModelAndView modelAndView = new ModelAndView("sessao/sessao");
		
		Sala sala = salaDao.findOne(idDaSala);
		modelAndView.addObject("sala", sala);
		
		List<Filme> filmes = filmeDao.findAll();
		modelAndView.addObject("filmes", filmes);
		
		modelAndView.addObject("form", sessaoForm);
		
		return modelAndView;
	}
	
	@PostMapping("admin/sessao")
	@Transactional
	public ModelAndView salva(@Valid SessaoForm sessaoForm, BindingResult result) {
		if (result.hasErrors()) {
			return form(sessaoForm.getSalaId(), sessaoForm);
		}
		
		Sessao sessao = sessaoForm.toSessao(salaDao, filmeDao);
		
		List<Sessao> sessoesDaSala = sessaoDao.listaSessoesDaSala(sessao.getSala());
		
		GerenciadorDeSessao gerenciador = new GerenciadorDeSessao(sessoesDaSala);
		if (!gerenciador.cabe(sessao)) {
			return form(sessaoForm.getSalaId(), sessaoForm);
		}
		
		sessaoDao.save(sessao);
		
		ModelAndView modelAndView = new ModelAndView("redirect:/admin/sala/"+ sessaoForm.getSalaId() +"/sessoes/");
		
		return modelAndView; 
	}
	
	@GetMapping("sessao/{id}/lugares")
	public ModelAndView listaLugares(@PathVariable("id") Integer sessaoId) {
		ModelAndView modelAndView = new ModelAndView("sessao/lugares");
		
		Sessao sessao = sessaoDao.findOne(sessaoId);
		modelAndView.addObject("sessao", sessao);
		
		Optional<ImagemDoFilme> optional =
				omdbClient.fazRequisicao(sessao.getFilme(), ImagemDoFilme.class);
		modelAndView.addObject("imagemCapa", optional.orElse(new ImagemDoFilme()));
		
		modelAndView.addObject("tiposDeIngressos", TipoDeIngresso.values());
		
		modelAndView.addObject("carrinho", carrinho);
		
		return modelAndView;
	}
	
	
	
	
	
	
	
	
}
