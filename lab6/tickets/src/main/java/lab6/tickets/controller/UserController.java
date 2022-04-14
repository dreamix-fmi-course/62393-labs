package lab6.tickets.controller;

import lab6.tickets.dto.EventDto;
import lab6.tickets.dto.UserDto;
import lab6.tickets.logger.Logger;
import lab6.tickets.mapper.EventDtoMapper;
import lab6.tickets.mapper.UserDtoMapper;
import lab6.tickets.model.User;
import lab6.tickets.service.UserService;
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
    private UserDtoMapper userMapper;
    @Autowired
    private EventDtoMapper eventMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private Logger logger;

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        try {
            logger.info("User to create: " + userDto);
            User user = this.userMapper.convertToEntity(userDto);
            this.userService.createUser(user);
            logger.info("Created user: " + user);

            return new ResponseEntity(
                    this.userMapper.convertToDto(user),
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
            UserDto userDto = this.userMapper.convertToDto(this.userService.findById(id));
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
            User user = userMapper.convertToEntity(userDto);
            userService.updateUserInformation(user);
            logger.info("Updated user: " + user);

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

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        logger.info("Retrieving all users");
        List<User> users = this.userService.getAllUsers();
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
