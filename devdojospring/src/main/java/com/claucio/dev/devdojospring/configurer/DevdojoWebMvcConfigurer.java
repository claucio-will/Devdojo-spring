package com.claucio.dev.devdojospring.configurer;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 *A anotação Configuration diz que tudo que for definido dentro dessa classe será usado
 * globalmente na aplicação spring.
 * */
@Configuration
public class DevdojoWebMvcConfigurer  implements WebMvcConfigurer {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        /**
         *A classe PageableHandlerMethodArgumentResolver que se encarrega de setar a pagina que começa
         * e a quantidades de itens que será retornado
        * */
        PageableHandlerMethodArgumentResolver pageHandler = new PageableHandlerMethodArgumentResolver();
        /**
         * Metodo setFallbackPageable que seta a pagina que começa quando for feita uma requisição
         * ele espera que seja passado a pagina que começa e a quantidade de itens que será retornado
         * */
        pageHandler.setFallbackPageable(PageRequest.of(0,5));
        resolvers.add(pageHandler);
    }
}
