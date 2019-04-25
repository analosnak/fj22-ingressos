package br.com.caelum.ingresso.servico;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import br.com.caelum.ingresso.model.Filme;

@Component
public class OmdbClient {

    public <T> Optional<T> fazRequisicao(Filme filme, Class<T> tipoDaClasse) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = "https://omdb-fj22.herokuapp.com/movie?title=" + filme.getNome();
            T objeto = restTemplate.getForObject(url, tipoDaClasse);

            return Optional.of(objeto);

        } catch (RestClientException e) {
            System.out.println("Deu erro no servidor");
            return Optional.empty();
        }
    }

}
