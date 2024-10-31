package com.elouissi.hunters_league.web.rest.VM.mapper;

import ch.qos.logback.core.model.ComponentModel;
import com.elouissi.hunters_league.domain.User;
import com.elouissi.hunters_league.web.rest.VM.LoginVM;
import com.elouissi.hunters_league.web.rest.VM.RegisterVM;
import org.mapstruct.Mapper;

import java.awt.event.ComponentEvent;

@Mapper
public interface UserMapper {

    User toEntity(LoginVM userVM);

    User toEntity(RegisterVM userVM);

//    UserDTO toDTO(User user) ;
}
