package com.elouissi.hunters_league.web.rest.VM.mapper;

import com.elouissi.hunters_league.domain.Hunt;
import com.elouissi.hunters_league.web.rest.VM.HuntVM;
import com.elouissi.hunters_league.web.rest.VM.ParticipationVM;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface HuntMapper {
    Hunt VmToEntity(HuntVM huntVM);
//    User DtoToEntity(AuthUserDTO AuthUserDTO);

    HuntVM toVM(Hunt hunt);
//    AuthUserDTO toDTO(User user);
}
