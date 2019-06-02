package com.bbp.usersservice.rest.controller;


import com.bbp.usersservice.facade.UserFacade;
import com.bbp.usersservice.rest.dto.UserCreateRestRequestDto;
import com.bbp.usersservice.rest.dto.UserUpdateRestRequestDto;
import com.bbp.usersservice.rest.dto.UserRestResponseDto;
import com.bbp.usersservice.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.bbp.usersservice.config.Constants.REST_PATH;

@RequiredArgsConstructor
@RestController
@RequestMapping(REST_PATH + "/users/")
public class UserController {

    private final UserFacade facade;
    private final UserMapper mapper;

    @GetMapping(path = "{userId}")
    public ResponseEntity<UserRestResponseDto> readUser(@PathVariable String userId) {
        var user = facade.readUser(userId);
        return ResponseEntity.ok().body(mapper.toRestResponse(user));
    }

    @GetMapping
    public ResponseEntity<Page<UserRestResponseDto>> readUsers(Pageable pageable) {
        var inventories = facade.readUsers(pageable);
        return ResponseEntity.ok().body(mapper.toRestResponsePage(inventories));
    }

    @PostMapping
    public ResponseEntity<UserRestResponseDto> createUser(@RequestBody UserCreateRestRequestDto restDto) {
        var facadeDto = mapper.toCreateFacadeRequest(restDto);
        var user = facade.createUser(facadeDto);
        //TODO fix null in location
        return ResponseEntity.created(null).body(mapper.toRestResponse(user));
    }

    @PutMapping(path = "/{userId}")
    public ResponseEntity<UserRestResponseDto> updateUser(@PathVariable("userId") String userId,
                                                          @RequestBody UserUpdateRestRequestDto restDto) {
        var facadeDto = mapper.toUpdateFacadeRequest(restDto);
        var user = facade.updateUser(userId, facadeDto);
        return ResponseEntity.ok().body(mapper.toRestResponse(user));
    }

    @DeleteMapping(path = "/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
        facade.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
