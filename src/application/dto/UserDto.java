package application.dto;

public class UserDto {
    private Integer userDtoId;
    private String userDtoName;
    private String userDtoLastname;
    private String userDtoBirthDate;

    public Integer getUserDtoId() {
        return userDtoId;
    }

    public void setUserDtoId(Integer userDtoId) {
        this.userDtoId = userDtoId;
    }

    public String getUserDtoName() {
        return userDtoName;
    }

    public void setUserDtoName(String userDtoName) {
        this.userDtoName = userDtoName;
    }

    public String getUserDtoLastname() {
        return userDtoLastname;
    }

    public void setUserDtoLastname(String userDtoLastname) {
        this.userDtoLastname = userDtoLastname;
    }

    public String getUserDtoBirthDate() {
        return userDtoBirthDate;
    }

    public void setUserDtoBirthDate(String userDtoBirthDate) {
        this.userDtoBirthDate = userDtoBirthDate;
    }

    @Override
    public String toString() {
        return  userDtoId + "-" + userDtoName + "-" + userDtoLastname + "-" + userDtoBirthDate;
    }
}
