package br.com.caelum.ingresso.services;

import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import br.com.caelum.ingresso.model.Filme;

@Component
public class OmdbClient {

	private Logger logger = Logger.getLogger(OmdbClient.class);

	public <T> Optional<T> fazRequisicao(Filme filme, Class<T> tipoDaClasse) {
		try {
			RestTemplate cliente = new RestTemplate();
			String url = "https://omdb-fj22.herokuapp.com/movie?title=" + filme.getNome();
			T objeto = cliente.getForObject(url, tipoDaClasse);
			return Optional.of(objeto);
		} catch (RestClientException e) {
			logger.error("erro ao pegar detalhes", e);
			return Optional.empty();
		}
	}

}
