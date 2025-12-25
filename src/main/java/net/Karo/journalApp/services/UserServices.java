package net.Karo.journalApp.services;

import net.Karo.journalApp.Etity.User;
import net.Karo.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserServices {

    @Autowired
    private  UserRepository userRepository;



   @Autowired
   private PasswordEncoder passwordEncoder;





    public void saveEntry(User user) {
        // Double encoding se bachaav
        /*if (!user.getPassword().startsWith("$2a$")) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }*/
        userRepository.save(user);
    }


    public  void createNewUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

    }

    public List<User> getAll(){
        return userRepository.findAll();

    }
    public Optional<User> findId(ObjectId id){

        return userRepository.findById(id);
    }


    public  User findByUserName(String userName){

        return userRepository.findByUserName(userName);
    }

}
