package au.azzmosphere.auth.controllers;

import au.azzmosphere.auth.persistence.enitites.User;
import au.azzmosphere.auth.services.RChessJwtTokenFactory;
import au.azzmosphere.auth.services.RChessUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
public class Create {

    private RChessUserDetailsService userDetailsService;
    private RChessJwtTokenFactory rChessJwtTokenFactory;
    private static final Logger logger = LoggerFactory.getLogger(RChessUserDetailsService.class);

    @Autowired
    public void setrChessJwtTokenFactory(RChessJwtTokenFactory rChessJwtTokenFactory) {
        this.rChessJwtTokenFactory = rChessJwtTokenFactory;
    }


    @Autowired
    public void setUserDetailsService(RChessUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    /**
     *
     * Gets user details attempts to create the user, if successful, returns a token ID.
     *
     * This token should be used in the bearer header on consequent communications
     * with the server.
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/user/registration", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody public Map create(@RequestBody @Valid User user) {
        userDetailsService.registerUser(user);

        logger.debug("creating JWT access token");
        String jwtToken = rChessJwtTokenFactory.createJwtToken(user);
        Map<String, String> response = new HashMap<>();
        response.put("bearer", jwtToken);
        response.put("status", "SUCCESS");

        return response;
    }

    /**
     * Handles various types of exceptions in a secure way.
     *
     * @return
     */
    @ExceptionHandler
    @ResponseBody public Map handleException() {
        Map<String, String>  response = new HashMap<>();

        response.put("error", "error");


        return response;
    }
}
