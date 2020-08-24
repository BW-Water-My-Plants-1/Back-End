package com.dgbuild.watermyplants.controllers;

import com.dgbuild.watermyplants.models.User;
import com.dgbuild.watermyplants.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    // http://localhost:2019/users/users
    @GetMapping(value = "/users",
            produces = "application/json")
        public ResponseEntity<?> listAllUsers(){
        List<User> userList = userService.findAll();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    // http://localhost:2019/users/user/{id}
    @GetMapping(value = "/user/{userId}",
            produces = "application/json")
    public ResponseEntity<?> findUserById(@PathVariable long userId){
        User getUser = userService.findById(userId);
        return new ResponseEntity<>(getUser, HttpStatus.OK);
    }

    // http://localhost:2019/users/myinfo
    @GetMapping(value = "/myinfo", produces = "application/json")
    public ResponseEntity<?> findCurrentUserInfo(){
        User currentUser = userService.getCurrentUser();
        return new ResponseEntity<>(currentUser, HttpStatus.OK);
    }

    // POST http://localhost:2019/users/user
    @PostMapping(value = "/user",
            consumes = "application/json",
            produces = "application/json")
    public ResponseEntity<?> addNewUser(@Valid @RequestBody User newUser){
        newUser.setUserid(0);
        newUser = userService.save(newUser);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    // PUT http://localhost:2019/users/user/{id}
    @PutMapping(value = "/user/{userId}",
                consumes = "application/json",
                produces = "application/json")
    public ResponseEntity<?> updateFullUser(@Valid @PathVariable long userId, @RequestBody User updateUser){
        updateUser.setUserid(userId);
        updateUser = userService.save(updateUser);
        return new ResponseEntity<>(updateUser, HttpStatus.CREATED);
    }

    // PATCH http://localhost:2019/users/user/{id}
    @PatchMapping(value = "/user/{userId}",
                consumes = "application/json",
                produces = "application/json")
    public ResponseEntity<?> updateUser(@RequestBody User updateUser, @PathVariable long userId){
        updateUser = userService.update(updateUser, userId);
        return new ResponseEntity<>(updateUser, HttpStatus.CREATED);
    }

    // DELETE http://localhost:2019/users/user/{id}
    @DeleteMapping(value = "/user/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable long userId){
        userService.delete(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
