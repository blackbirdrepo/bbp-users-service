package com.bbp.usersservice.rest.controller;

import com.bbp.usersservice.facade.RoleFacade;
import com.bbp.usersservice.rest.dto.RoleCreateUpdateRestRequestDto;
import com.bbp.usersservice.rest.dto.RoleRestResponseDto;
import com.bbp.usersservice.service.mapper.RoleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bbp.usersservice.config.Constants.REST_PATH;

@RequiredArgsConstructor
@RestController
@RequestMapping(REST_PATH + "/roles/")
public class RoleController {

    private final RoleFacade facade;
    private final RoleMapper mapper;

    @GetMapping(path = "{roleId}")
    public ResponseEntity<RoleRestResponseDto> readRole(@PathVariable String roleId) {
        var role = facade.readRole(roleId);
        return ResponseEntity.ok().body(mapper.toRestResponse(role));
    }

    @GetMapping
    public ResponseEntity<List<RoleRestResponseDto>> readRoles() {
        return ResponseEntity.ok().body(mapper.toRestResponseList(facade.readRoles()));
    }

    @PostMapping
    public ResponseEntity<RoleRestResponseDto> createRole(@RequestBody RoleCreateUpdateRestRequestDto restDto) {
        var facadeDto = mapper.toCreateUpdateFacadeRequest(restDto);
        var role = facade.createRole(facadeDto);
        //TODO fix null in resource location
        return ResponseEntity.created(null).body(mapper.toRestResponse(role));
    }

    @PutMapping(path = "/{roleId}")
    public ResponseEntity<RoleRestResponseDto> createRole(
            @PathVariable("roleId") String roleId,
            @RequestBody RoleCreateUpdateRestRequestDto restDto) {
        var facadeDto = mapper.toCreateUpdateFacadeRequest(restDto);
        var role = facade.updateRole(roleId, facadeDto);
        return ResponseEntity.ok().body(mapper.toRestResponse(role));
    }

    @DeleteMapping(path = "/{roleId}")
    public ResponseEntity<Void> deleteRole(@PathVariable String roleId) {
        facade.deleteRole(roleId);
        return ResponseEntity.noContent().build();
    }
}