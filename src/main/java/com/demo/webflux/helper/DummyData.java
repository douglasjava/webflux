package com.demo.webflux.helper;

import com.demo.webflux.document.Playlist;
import com.demo.webflux.repository.PlaylistRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.UUID;

@AllArgsConstructor
@Component
public class DummyData implements CommandLineRunner {

    private PlaylistRepository repository;

    /**
     * Primeiro apaga o repositorio e após cria alguns nomes no banco
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        repository.deleteAll()
                .thenMany(
                        Flux.just("API REST Spring Boot", "Java 8", "Java 9",
                                "Java 10", "Java 11", "Gitlab", "Github", "Inter")
                        .map(nome -> new Playlist(UUID.randomUUID().toString(), nome))
                        .flatMap(repository::save))
                .subscribe(System.out::println);

    }

}
