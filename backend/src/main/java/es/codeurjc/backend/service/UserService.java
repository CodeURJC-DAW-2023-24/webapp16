package es.codeurjc.backend.service;

import es.codeurjc.backend.DTOs.UserDTO;
import es.codeurjc.backend.model.User;
import es.codeurjc.backend.model.UserPasswords;
import es.codeurjc.backend.repository.UserPasswordsRepository;
import es.codeurjc.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserPasswordsRepository userPasswordsRepository;
    @Autowired
    private ConversionService conversionService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> findAllUsers(){return userRepository.findAll();}
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

    public Optional<User> findUserByID(long id){return userRepository.findById(id);}
    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void modUser (User user){
        User DBuser = userRepository.getReferenceById(user.getId());
        if (user.getName() != null){
            DBuser.setName(user.getName());

        }if (user.getFirstName() != null){
            DBuser.setFirstName(user.getFirstName());

        }if (user.getLastName() != null){
            DBuser.setLastName(user.getLastName());

        }if (user.getDateOfBirth() != null){
            DBuser.setDateOfBirth(user.getDateOfBirth());

        }if (user.getPhoneNumber() != null){
            DBuser.setPhoneNumber(user.getPhoneNumber());

        }if (user.getAddress() != null){
            DBuser.setAddress(user.getAddress());

        }if (user.getEmail() != null){
            DBuser.setEmail(user.getEmail());

        }if (user.getGender() != null){
            DBuser.setGender(user.getGender());

        }if (user.getDni() != null){
            DBuser.setDni(user.getDni());

        }if (user.getNickname() != null){
            DBuser.setNickname(user.getName());
        }
        userRepository.save(DBuser);
    }
    public User getUserById(String username, Long userId) {
        if (canAccessUser(username, userId)) {
            return userRepository.findById(userId).orElse(null);
        } else {
            throw new AccessDeniedException("No tienes permiso para acceder a este perfil");
        }
    }

    public User getUserByName(String username) {
        return userRepository.findByName(username).orElse(null);
    }

    public User updateUser(String username, User updatedUser) {
        if (canAccessUser(username, updatedUser.getId())) {
            return userRepository.save(updatedUser);
        } else {
            throw new AccessDeniedException("No tienes permiso para modificar este perfil");
        }
    }
    public void newUser(String name, String email, String password, String date) {
        User userNew = new User(name, passwordEncoder.encode(password), "USER");
        userNew.setEmail(email);
        userNew.setDateOfBirth(date);
        userRepository.save(userNew);

    }
    public void deleteUserByID(long idUser){
        User user = userRepository.getReferenceById(idUser);
        userRepository.delete(user);
    }
    public boolean canAccessUser(String username, Long userId) {
        Optional<User> optionalUser = userRepository.findByName(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return user.getId().equals(userId);
        } else {
            return false;
        }
    }
    public UserDTO convertToDTO(User user) {
        return conversionService.convertToDTO(user, UserDTO.class);
    }

    public User convertToEntity(UserDTO userDTO) {
        return conversionService.convertToEntity(userDTO, User.class);
    }
}
