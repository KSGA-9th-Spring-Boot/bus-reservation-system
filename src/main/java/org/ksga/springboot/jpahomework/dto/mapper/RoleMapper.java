package org.ksga.springboot.jpahomework.dto.mapper;

import org.ksga.springboot.jpahomework.dto.model.user.RoleDto;
import org.ksga.springboot.jpahomework.model.user.Role;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface RoleMapper {
    Role roleDtoToRole(RoleDto roleDto);
    RoleDto roleToRoleDto(Role role);
}
