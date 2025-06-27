package com.jtspringproject.services;

import com.jtspringproject.models.*;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jtspringproject.dao.UserDao;
import com.jtspringproject.models.User;
import com.jtspringproject.exceptions.ResourceNotFoundException;

@Service
public class UserService implements IUserService {

	private final UserDao userDao;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public UserService(UserDao userDao, PasswordEncoder passwordEncoder) {
		this.userDao = userDao;
		this.passwordEncoder = passwordEncoder;
	}
	
	public List<User> getUsers(){
		return this.userDao.getAllUser();
	}
	
	public User addUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		try {
			return this.userDao.saveUser(user);
		} catch (DataIntegrityViolationException e) {
			// Translating a generic data exception to a more specific, application-level one.
			throw new IllegalArgumentException("User '" + user.getUsername() + "' already exists.");
		}
	}
	
	public User checkLogin(String username,String password) {
		return this.userDao.getUser(username, password);
	}

	public boolean checkUserExists(String username) {
		return this.userDao.userExists(username);
	}

	public User getUserByUsername(String username) {
		User user = userDao.getUserByUsername(username);
		if (user == null) {
			throw new ResourceNotFoundException("User not found with username: " + username);
		}
		return user;
	}
	
	public User updateUser(User user) {
		return this.userDao.updateUser(user);
	}
}
