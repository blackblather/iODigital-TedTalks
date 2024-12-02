package com.iodigital.tedtalks.dto.mapper;

import com.iodigital.tedtalks.dto.GetTalksResponseDto;
import com.iodigital.tedtalks.dto.TalkDto;
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

    @Mapping(target = "title", source = "title")
    @Mapping(target = "views", source = "views")
    @Mapping(target = "likes", source = "likes")
    @Mapping(target = "url", source = "url")
    @Mapping(target = "influenceFactor", source = "influenceFactor")
    public abstract TalkDto talkToTalkDto(Talks talk);

    public abstract List<TalkDto> talksToTalkDtos(List<Talks> talks);

    @Mapping(target = "total", source = "total")
    @Mapping(target = "talks", source = "talks")
    public abstract GetTalksResponseDto talkListWrapperToGetTalkRespDTO(TalksListWrapper wrapper);

}
