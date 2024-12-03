package com.iodigital.tedtalks.dto.mapper;

import com.iodigital.tedtalks.dto.*;
import com.iodigital.tedtalks.entity.Author;
import com.iodigital.tedtalks.entity.Talk;
import com.iodigital.tedtalks.entity.wrapper.TalksListWrapper;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class DtoMapper {
    /* ******************* */
    /* Talks to DTO mapper */
    /* Used by:            */
    /*  - Get all talks    */
    /*  - Get talk by id   */
    /* ******************* */
    @Named("simpleAuthorMap")
    @Mapping(target = "name", source = "name")
    public abstract AuthorDTO authorToAuthorDto(Author author);

    @Named("influenceAuthorMap")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "influence", source = "influence")
    public abstract AuthorDTO authorToInfluenceAuthorDto(Author author);

    @Mapping(target = "author", source = "author", qualifiedByName = "simpleAuthorMap")
    @Mapping(target = "id", source = "id")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "views", source = "views")
    @Mapping(target = "likes", source = "likes")
    @Mapping(target = "url", source = "url")
    @Mapping(target = "influenceFactor", source = "influenceFactor")
    @Mapping(target = "year", source = "year")
    public abstract TalkDTO talkToDto(Talk talk);

    /* ********************************* */
    /* Most influential authors response */
    /* Used by:                          */
    /*  - Get most influential authors   */
    /* ********************************* */
    public InfluentialAuthorsResponseDTO authorsToInfluentialAuthorsDto(List<Author> authors) {
        // Instantiate
        InfluentialAuthorsResponseDTO dto = new InfluentialAuthorsResponseDTO();
        dto.authorsDTO = new ArrayList<>();

        // Populate
        for (Author a : authors) {
            dto.authorsDTO.add(authorToInfluenceAuthorDto(a));
        }

        // Return populated
        return dto;
    }


    /* ***************** */
    /* Get ALL Talks DTO */
    /* ***************** */
    public abstract List<TalkDTO> talksToTalkDtos(List<Talk> talks);

    @Mapping(target = "total", source = "total")
    @Mapping(target = "talks", source = "talks")
    public abstract GetTalksResponseDTO wrapperToGetTalkRespDTO(TalksListWrapper wrapper);

    public TalkListDTO listToTalkListDTO(List<Talk> talks) {
        // Instantiate
        TalkListDTO dto = new TalkListDTO();

        // Populate
        dto.talks = talksToTalkDtos(talks);

        // Return populated
        return dto;
    }

    /* ******************* */
    /* DTO to Talks mapper */
    /* Used by:            */
    /* - Update Talk       */
    /* ******************* */
    @Mapping(target = "name", source = "name")
    public abstract Author authorToAuthorDto(AuthorDTO author);

    public Talk dtoToTalk(TalkDTO dto) {
        return new Talk(
                authorToAuthorDto(dto.author),
                dto.title,
                dto.views,
                dto.likes,
                dto.url,
                dto.year
        );
    }
}
