package application.utils;

import application.entity.User;
import application.dto.UserDto;
import application.request.UserRequest;
import application.service.UserService;
import application.service.UserServiceImpl;

public class Converter {
    public UserService userService= new UserServiceImpl();
    public static UserDto fromUserToUserDto(User source) {

        UserDto destination = new UserDto();
        destination.setUserDtoId(source.getUserId());
        destination.setUserDtoName(source.getNome());
        destination.setUserDtoLastname(source.getCognome());
        destination.setUserDtoBirthDate(source.getDataDiNascita());

        return destination;
    }

    public static User fromUserDtoToUser(UserDto source) {

        User destination= new User();
        destination.setUserId(source.getUserDtoId());
        destination.setNome(source.getUserDtoName());
        destination.setCognome(source.getUserDtoLastname());
        destination.setDataDiNascita(source.getUserDtoBirthDate());

        return destination;
    }

    public User fromUserRequestToUSer(UserRequest origin){
        User destination = new User();
        //in "ingresso" non viene piazzato alcun id al Request in quanto è nel Dao che viene assegnato e restituito con ID in "uscita"
        destination.setNome(origin.getNome());
        destination.setCognome(origin.getCognome());
        destination.setDataDiNascita(origin.getDataDiNascita());
        destination.setIndirizzo(origin.getIndirizzo());
        destination.setIdDocumento(origin.getIdDocumento());
        return destination;
    }

    public UserRequest fromUserToUSerRequest(User origin){
        UserRequest destination = new UserRequest();
        destination.setId(origin.getUserId());
        destination.setNome(origin.getNome());
        destination.setCognome(origin.getCognome());
        destination.setDataDiNascita(origin.getDataDiNascita());
        destination.setIndirizzo(origin.getIndirizzo());
        destination.setIdDocumento(origin.getIdDocumento());
        return destination;
    }
}
