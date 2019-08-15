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
		try {
			String nomeDoFilme = filme.getNome();
			RestTemplate restTemplate = new RestTemplate();
			String url = "https://omdb-fj22.herokuapp.com/movie?title=" + nomeDoFilme.replace(" ", "+");
			DetalhesDoFilme detalhesDoFilme = restTemplate.getForObject(url, DetalhesDoFilme.class);
			return Optional.ofNullable(detalhesDoFilme);
		} catch (RestClientException e) {
			System.out.println("deu erro ao pegar dados da API");
			return Optional.empty();
		}
	}

}
