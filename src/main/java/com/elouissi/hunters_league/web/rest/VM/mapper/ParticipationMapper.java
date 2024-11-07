package com.elouissi.hunters_league.web.rest.VM.mapper;

import com.elouissi.hunters_league.domain.Participation;
import com.elouissi.hunters_league.web.rest.VM.ParticipationVM;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface ParticipationMapper {
    Participation VmToEntity(ParticipationVM participationVM);
//    User DtoToEntity(AuthUserDTO AuthUserDTO);

    ParticipationVM toVM(Participation participation);
//    AuthUserDTO toDTO(User user);
}
