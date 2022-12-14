package com.demo.webflux.funcional.router;

import com.demo.webflux.funcional.handler.PlaylistHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

//@Configuration
public class PlaylistRouter {

    @Bean
    public RouterFunction<ServerResponse> router(PlaylistHandler handler) {
        return RouterFunctions
                .route(GET("/webflux/playlist").and(accept(MediaType.APPLICATION_JSON)), handler::lista)
                .andRoute(GET("/webflux/playlist/{id}").and(accept(MediaType.APPLICATION_JSON)), handler::buscar)
                .andRoute(POST("/webflux/playlist").and(accept(MediaType.APPLICATION_JSON)), handler::save);
    }

}
