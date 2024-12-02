package com.iodigital.tedtalks.dto.mapper;

import com.iodigital.tedtalks.dto.AuthorDto;
import com.iodigital.tedtalks.dto.GetTalksResponseDto;
import com.iodigital.tedtalks.dto.TalkDto;
import com.iodigital.tedtalks.entity.Author;
import com.iodigital.tedtalks.entity.Talks;
import com.iodigital.tedtalks.entity.wrapper.TalksListWrapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class TalksMapper {
    /* ******************* */
    /* Talks to DTO mapper */
    /* Used by:            */
    /*  - Get all talks    */
    /*  - Get talk by id   */
    /* ******************* */
    @Mapping(target = "name", source = "name")
    public abstract AuthorDto authorToAuthorDto(Author author);

    @Mapping(target = "author", source = "author")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "views", source = "views")
    @Mapping(target = "likes", source = "likes")
    @Mapping(target = "url", source = "url")
    @Mapping(target = "influenceFactor", source = "influenceFactor")
    @Mapping(target = "year", source = "year")
    public abstract TalkDto talkToDto(Talks talk);

    public abstract List<TalkDto> talksToTalkDtos(List<Talks> talks);

    /* ***************** */
    /* Get ALL Talks DTO */
    /* ***************** */
    @Mapping(target = "total", source = "total")
    @Mapping(target = "talks", source = "talks")
    public abstract GetTalksResponseDto talkListWrapperToGetTalkRespDTO(TalksListWrapper wrapper);

    /* ******************* */
    /* DTO to Talks mapper */
    /* Used by:            */
    /* - Update Talk       */
    /* ******************* */
    @Mapping(target = "name", source = "name")
    public abstract Author authorToAuthorDto(AuthorDto author);

    //@Mapping(target = "author", source = "author")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "views", source = "views")
    @Mapping(target = "likes", source = "likes")
    @Mapping(target = "url", source = "url")
    //@Mapping(target = "influenceFactor", source = "influenceFactor") Not updatable
    @Mapping(target = "year", source = "year")
    public abstract Talks dtoToTalk(TalkDto dto);
}
