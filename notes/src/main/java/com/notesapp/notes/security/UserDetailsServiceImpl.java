package com.notesapp.notes.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.notesapp.notes.users.User;
import com.notesapp.notes.users.UserService;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = findUserbyUername(username);

		UserBuilder builder = null;
		if (user != null) {
			builder = org.springframework.security.core.userdetails.User.withUsername(username);
			builder.password(user.getPassword());
			// Every user has only one role i.e to see own notes so not using roles.
			builder.roles("USER_ALLOWED");
		} else {
			throw new UsernameNotFoundException("User not found.");
		}

		return builder.build();

	}

	private User findUserbyUername(String username) {
		if (username != null) {
			User user = userService.getUserByName(username);
			if(user != null) {
				User forSecurity = new User();
				forSecurity.setEmail(user.getEmail());
				forSecurity.setPassword("{noop}"+user.getPassword());
				return forSecurity;
			}
		}
		return null;
	}

}
