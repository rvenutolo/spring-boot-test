package org.venutolo.spring.test;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleRestController {

    @RequestMapping("/")
    public String index() {
        return "Example Rest Controller index response";
    }

}
