package com.trycontrol.user.service;

import com.trycontrol.user.dto.UserDTO;
import com.trycontrol.user.infra.model.User;
import com.trycontrol.user.infra.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    public UserRepository userRepository;

    public ResponseEntity<User> createUser(User userDetails) {
        try{
            Optional<User> alredyExistsUser = userRepository.findByEmailAndPassword(userDetails.getEmail(), userDetails.getPassword());
            if (alredyExistsUser.isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
            }

            User newUser = new User();
            newUser.setName(userDetails.getName());
            newUser.setEmail(userDetails.getEmail());
            newUser.setPassword(userDetails.getPassword());
            newUser.setCreatedAt(LocalDateTime.now());
            newUser.setUpdatedAt(null);

            userRepository.save(newUser);

            return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<User> getUserByLogin(UserDTO userDetails) {
        try{
            Optional<User> foundedUser = userRepository.findByEmailAndPassword(userDetails.email(), userDetails.password());
            if (foundedUser.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            return ResponseEntity.status(HttpStatus.OK).body(foundedUser.get());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<String> deleteUser(UserDTO userDetails){
        try {
            Optional<User> foundedUser = userRepository.findByEmailAndPassword(userDetails.email(), userDetails.password());
            if(foundedUser.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
            }

            User user = foundedUser.get();

            userRepository.deleteById(user.getId());

            return ResponseEntity.status(HttpStatus.OK).body("Usuário deletado com sucesso.");
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<User> updateUser(User userDetails){
        try{
            Optional<User> foundedUser = userRepository.findByEmailAndPassword(userDetails.getEmail(), userDetails.getPassword());
            if(foundedUser.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            User user = foundedUser.get();
            user.setName(userDetails.getName());
            user.setUpdatedAt(LocalDateTime.now());

            userRepository.save(user);

            return ResponseEntity.status(HttpStatus.OK).body(user);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<List<User>> getAllUsers(){
        try{
            List<User> foundedUsers = userRepository.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(foundedUsers);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}
