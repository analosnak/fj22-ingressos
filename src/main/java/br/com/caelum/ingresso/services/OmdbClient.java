package br.com.caelum.ingresso.services;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.caelum.ingresso.model.DetalhesDoFilme;
import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.ImagemTop;

@Service
public class OmdbClient {

	public <T> Optional<T> fazRequisicao(Filme filme, Class<T> tipoDaClasse) {
		try {
	    	RestTemplate restTemplate = new RestTemplate();
	    	String nome = filme.getNome().replace(" ", "+");
	    	String url = "http://omdb-fj22.herokuapp.com/movie?title=" + nome;
	    	T objeto = restTemplate.getForObject(url, tipoDaClasse);
	    	
	    	return Optional.of(objeto);
	    	} catch (Exception e) {
				e.printStackTrace();
				return Optional.empty();
			}
	}

}
