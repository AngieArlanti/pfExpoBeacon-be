package app.stands;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class StandController {

    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/stands")
    public Stand getStand(@RequestParam(name="name", required=false, defaultValue="0C:F3:EE:08:FC:DD") String name) {
       return new Stand(counter.incrementAndGet(), "The first stand",
               "Some stand with a description",
               "0C:F3:EE:08:FC:DD",
               "http://modular360.es/wp-content/uploads/2016/11/Stand-Basic-6-3-caras.png"  );
    }
}