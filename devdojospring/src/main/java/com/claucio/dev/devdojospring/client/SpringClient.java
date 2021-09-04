package com.claucio.dev.devdojospring.client;

import com.claucio.dev.devdojospring.domain.Anime;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Essa classe é apenas para teste ela serve como se fosse um front-end.
 * usando o RestTemplate para fazer as requisições.
 * OBS: RestTemplate será descontinuado, estudar o WebClient.
 */

@Log4j2
public class SpringClient {

    public static void main(String[] args) {
        ResponseEntity<Anime> entity = new RestTemplate().getForEntity("http://localhost:8080/animes/2", Anime.class);
        log.info(entity);

        Anime object = new RestTemplate().getForObject("http://localhost:8080/animes/{id}", Anime.class, 2);
        log.info(object);

        ResponseEntity<List<Anime>> exchange = new RestTemplate().exchange("http://localhost:8080/animes/all", HttpMethod.GET, null,
                new ParameterizedTypeReference<>() {
                });

        log.info(exchange.getBody());
        //GET ------------------------------------------------------------- FIM


        //POST------------------------------------------------------------ INICIO
        Anime bobEsponja = Anime.builder().name("Bob Esponja").build();
        Anime anime = new RestTemplate().postForObject("http://localhost:8080/animes", bobEsponja, Anime.class);
        log.info("Anime criado =>" + anime);


        Anime animePost = Anime.builder().name("Bob Esponja").build();

        ResponseEntity<Anime> exchangePost = new RestTemplate().exchange("http://localhost:8080/animes/",
                HttpMethod.POST,
                new HttpEntity<>(animePost),
                Anime.class
        );
        log.info("Anime criado =>" + exchangePost);

        // ----------------------------------------------------------------------- FIM
    }
}
