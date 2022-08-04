package com.demo.webflux.controller;

import com.demo.webflux.document.Playlist;
import com.demo.webflux.service.PlaylistService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@AllArgsConstructor
@RestController
@RequestMapping("/webflux/playlist")
public class PlaylistController {

    private PlaylistService service;

    @GetMapping
    public Flux<Playlist> lista() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Playlist> buscar(@PathVariable String id) {
        return service.findById(id);
    }

    @PostMapping
    public Mono<Playlist> salvar(@RequestBody Playlist playlist) {
        return service.save(playlist);
    }


    @GetMapping(value="/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Tuple2<Long, Playlist>> getPlaylistByEvents(){

        Flux<Long> interval = Flux.interval(Duration.ofSeconds(10));
        Flux<Playlist> events = service.findAll();
        System.out.println("Passou aqui events");

        return Flux.zip(interval, events);

    }

    @GetMapping(value="/mvc")
    public List<String> getPlaylistByMvc() throws InterruptedException {

        System.out.println("---Start get Playlists by MVC--- " + LocalDateTime.now());

        List<String> playlistList = new ArrayList<>();
        playlistList.add("Java 8");
        playlistList.add("Spring Security");
        playlistList.add("Github");
        playlistList.add("Deploy de uma aplicação java no IBM Cloud");
        playlistList.add("Bean no Spring Framework");
        TimeUnit.SECONDS.sleep(15);

        return playlistList;

    }
}
