package by.itstep.shop.controller;


import by.itstep.shop.dao.model.User;
import by.itstep.shop.dao.model.enums.Role;
import by.itstep.shop.dao.model.enums.Status;
import by.itstep.shop.service.ItemService;
import by.itstep.shop.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Map;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService, ItemService itemService) {
        this.userService = userService;
    }


    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/success")
    public String getSuccessPage() {
        return "success";
    }

    @GetMapping("/registration")
    public String getRegistrationPage() {

        return "registration";
    }

    @PostMapping("/registration")
    public String userRegistration(@RequestBody User user, Map<String, Object> model) {
        User userFromDb = userService.findByUsername(user.getUsername());
        if (userFromDb != null) {
            model.put("message", "User exist!");
            return "registration";
        }
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.setStartMoney(user);
        user.setRole(Role.USER);
        user.setStatus(Status.ACTIVE);
        model.put("money", user.getMoney());
        userService.save(user);
        return "login";
    }

    @PostMapping("/logout")
    public String logout() {
        return "redirect:/auth/login";
    }

}
