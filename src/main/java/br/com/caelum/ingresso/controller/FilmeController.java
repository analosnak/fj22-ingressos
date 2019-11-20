package br.com.caelum.ingresso.controller;

import br.com.caelum.ingresso.dao.FilmeDao;
import br.com.caelum.ingresso.dao.SessaoDao;
import br.com.caelum.ingresso.model.DetalhesDoFilme;
import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.services.OmdbClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

/**
 * Created by nando on 03/03/17.
 */
@Controller
public class FilmeController {


    @Autowired
    private FilmeDao filmeDao;
    @Autowired
    private SessaoDao sessaoDao;
    @Autowired
    private OmdbClient client;


    @GetMapping({"/admin/filme", "/admin/filme/{id}"})
    public ModelAndView form(@PathVariable("id") Optional<Integer> id, Filme filme){

        ModelAndView modelAndView = new ModelAndView("filme/filme");

        if (id.isPresent()){
            filme = filmeDao.findOne(id.get());
        }

        modelAndView.addObject("filme", filme);

        return modelAndView;
    }


    @PostMapping("/admin/filme")
    @Transactional
    public ModelAndView salva(@Valid Filme filme, BindingResult result){

        if (result.hasErrors()) {
            return form(Optional.ofNullable(filme.getId()), filme);
        }

        filmeDao.save(filme);

        ModelAndView view = new ModelAndView("redirect:/admin/filmes");

        return view;
    }


    @GetMapping(value="/admin/filmes")
    public ModelAndView lista(){

        ModelAndView modelAndView = new ModelAndView("filme/lista");

        modelAndView.addObject("filmes", filmeDao.findAll());

        return modelAndView;
    }


    @DeleteMapping("/admin/filme/{id}")
    @ResponseBody
    @Transactional
    public void delete(@PathVariable("id") Integer id){
        filmeDao.delete(id);
    }
    
    @GetMapping("filme/em-cartaz")
    public ModelAndView emCartaz() {
    	ModelAndView modelAndView = new ModelAndView("filme/em-cartaz");
    	modelAndView.addObject("filmes", filmeDao.findAll());
    	
    	return modelAndView;
    }

    @GetMapping("filme/{id}/detalhe")
    public ModelAndView detalhe(@PathVariable("id")  Integer id) {
    	ModelAndView modelAndView = new ModelAndView("filme/detalhe");
    	    	
    	Filme filme = filmeDao.findOne(id);

    	Optional<DetalhesDoFilme> optional = client.fazRequisicao(filme);
    	
    	DetalhesDoFilme padrao = new DetalhesDoFilme();
    	padrao.setTitulo("Sem Filme");
    	padrao.setAno("1900");
    	
		DetalhesDoFilme detalhesDoFilme = optional.orElse(padrao);
    
    	
    	modelAndView.addObject("sessoes", sessaoDao.buscaSessoesDoFilme(filme));
    	modelAndView.addObject("detalhes", detalhesDoFilme);
    	
    	return modelAndView;
    }
    
}
