package com.neueda.payments.control;

import com.neueda.payments.model.User;
import com.neueda.payments.model.UserDTO;
import com.neueda.payments.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping()
    public List<UserDTO> getAllUsers(){
        return userService.getAllUsers().stream().map(user -> new UserDTO(user)).toList();
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Long id){
        return new UserDTO(userService.getUserById(id));
    }

    @PostMapping()
    public UserDTO addUser(@RequestBody User user){
        return new UserDTO(userService.addUser(user));
    }
}
