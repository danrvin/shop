package by.itstep.shop.controller.restController;

import by.itstep.shop.dao.model.User;
import by.itstep.shop.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    @PreAuthorize(value = "hasAuthority('user:read')")
    public User getUser(@PathVariable Long id) {
        return userService.findByUserId(id);
    }

    @GetMapping("/all")
    @PreAuthorize(value = "hasAuthority('user:read')")
    public List<User> users() {
        return userService.findAll();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize(value = "hasAuthority('user:write')")
    public List<User> deleteUser(@PathVariable Long id) {
        userService.deleteUser(userService.findByUserId(id));
        return userService.users();
    }

    @PostMapping("/create")
    @PreAuthorize(value = "hasAuthority('user:write')")
    public User createUser(@RequestBody User user) {
        return userService.save(user);
    }

    @PutMapping("/{id}")
    @PreAuthorize(value = "hasAuthority('user:write')")
    public User updateUser(@PathVariable("id") User userFromDb, @RequestBody User user) {
        return userService.updateUser(userFromDb,user);
    }
}
