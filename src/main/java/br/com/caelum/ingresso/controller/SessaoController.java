package br.com.caelum.ingresso.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

import br.com.caelum.ingresso.client.OmdbClient;
import br.com.caelum.ingresso.dao.FilmeDao;
import br.com.caelum.ingresso.dao.SalaDao;
import br.com.caelum.ingresso.dao.SessaoDao;
import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.ImagemDoFilme;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.desconto.Desconto;
import br.com.caelum.ingresso.model.desconto.DescontoEstudante;
import br.com.caelum.ingresso.model.desconto.DescontoIdoso;
import br.com.caelum.ingresso.model.form.SessaoForm;
import br.com.caelum.ingresso.validacao.GerenciadorDeSessao;

@Controller
public class SessaoController {
	
	@Autowired
	private FilmeDao filmeDao;
	@Autowired
	private SalaDao salaDao;
	@Autowired
	private SessaoDao sessaoDao;
	@Autowired
	private GerenciadorDeSessao gerenciador;
	@Autowired
	private OmdbClient omdbClient;
	
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
		
		List<Sessao> sessoes = sessaoDao.findAllBySala(sessaoForm.getSalaId());
		
		if (!gerenciador.cabe(sessao, sessoes)) {
			return formulario(sessaoForm.getSalaId(), sessaoForm);
		}
		sessaoDao.salva(sessao);
		
		return new ModelAndView("redirect:/admin/sala/"+
		sessaoForm.getSalaId() +"/sessoes/");
	}
	
	@GetMapping("sessao/{id}/lugares")
	public ModelAndView lugares(@PathVariable("id") Integer id) {
		ModelAndView mav = new ModelAndView("sessao/lugares");
		
		Sessao sessao = sessaoDao.findOne(id);
		mav.addObject("sessao", sessao);
		
		Optional<ImagemDoFilme> optional = 
				omdbClient.fazRequisicao(sessao.getFilme(), ImagemDoFilme.class);
		
		ImagemDoFilme imagem = optional.orElse(new ImagemDoFilme());
		
		mav.addObject("imagemCapa", imagem);
		
		mav.addObject("tiposDeIngressos", TipoDeIngresso.values());
		
		return mav;
	}
	
	
	
}
