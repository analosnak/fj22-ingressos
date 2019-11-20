package br.com.caelum.ingresso.services;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.caelum.ingresso.model.DetalhesDoFilme;
import br.com.caelum.ingresso.model.Filme;

@Service
public class OmdbClient {

	public Optional<DetalhesDoFilme> fazRequisicao(Filme filme) {
		try {
	    	RestTemplate restTemplate = new RestTemplate();
	    	String nome = filme.getNome().replace(" ", "+");
	    	String url = "http://omdb-fj22.herokuapp.com/movie?title=" + nome;
	    	DetalhesDoFilme detalhesDoFilme = restTemplate.getForObject(url, DetalhesDoFilme.class);
	    	
	    	return Optional.of(detalhesDoFilme);
	    	} catch (Exception e) {
				e.printStackTrace();
				return Optional.empty();
			}
	}
}
