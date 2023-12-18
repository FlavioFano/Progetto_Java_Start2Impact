package application.service;
import application.dto.UserDto;
import application.request.UserRequest;

import java.util.List;

public interface UserService {
    UserRequest createUser(UserRequest userRequest);
    List<UserDto> getAll();
    UserDto getUserById(int id);
}
