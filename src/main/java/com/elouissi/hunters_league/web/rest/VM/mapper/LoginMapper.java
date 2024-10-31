package com.elouissi.hunters_league.web.rest.VM.mapper;

import ch.qos.logback.core.model.ComponentModel;
import com.elouissi.hunters_league.domain.User;
import com.elouissi.hunters_league.service.DTO.AuthUserDTO;
import com.elouissi.hunters_league.web.rest.VM.LoginVM;
import com.elouissi.hunters_league.web.rest.VM.RegisterVM;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LoginMapper {
    User VmToEntity(LoginVM loginVM);
//    User DtoToEntity(AuthUserDTO AuthUserDTO);

    LoginVM toVM(User user);
//    AuthUserDTO toDTO(User user);
}

