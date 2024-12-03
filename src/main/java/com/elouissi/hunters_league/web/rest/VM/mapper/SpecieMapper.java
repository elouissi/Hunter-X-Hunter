package com.elouissi.hunters_league.web.rest.VM.mapper;

import com.elouissi.hunters_league.domain.Species;
import com.elouissi.hunters_league.web.rest.VM.SpecieVM;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface SpecieMapper {
    Species VmToEntity(SpecieVM specieVM);
//    User DtoToEntity(AuthUserDTO AuthUserDTO);

    SpecieVM toVM(Species specie);
//    AuthUserDTO toDTO(User user);
}
