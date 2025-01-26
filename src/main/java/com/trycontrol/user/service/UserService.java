package com.trycontrol.user.service;

import com.trycontrol.user.dto.UserDTO;
import com.trycontrol.user.dto.UserDetailsDTO;
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

    public ResponseEntity<UserDetailsDTO> createUser(User user) {
        try{
            Optional<User> alredyExistsUser = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());

            if (alredyExistsUser.isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
            }

            User newUser = new User();
            newUser.setName(user.getName());
            newUser.setEmail(user.getEmail());
            newUser.setPassword(user.getPassword());
            newUser.setCreatedAt(LocalDateTime.now());
            newUser.setUpdatedAt(null);

            userRepository.save(newUser);

            UserDetailsDTO userDetailsDTO = new UserDetailsDTO(
                    newUser.getName(),
                    newUser.getEmail(),
                    newUser.getCreatedAt(),
                    newUser.getUpdatedAt()
            );

            return ResponseEntity.status(HttpStatus.CREATED).body(userDetailsDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<UserDetailsDTO> getUserByLogin(UserDTO userDetails) {
        try{
            Optional<User> foundedUser = userRepository.findByEmailAndPassword(userDetails.email(), userDetails.password());

            if (foundedUser.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            User user = foundedUser.get();
            UserDetailsDTO userDetailsDTO = new UserDetailsDTO(
                    user.getName(),
                    user.getEmail(),
                    user.getCreatedAt(),
                    user.getUpdatedAt()
            );

            return ResponseEntity.status(HttpStatus.OK).body(userDetailsDTO);
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

    public ResponseEntity<UserDetailsDTO> updateUserDetails(UserDTO updateUserDTO) {
        try {
            Optional<User> foundedUser = userRepository.findByEmailAndPassword(updateUserDTO.email(), updateUserDTO.password());
            if (foundedUser.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            User user = foundedUser.get();

            if (!user.getPassword().equals(updateUserDTO.password())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
            }

            user.setName(updateUserDTO.name());
            user.setUpdatedAt(LocalDateTime.now());

            userRepository.save(user);

            UserDetailsDTO userDetails = new UserDetailsDTO(
                    user.getName(),
                    user.getEmail(),
                    user.getCreatedAt(),
                    user.getUpdatedAt()
            );

            return ResponseEntity.ok(userDetails);
        } catch (RuntimeException e) {
            throw e;
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
