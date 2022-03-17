package by.itstep.shop.security;


import by.itstep.shop.dao.model.User;
import by.itstep.shop.dao.repo.UserRepo;
import by.itstep.shop.service.exceptions.InvalidUserException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepo userRepo;

    public UserDetailsServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
            User user = userRepo.findByEmail(email).orElseThrow(() ->
                    new InvalidUserException("User doesn't exist"));
            return SecurityUser.fromUser(user);

    }
}