package es.codeurjc.backend.service;

import es.codeurjc.backend.model.User;
import es.codeurjc.backend.model.UserPasswords;
import es.codeurjc.backend.repository.UserPasswordsRepository;
import es.codeurjc.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserPasswordsRepository userPasswordsRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private void save(User user){
        this.userRepository.save(user);
    }
    public void addUser(User user){
        String hashedPassword = BCrypt.hashpw(user.getEncodedPassword(), BCrypt.gensalt(10));
        UserPasswords userPasswords = new UserPasswords();
        userPasswords.setPass(hashedPassword);
        userPasswords.setUser(user.getNickname());
        this.saveCredentials(userPasswords);

        user.setEncodedPassword(hashedPassword);//En un futuro hay que quitar pass de user
        userRepository.save(user);
    }
    public boolean checkUser(String pass, String nick){
        System.out.println(nick);
        String BDpassword = this.userRepository.getUserCredential(nick);
        if (BCrypt.checkpw(pass, BDpassword)){
            System.out.println("Coincidence took place");
            return true;
        }
        return false;
    }
    private void saveCredentials(UserPasswords userPasswords){
        this.userPasswordsRepository.save(userPasswords);
    }

    public Optional<User> findUserByName(String userName){return userRepository.findByName(userName);}
    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void newUser(String name, String email, String password, String date) {
        User userNew = new User(name, passwordEncoder.encode(password), "USER");
        userNew.setEmail(email);
        userNew.setDateOfBirth(date);
        userRepository.save(userNew);

    }
}
