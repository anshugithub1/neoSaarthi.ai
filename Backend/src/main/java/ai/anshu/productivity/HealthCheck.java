package ai.anshu.productivity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {

    @GetMapping("/health-check")
    public static String heathcheck(){
        return "Hello world";
    }

}
