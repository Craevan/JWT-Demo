package dev.crevan.jwt.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dev.crevan.jwt.model.User;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserTo {

    private Long id;
    private String username;
    private String firstname;
    private String lastname;
    private String email;

    public User toUser() {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setEmail(email);

        return user;
    }

    public static UserTo fromUser(final User user) {
        UserTo userTo = new UserTo();
        userTo.setId(user.getId());
        userTo.setUsername(user.getUsername());
        userTo.setFirstname(user.getFirstname());
        userTo.setLastname(user.getLastname());
        userTo.setEmail(user.getEmail());

        return userTo;
    }
}
