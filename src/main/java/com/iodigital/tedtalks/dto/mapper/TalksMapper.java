package com.iodigital.tedtalks.dto.mapper;

import com.iodigital.tedtalks.dto.AuthorDto;
import com.iodigital.tedtalks.dto.GetTalksResponseDto;
import com.iodigital.tedtalks.dto.TalkDto;
import com.iodigital.tedtalks.entity.Author;
import com.iodigital.tedtalks.entity.Talks;
import com.iodigital.tedtalks.entity.wrapper.TalksListWrapper;
import org.mapstruct.*;

import java.time.LocalDate;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class TalksMapper {

    @Mapping(target = "name", source = "name")
    public abstract AuthorDto authorToAuthorDto(Author author);

    @Mapping(target = "author", source = "author")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "views", source = "views")
    @Mapping(target = "likes", source = "likes")
    @Mapping(target = "url", source = "url")
    @Mapping(target = "influenceFactor", source = "influenceFactor")
    @Mapping(target = "year", source = "date", qualifiedByName = { "LocalDateToYear" })
    public abstract TalkDto talkToTalkDto(Talks talk);

    @Named("LocalDateToYear")
    public Integer localDateToYear(LocalDate localDate) {
        return localDate.getYear();
    }

    public abstract List<TalkDto> talksToTalkDtos(List<Talks> talks);

    @Mapping(target = "total", source = "total")
    @Mapping(target = "talks", source = "talks")
    public abstract GetTalksResponseDto talkListWrapperToGetTalkRespDTO(TalksListWrapper wrapper);

}
