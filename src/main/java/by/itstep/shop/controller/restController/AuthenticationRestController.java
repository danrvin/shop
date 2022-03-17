package by.itstep.shop.controller.restController;

import by.itstep.shop.dao.model.User;
import by.itstep.shop.dao.model.dto.AuthenticationRequestDTO;
import by.itstep.shop.security.JwtTokenProvider;
import by.itstep.shop.security.SecurityUser;
import by.itstep.shop.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationRestController {

    private final Logger logger = LoggerFactory.getLogger(ItemRestController.class);

    private final AuthenticationManager authenticationManager;
    private final UserServiceImpl userService;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthenticationRestController(AuthenticationManager authenticationManager, UserServiceImpl userService, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequestDTO request, HttpServletResponse response) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            User user = userService.findByUsername(request.getUsername());
            String token = jwtTokenProvider.createToken(request.getUsername(), user.getRole().name());
            Cookie cookie = new Cookie("token", token);
            cookie.setHttpOnly(true);
            cookie.setMaxAge(7 * 24 * 60 * 60);
            response.addCookie(cookie);
            Map<Object, Object> body = new HashMap<>();
            body.put("username", request.getUsername());
            body.put("token", token);
            logger.info("User " + user.getUsername() + " logged in");
            return ResponseEntity.ok(body);
        } catch (AuthenticationException e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>("Invalid email password combination", HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> user(@RequestBody User user)
    {
        try {
            SecurityUser.fromUser(userService.save(user));
            logger.info("User: " + user.getUsername() + " has registered");
            return ResponseEntity.ok().body(user);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response, Principal principal) {
        try {
            SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
            securityContextLogoutHandler.logout(request, response, null);
            return ResponseEntity.ok().body(userService.findByUsername(principal.getName()));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        }

    }
}
