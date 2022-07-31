package ar.com.portfolio.api.repositories;

import ar.com.portfolio.api.models.User;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  UserRepository extends JpaRepository <User, Long>{

    public List<User> findAllByPersonId(Long personId);

    public ArrayList<User> findByUser(String user);

    public List<User> findByUserAndPass(String user, String pass);

}
