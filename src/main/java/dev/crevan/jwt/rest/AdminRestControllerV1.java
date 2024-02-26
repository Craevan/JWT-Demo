package dev.crevan.jwt.rest;

import dev.crevan.jwt.dto.UserTo;
import dev.crevan.jwt.model.User;
import dev.crevan.jwt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = AdminRestControllerV1.REST_URL)
@RequiredArgsConstructor
public class AdminRestControllerV1 {

    static final String REST_URL = "/api/v1/admin";

    private final UserService userService;

    @GetMapping("/users/{id}")
    public ResponseEntity<UserTo> getUserById(@PathVariable(name = "id") final Long id) {
        User user = userService.findById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        UserTo result = UserTo.fromUser(user);

        return ResponseEntity.ok(result);
    }
}
