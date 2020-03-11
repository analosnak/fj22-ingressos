package br.com.caelum.ingresso.webservice;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import br.com.caelum.ingresso.model.DetalhesDoFilme;
import br.com.caelum.ingresso.model.Filme;

@Component
public class OmdbClient {
	public Optional<DetalhesDoFilme> fazRequisicao(Filme filme) {
    	DetalhesDoFilme detalhesDoFilme;
    	try {
	    	RestTemplate restTemplate = new RestTemplate();
	    	String url = "https://omdb-fj22.herokuapp.com/movie?title="+ filme.getNome().replace(" ", "+");
			detalhesDoFilme = restTemplate.getForObject(url, DetalhesDoFilme.class);
			return Optional.ofNullable(detalhesDoFilme);
    	} catch (RestClientException e) {
    		e.printStackTrace();
    		return Optional.empty();
    	}
	}
}
