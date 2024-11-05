package com.elouissi.hunters_league.web.rest.VM.mapper;

import com.elouissi.hunters_league.domain.Competition;
import com.elouissi.hunters_league.web.rest.VM.CompetitionVM;
import com.elouissi.hunters_league.web.rest.VM.UserVM;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface CompetitionMapper {
    Competition VmToEntity(CompetitionVM competitionVM);
//    Competition DtoToEntity(AuthCompetitionDTO AuthCompetitionDTO);

    CompetitionVM toVM(Competition competition);
//    AuthCompetitionDTO toDTO(Competition competition);

}
