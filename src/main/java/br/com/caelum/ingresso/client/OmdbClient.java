package br.com.caelum.ingresso.client;

import java.util.Optional;

import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import br.com.caelum.ingresso.model.DetalhesDoFilme;
import br.com.caelum.ingresso.model.Filme;

public class OmdbClient {
	public Optional<DetalhesDoFilme> fazRequisicao(Filme filme) {
		RestTemplate client = new RestTemplate();
		try {
		String url = "https://omdb-fj22.herokuapp.com/movie?title="
						+ filme.getNome().replaceAll(" ", "+");
		DetalhesDoFilme detalhesDoFilme = 
				client.getForObject(url, DetalhesDoFilme.class);
		
		return Optional.of(detalhesDoFilme);
		} catch (RestClientException e) {
			return Optional.empty();
		}
	}
}
