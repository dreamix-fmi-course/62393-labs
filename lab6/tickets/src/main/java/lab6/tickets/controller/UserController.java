package lab6.tickets.controller;

import lab6.tickets.logger.Logger;
import lab6.tickets.model.User;
import lab6.tickets.service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private Logger logger;

    @PostMapping(
            value = "create",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<User> createUser(@RequestBody User user) {
        this.userService.createUser(user);
        logger.info("Created user " + user);

        return new ResponseEntity(
                user,
                HttpStatus.CREATED
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<User> findById(@PathVariable UUID id) {
        return new ResponseEntity(
                this.userService.findById(id),
                HttpStatus.OK
        );
    }

    @DeleteMapping("{id}")
    public ResponseEntity<User> deleteUser(@PathVariable UUID id) {
        this.userService.deleteUser(id);

        return new ResponseEntity(
                "Deleted successfully",
                HttpStatus.OK
        );
    }
}
