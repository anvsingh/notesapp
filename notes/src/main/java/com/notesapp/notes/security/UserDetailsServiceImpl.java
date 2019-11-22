package com.notesapp.notes.security;

import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.notesapp.notes.users.User;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = findUserbyUername(username);

	    UserBuilder builder = null;
	    if (user != null) {
	      builder = org.springframework.security.core.userdetails.User.withUsername(username);
	      //builder.password(new BCryptPasswordEncoder().encode(user.getPassword()));
	      builder.password(user.getPassword());
	      builder.roles("USER_ALLOWED");
	    } else {
	      throw new UsernameNotFoundException("User not found.");
	    }

	    return builder.build();
		
	}
	
	 private User findUserbyUername(String username) {
		    if(username.equalsIgnoreCase("test")) {
		      //return new User(username, "admin123", "ADMIN");
		    	User user = new User();
		    	user.setEmail("test");
		    	user.setPassword("{noop}test");
		    	return user;
		    }
		    return null;
		  }

}
