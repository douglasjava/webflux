package com.demo.webflux.service;

import com.demo.webflux.document.Playlist;
import com.demo.webflux.repository.PlaylistRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Service
public class PlaylistService {

    private PlaylistRepository repository;

    public Flux<Playlist> findAll() {
        return repository.findAll();
    }

    public Mono<Playlist> findById(String id) {
        return repository.findById(id);
    }

    public Mono<Playlist> save(Playlist playlist) {
        return repository.save(playlist);
    }

}
