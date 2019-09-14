package app.stands;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class StandController {

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

}