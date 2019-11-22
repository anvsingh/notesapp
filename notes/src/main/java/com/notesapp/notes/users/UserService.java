package com.notesapp.notes.users;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {

	@Autowired
	UserRepository userRepo;

	public void add(UserDTO dto) {
		userRepo.save(toEntity(dto));
	}

	public void delete(long id) {
		userRepo.deleteById(id);
	}

	public List<User> getUsers() {
		return (List<User>) userRepo.findAll();
	}

	public User getUserById(long id) {
        Optional<User> optionalUser = userRepo.findById(id);
        return optionalUser.get();
    }

	private User toEntity(UserDTO dto) {
		User entity = new User();
		entity.setEmail(dto.getEmail());
		entity.setPassword(dto.getPassword());
		return entity;
	}

}