package br.com.caelum.ingresso.client;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import br.com.caelum.ingresso.model.Filme;

@Component
public class OmdbClient {
	public <T> Optional<T> fazRequisicao(Filme filme, Class<T> classe) {
		RestTemplate client = new RestTemplate();
		try {
		String url = "https://omdb-fj22.herokuapp.com/movie?title="
						+ filme.getNome().replaceAll(" ", "+");
		T objeto = 
				client.getForObject(url, classe);
		return Optional.of(objeto);
		} catch (RestClientException e) {
			return Optional.empty();
		}
	}
	
}
