package com.elouissi.hunters_league.web.rest.VM.mapper;

import com.elouissi.hunters_league.domain.AppUser;
import com.elouissi.hunters_league.service.DTO.AuthUserDTO;
import com.elouissi.hunters_league.web.rest.VM.RegisterVM;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RegisterMapper {
    AppUser VmToEntity(RegisterVM registerVM);
    AppUser DtoToEntity(AuthUserDTO AuthUserDTO);

    RegisterVM toVM(AppUser appUser);
    AuthUserDTO toDTO(AppUser appUser);
}
