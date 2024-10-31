package com.elouissi.hunters_league.web.rest.VM.mapper;

import ch.qos.logback.core.model.ComponentModel;
import com.elouissi.hunters_league.domain.User;
import com.elouissi.hunters_league.service.DTO.AuthUserDTO;
import com.elouissi.hunters_league.web.rest.VM.RegisterVM;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RegisterMapper {
    User VmToEntity(RegisterVM registerVM);
    User DtoToEntity(AuthUserDTO AuthUserDTO);

    RegisterVM toVM(User user);
    AuthUserDTO toDTO(User user);
}
