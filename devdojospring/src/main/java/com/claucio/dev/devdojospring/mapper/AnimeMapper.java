package com.claucio.dev.devdojospring.mapper;

import com.claucio.dev.devdojospring.domain.Anime;
import com.claucio.dev.devdojospring.requests.AnimePostRequestBody;
import com.claucio.dev.devdojospring.requests.AnimePutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class AnimeMapper {

    public static final AnimeMapper INSTANCE = Mappers.getMapper(AnimeMapper.class);

    public abstract Anime toAnime(AnimePostRequestBody animePostRequestBody);

    public abstract Anime toAnime(AnimePutRequestBody animePutRequestBody);

}
