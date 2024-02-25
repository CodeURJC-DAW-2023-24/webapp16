package es.codeurjc.backend.service;

import es.codeurjc.backend.model.User;
import es.codeurjc.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.mindrot.jbcrypt.BCrypt;
import java.util.List;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    private void save(User user){
        userRepository.save(user);
    }
    public void addUser(User user){
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10));
        String hashedNickname = BCrypt.hashpw(user.getNickname(), BCrypt.gensalt(10));
        user.setPassword(hashedPassword);
        user.setNickname(hashedNickname);
        this.save(user);
    }
    public void checkUser(String pass, String nick){
        String hashedPassword = BCrypt.hashpw(pass, BCrypt.gensalt(10));
        String hashedNickname = BCrypt.hashpw(pass, BCrypt.gensalt(10));

    }

}
