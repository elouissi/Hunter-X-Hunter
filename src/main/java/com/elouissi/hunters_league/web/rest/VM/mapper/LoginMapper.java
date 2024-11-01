package com.elouissi.hunters_league.web.rest.VM.mapper;

import com.elouissi.hunters_league.domain.User;
import com.elouissi.hunters_league.web.rest.VM.LoginVM;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LoginMapper {
    User VmToEntity(LoginVM loginVM);
//    User DtoToEntity(AuthUserDTO AuthUserDTO);

    LoginVM toVM(User user);
//    AuthUserDTO toDTO(User user);
}

