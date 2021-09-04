package com.claucio.dev.devdojospring.service;

import com.claucio.dev.devdojospring.domain.Anime;
import com.claucio.dev.devdojospring.exception.BadRequestException;
import com.claucio.dev.devdojospring.mapper.AnimeMapper;
import com.claucio.dev.devdojospring.repository.AnimeRepository;
import com.claucio.dev.devdojospring.requests.AnimePostRequestBody;
import com.claucio.dev.devdojospring.requests.AnimePutRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimeService {

    private final AnimeRepository animeRepository;

    public Page<Anime> findAll(Pageable pageable) {
        return animeRepository.findAll(pageable);
    }

    public List<Anime> findAllNoPageable() {
        return animeRepository.findAll();
    }

    public List<Anime> findByName(String name) {
        return animeRepository.findByName(name);
    }

    public Anime findByIdOrThrowBadRequestException(Long id) {
        return animeRepository.findById(id).orElseThrow(() -> new BadRequestException("Anime not found"));
    }

    public Anime save(AnimePostRequestBody animePostRequestBody) {
        return animeRepository.save(AnimeMapper.INSTANCE.toAnime(animePostRequestBody));
    }


    public void delete(long id) {
        animeRepository.delete(findByIdOrThrowBadRequestException(id));

    }

    public void replace(AnimePutRequestBody animePutRequestBody) {
        Anime savedAnime = findByIdOrThrowBadRequestException(animePutRequestBody.getId());
        Anime anime = animeRepository.save(AnimeMapper.INSTANCE.toAnime(animePutRequestBody));
        anime.setId(savedAnime.getId());
    }
}
