package com.tickets.controller;

import com.tickets.dto.UserDto;
import com.tickets.logger.Logger;
import com.tickets.mapper.UserDtoMapper;
import com.tickets.model.User;
import com.tickets.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private UserDtoMapper mapper;
    @Autowired
    private UserService userService;
    @Autowired
    private Logger logger;

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        try {
            logger.info("User to create: " + userDto);
            User user = this.mapper.convertToEntity(userDto);
            user = this.userService.createUser(user);
            logger.info("Created user: " + user);

            return new ResponseEntity(
                    this.mapper.convertToDto(user),
                    HttpStatus.CREATED
                    );
        } catch (Exception e) {
            logger.error(e);
            return new ResponseEntity(
                    HttpStatus.INTERNAL_SERVER_ERROR
                    );
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDto> findById(@PathVariable UUID id) {
        try {
            logger.info("User to retrieve with ID: " + id);
            UserDto userDto = this.mapper.convertToDto(this.userService.findById(id));
            logger.info("Retrieved user: " + userDto);
            return new ResponseEntity(
                    userDto,
                    HttpStatus.OK
                    );
        } catch (Exception e) {
            logger.error(e);
            return new ResponseEntity(
                    HttpStatus.NOT_FOUND
                    );
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable UUID id) {
        try {
            logger.info("User to delete with ID: " + id);
            this.userService.deleteUser(id);
            logger.info("Deleted user with ID: " + id);
            return new ResponseEntity(
                    HttpStatus.OK
                    );
        } catch (Exception e) {
            logger.error(e);
            return new ResponseEntity(
                    HttpStatus.NOT_FOUND
                    );
        }
    }

    @PutMapping
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
        try {
            logger.info("User to update: " + userDto);
            User user = mapper.convertToEntity(userDto);
            user = userService.updateUserInformation(user);
            logger.info("Updated user: " + user);

            return new ResponseEntity(
                    this.mapper.convertToDto(user),
                    HttpStatus.OK
                    );
        } catch (Exception e) {
            logger.error(e);
            return new ResponseEntity(
                    HttpStatus.NOT_FOUND
                    );
        }
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        logger.info("Retrieving all users");
        List<UserDto> users = this.userService.getAllUsers().stream()
                .map(mapper::convertToDto)
                .collect(Collectors.toList());
        return new ResponseEntity(
                users,
                HttpStatus.OK
                );
    }

    @GetMapping("template")
    public ResponseEntity<UserDto> getTemplate() {
        return new ResponseEntity(
                new UserDto(UUID.randomUUID(), "UserName", "Email@mail.org"),
                HttpStatus.OK
                );
    }
}
