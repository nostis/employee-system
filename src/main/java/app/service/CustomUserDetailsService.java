package app.service;

import app.dao.UserDAOCrud;
import app.model.CustomUserDetails;
import app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDAOCrud userDAOCrud;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<User> userOptional = userDAOCrud.findByLogin(login);

        userOptional
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return userOptional.map(CustomUserDetails::new).get();
    }
}
