package ar.com.portfolio.api.services;

import ar.com.portfolio.api.models.User;
import ar.com.portfolio.api.repositories.UserRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public ArrayList< User> getAllUser() {
        return (ArrayList< User>) userRepository.findAll();
    }

    public User getUserByID(Long id) {
        return userRepository.findById(id).get();
    }

    public List<User> getUserByPersonId(Long personId) {
        return userRepository.findAllByPersonId(personId);
    }


    public User findById(Long userId) {
        return userRepository.findById(userId).get();
    }



}
