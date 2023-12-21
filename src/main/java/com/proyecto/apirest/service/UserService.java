package com.proyecto.apirest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.proyecto.apirest.entities.User;
import com.proyecto.apirest.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	// Get
	public List<User> getUser() {
		return userRepository.findAll();
	}

	public Optional<User> getUser(Long id) {
		return userRepository.findById(id);
	}

	// Save
	/*
	 * public void save(User user) { userRepository.save(user); }
	 */
	public void save(User user) {
		String encryptedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encryptedPassword);
		userRepository.save(user);
	}

	// Update
	/* public User update(User user) {
		return userRepository.save(user);
	} */
	public User update(User user) {
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        return userRepository.save(user);
    }

	// Delete
	public void delete(Long id) {
		userRepository.deleteById(id);
	}

}
