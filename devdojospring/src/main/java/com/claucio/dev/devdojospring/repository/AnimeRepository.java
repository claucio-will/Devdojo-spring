package com.claucio.dev.devdojospring.repository;

import com.claucio.dev.devdojospring.domain.Anime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnimeRepository  extends JpaRepository<Anime, Long> {

   List<Anime> findByName(String name);

}
