package au.azzmosphere.auth.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class Verify {
    @RequestMapping(path = "/verify")
    @ResponseBody public final Map<String, String> verify(Map<String, String> value) {
        Map<String, String> test = new HashMap<>();

        test.put("test", "test");
        return test;
    }
}
