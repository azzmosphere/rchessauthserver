package au.azzmosphere.auth.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class Create {
    @RequestMapping(value = "/create")
    @ResponseBody public Map create(Map<String, String> userdetails) {
        Map<String, String> test = new HashMap<>();

        test.put("test", "test");
        return test;
    }
}
