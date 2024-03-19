package soa.userservice.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import soa.userservice.dto.UserRequest;
import soa.userservice.dto.UserResponse;
import soa.userservice.models.User;
import soa.userservice.repositories.UserRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepo userRepo;

    public UserResponse getUserById(int id)  {
        User u = userRepo.getUserById(id).orElseThrow(
               () -> new IllegalArgumentException("Get user with id " + id + " request resulted in error. Please try again."));
        return new UserResponse(u.getId(), u.getFirstName(), u.getLastName(), u.getMobileNumber(), u.getEmail(), u.getPassword());
    }

    public List<UserResponse> getAll() {
        List<User> list = userRepo.getAll().orElseThrow(
                () -> new IllegalArgumentException("Get all users request resulted in error. Please try again.")
        );
        return list.stream().map(this::buildResponse).collect(Collectors.toList());
    }

    public UserResponse saveUser(int id, String firstName, String lastName, String email, String mobileNumber, String password) {
        User user = new User(id, firstName, lastName, email, mobileNumber, password);
        userRepo.save(user);
        User fromDB = userRepo.getUserById(id).orElseThrow(
                () -> new IllegalArgumentException("User was not saved! Please try again.")
        );
        return buildResponse(fromDB);
    }

    private UserResponse buildResponse(User u) {
        return new UserResponse(
                u.getId(),
                u.getFirstName(),
                u.getLastName(),
                u.getMobileNumber(),
                u.getEmail(),
                u.getPassword());
    }

    public int deleteUser(int id) {
        User user = userRepo.getUserById(id).orElseThrow(
                () -> new IllegalArgumentException("Deletion of user with id " + id + " resulted in error. Please try again.")
        );
        userRepo.delete(user);
        return user.getId();
    }

    public UserResponse editUser(int id, UserRequest request) {
        User user = userRepo.getUserById(id).orElseThrow(
                () -> new IllegalArgumentException("Get user with id " + id + " request resulted in error. Please try again.")
        );
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setMobileNumber(request.getMobileNumber());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        userRepo.save(user);
        return buildResponse(userRepo.getUserById(id).orElseThrow(
                () -> new IllegalArgumentException("User with id " + id + " could not be saved. Please try again.")
        ));
    }
}
