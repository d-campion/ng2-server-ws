package com.dimitri.angular.middleware.rest;

import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dimitri.angular.core.dto.CredentialDTO;
import com.dimitri.angular.core.dto.UserDTO;

/**
 * 
 * @author Dimitri <campion.dimitri@gmail.com>
 * @version 0.0.1
 *
 */
@RequestMapping("/users")
@RestController
public class UserController {
	
	@RequestMapping(value="/authenticate",method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public UserDTO authenticate(HttpSession session, @RequestBody CredentialDTO credential){
	
		UserDTO userDto = new UserDTO();
		
		userDto.setNom("CAMPION");
		userDto.setPrenom("Dimtri");
		userDto.setUsername(credential.getUsername());
		userDto.setPassword(credential.getPassword());
		
		if(credential.getUsername() == null || credential.getPassword() == null || 
				( !credential.getUsername().equals("admin") && !credential.getPassword().equals("password") ))
			return null;
		
		return userDto;
	}
	
}
