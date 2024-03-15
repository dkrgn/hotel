package soa.userservice.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import soa.userservice.models.User;
import soa.userservice.repositories.UserRepo;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepo userRepo;

    public User getUserById(int id)  {
        return userRepo.getUserById(id).orElse(new User()); //validate optional better
    }

    public List<User> getAll() {
        return userRepo.getAll().orElse(new ArrayList<>());
    }

    public User saveUser(int id, String firstName, String lastName, String email, String mobileNumber) {
        User user = new User(id, firstName, lastName, email, mobileNumber);
        userRepo.save(user);
        return userRepo.getUserById(id).orElse(new User()); //validate optional better
    }

    public int deleteUser(int id) {
        User user = userRepo.getUserById(id).orElse(null);
        if (user == null) {
            return -1;
        }
        userRepo.delete(user);
        return user.getId();
    }
}
