package com.elouissi.hunters_league.web.rest.VM.mapper;

import com.elouissi.hunters_league.domain.User;
import com.elouissi.hunters_league.service.DTO.AuthUserDTO;
import com.elouissi.hunters_league.web.rest.VM.RegisterVM;
import com.elouissi.hunters_league.web.rest.VM.UserVM;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User VmToEntity(UserVM userVM);
//    User DtoToEntity(AuthUserDTO AuthUserDTO);

    UserVM toVM(User user);
//    AuthUserDTO toDTO(User user);
}
