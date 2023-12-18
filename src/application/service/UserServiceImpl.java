package application.service;

import application.entity.User;
import application.dao.UserDao;
import application.dao.UserDaoImpl;
import application.dto.UserDto;
import application.request.UserRequest;
import application.utils.Converter;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    UserDao userDao= new UserDaoImpl();

    @Override
    public UserRequest createUser(UserRequest userRequest) {
        Converter converter= new Converter();
        User userSendInDao = userDao.createUser(converter.fromUserRequestToUSer(userRequest));
        return converter.fromUserToUSerRequest(userSendInDao);
    }

    @Override
    public List<UserDto> getAll() {
        List<User> usersList = userDao.getAll();
        return convertUsersListIntoUsersDtoList(usersList);
    }

    private List<UserDto> convertUsersListIntoUsersDtoList(List<User> usersList) {
        List<UserDto> usersDtoList = new ArrayList<>();
        UserDto userDto;

        for (User user : usersList) {
            userDto= Converter.fromUserToUserDto(user);
            usersDtoList.add(userDto);
        }
        return usersDtoList;
    }

    @Override
    public UserDto getUserById(int id) {
        return getAll().stream().filter(userDto ->userDto.getUserDtoId().equals(id)).findAny().orElse(null);
    }


}
