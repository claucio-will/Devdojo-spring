package com.claucio.dev.devdojospring.controller;

import com.claucio.dev.devdojospring.domain.Anime;
import com.claucio.dev.devdojospring.requests.AnimePostRequestBody;
import com.claucio.dev.devdojospring.requests.AnimePutRequestBody;
import com.claucio.dev.devdojospring.service.AnimeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "animes")
@RequiredArgsConstructor
@Log4j2
public class AnimeController {

    private final AnimeService animeService;

    /**
     * Usando o Pageable no metodo get, ele conta com paginção e também com o sort
     * */
    @GetMapping()
    public ResponseEntity<Page<Anime>> list(Pageable pageable) {
        return ResponseEntity.ok(animeService.findAll(pageable));
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<Anime>> listAll() {
        return ResponseEntity.ok(animeService.findAllNoPageable());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Anime> findById(@PathVariable long id) {
        return ResponseEntity.ok(animeService.findByIdOrThrowBadRequestException(id));
    }

    @GetMapping(path = "/find") 
    public ResponseEntity<List<Anime>> findByName(@RequestParam String name) {
        return ResponseEntity.ok(animeService.findByName(name));
    }
    
    @PostMapping()
    public ResponseEntity<Anime> save(@RequestBody @Valid AnimePostRequestBody anime){
     return new ResponseEntity<>(animeService.save(anime), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        animeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping()
    public ResponseEntity<Void> replace(@RequestBody AnimePutRequestBody animePutRequestBody) {
        animeService.replace(animePutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
