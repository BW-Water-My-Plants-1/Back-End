package com.dgbuild.watermyplants.services;

import com.dgbuild.watermyplants.exceptions.ResourceNotFoundException;
import com.dgbuild.watermyplants.models.Plant;
import com.dgbuild.watermyplants.models.Role;
import com.dgbuild.watermyplants.models.User;
import com.dgbuild.watermyplants.models.UserRoles;
import com.dgbuild.watermyplants.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.resource.OAuth2AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Transactional
@Service(value = "userService")
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HelperFunctions helperFunctions;

    @Autowired
    private RoleService roleService;

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }

    @Override
    public void delete(long id) {
        userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User " + id + " Not Found"));
//        if (helperFunctions.getCurrentAuditor() != "SYSTEM" || !helperFunctions.isAuthorizedToMakeChange(userRepository.findById(id).get().getUsername())){
//            throw new OAuth2AccessDeniedException("You cannot delete other users");
//        }
        userRepository.deleteById(id);
    }

    @Transactional
    @Override
    public User save(User user) {
        User newUser = new User();

        if (user.getUserid() != 0){
            userRepository.findById(user.getUserid())
                    .orElseThrow(() -> new ResourceNotFoundException("User " + user.getUserid() + " Not Found"));
            newUser.setUserid(user.getUserid());
        }

        newUser.setEmail(user.getEmail());

        newUser.setPassword(user.getPassword());

        newUser.setPhone(user.getPhone());

        newUser.setUsername(user.getUsername());

        newUser.getPlants().clear();

        for (Plant p : user.getPlants()) {
            newUser.getPlants().add(new Plant(p.getNickname(), p.getSpecies(),
                            p.getFrequency(), newUser));
        }

        newUser.getRoles().clear();

        for (UserRoles ur: user.getRoles()){
            Role addRole = roleService.findById(ur.getRole().getRoleid());

            newUser.getRoles().add(new UserRoles(newUser, addRole));
        }
        newUser = userRepository.save(newUser);
//        if (helperFunctions.getCurrentAuditor() != "SYSTEM" || !helperFunctions.isAuthorizedToMakeChange(newUser.getUsername())){
//            throw new OAuth2AccessDeniedException("You cannot change other users");
//        }
        return newUser;
    }

    @Transactional
    @Override
    public User update(User updateUser, long id) {
        User newUser = new User();

        userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User " + id + " Not Found"));
        newUser.setUserid(id);

        User currentUser = userRepository.findById(id).get();

        if (updateUser.getEmail() != null){
            newUser.setEmail(updateUser.getEmail());
        }else{
            newUser.setEmail(currentUser.getEmail());
        }

        if (updateUser.getPassword() != null){
            newUser.setPassword(updateUser.getPassword());
        }else{
            newUser.setPassword(currentUser.getPassword());
        }

        if (updateUser.getPhone() != null){
            newUser.setPhone(updateUser.getPhone());
        }else{
            newUser.setPhone(currentUser.getPhone());
        }

        if (updateUser.getUsername() != null){
            newUser.setUsername(updateUser.getUsername());
        }else{
            newUser.setUsername(currentUser.getUsername());
        }

        for (Plant p : currentUser.getPlants()) {
            newUser.getPlants().add(p);
        }

        for (Plant p : updateUser.getPlants()) {
            newUser.getPlants().add(new Plant(p.getNickname(), p.getSpecies(),
                                p.getFrequency(), userRepository.findById(id).get()));
        }

        newUser.getRoles().clear();

        Set<UserRoles> URSet = new HashSet<>();
        updateUser.getRoles().iterator().forEachRemaining(URSet::add);
        updateUser.getRoles().clear();

        newUser.getRoles().clear();

        for (UserRoles ur: currentUser.getRoles()){
            newUser.getRoles().add(ur);
        }
        for (UserRoles ur: updateUser.getRoles()){
            newUser.getRoles().add(new UserRoles(ur.getUser(), ur.getRole()));
        }

//        if (helperFunctions.getCurrentAuditor() != "SYSTEM" || !helperFunctions.isAuthorizedToMakeChange(newUser.getUsername())) {
//            throw new OAuth2AccessDeniedException("You cannot change other users");
//        }
            return userRepository.save(newUser);
    }

    @Override
    public List<User> findAll() {
        List<User> userList = new ArrayList<>();
        userRepository.findAll().iterator().forEachRemaining(userList::add);
        return userList;
    }

    @Override
    public User findById(long id) {
        User findUser = userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("User " + id + " Not Found"));
        return findUser;
    }

    @Override
    public User getCurrentUser() {
        User currentUser = userRepository.findByUsername(helperFunctions.getCurrentAuditor());
        return currentUser;
    }

//    @Override
//    public User updateCurrentUser(User newInfo) {
//        User currentUser = userRepository.findByUsername(helperFunctions.getCurrentAuditor());
//        User newUser = new User();
//
//        if (newInfo.getEmail() != null){
//            currentUser.setEmail(newInfo.getEmail());
//        }else{
//            newUser.setEmail(currentUser.getEmail());
//        }
//
//        if (newInfo.getPassword() != null){
//            newUser.setPassword(newInfo.getPassword());
//        }else{
//            newUser.setPassword(currentUser.getPassword());
//        }
//
//        if (newInfo.getPhone() != null){
//            newUser.setPhone(newInfo.getPhone());
//        }else{
//            newUser.setPhone(currentUser.getPhone());
//        }
//
//        if (newInfo.getUsername() != null){
//            newUser.setUsername(newInfo.getUsername());
//        }else{
//            newUser.setUsername(currentUser.getUsername());
//        }
//
//        for (Plant p : currentUser.getPlants()) {
//            newUser.getPlants().add(p);
//        }
//
//        for (Plant p : newInfo.getPlants()) {
//            newUser.getPlants().add(new Plant(p.getNickname(), p.getSpecies(),
//                    p.getFrequency(), currentUser));
//        }
//
//        newUser.getRoles().clear();
//
//        Set<UserRoles> URSet = new HashSet<>();
//        newInfo.getRoles().iterator().forEachRemaining(URSet::add);
//        newInfo.getRoles().clear();
//
//        newUser.getRoles().clear();
//
//        for (UserRoles ur: currentUser.getRoles()){
//            newUser.getRoles().add(ur);
//        }
//        for (UserRoles ur: newInfo.getRoles()){
//            newUser.getRoles().add(new UserRoles(ur.getUser(), ur.getRole()));
//        }
//
//        if (helperFunctions.isAuthorizedToMakeChange())
//
//        return userRepository.save(newUser);
//    }
}
