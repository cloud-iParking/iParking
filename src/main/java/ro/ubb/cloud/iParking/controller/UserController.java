package ro.ubb.cloud.iParking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.cloud.iParking.service.UserService;
import ro.ubb.cloud.iParking.service.dto.UserDTO;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/user")
@Produces("application/json")
@Consumes("application/json;charset=UTF-8")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> saveUser(@RequestBody UserDTO userDTO) {
        UserDTO response = userService.save(userDTO);
        if (response != null) {
            return ResponseEntity.ok().body("User saved!");
        }
        return ResponseEntity.badRequest().body("User save failed!");
    }
}