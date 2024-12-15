package com.elouissi.hunters_league.web.rest.VM.mapper;

import com.elouissi.hunters_league.domain.AppUser;
import com.elouissi.hunters_league.service.DTO.UserResponse;
import com.elouissi.hunters_league.web.rest.VM.UserVM;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface UserMapper {
    AppUser VmToEntity(UserVM userVM);
//    User DtoToEntity(AuthUserDTO AuthUserDTO);

    UserVM toVM(AppUser appUser);
//    AuthUserDTO toDTO(User user);
default Page<UserResponse> toUserResponsePage(Page<AppUser> users) {
    return users.map(this::toUserResponse);
}
    UserResponse toUserResponse(AppUser appUser);

}
