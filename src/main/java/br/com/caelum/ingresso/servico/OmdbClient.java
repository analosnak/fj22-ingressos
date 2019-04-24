package br.com.caelum.ingresso.servico;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.InfosDoFilme;

@Component
public class OmdbClient {

    public Optional<InfosDoFilme> fazRequisicao(Filme filme) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = "https://omdb-fj22.herokuapp.com/movie?title=" + filme.getNome();
            InfosDoFilme infosDoFilme = restTemplate.getForObject(url, InfosDoFilme.class);

            return Optional.of(infosDoFilme);

        } catch (RestClientException e) {
            System.out.println("Deu erro no servidor");
            return Optional.empty();
        }
    }
}
