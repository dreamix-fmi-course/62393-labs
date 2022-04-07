package lab6.tickets.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@RestController
@RequestMapping("dummy")
public class DummyController {

    @GetMapping("some-method")
    public String someMethod() {
        return "Some string";
    }

    @GetMapping("some-method-with-args")
    public String someMethodWithArgs(String arg) {
        return "Some string but with " + arg;
    }

    @PostMapping("body-receiver")
    public String bodyReceiver(@RequestBody String body) {
        return "Some string but posting " + body;
    }
}
