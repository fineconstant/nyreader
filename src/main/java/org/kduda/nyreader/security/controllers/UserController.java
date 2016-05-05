package org.kduda.nyreader.security.controllers;

import org.kduda.nyreader.common.user.UserDetailsServiceImpl;
import org.kduda.nyreader.security.JwtUser;
import org.kduda.nyreader.security.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {
	@Value("${jwt.token.header}")
	private String TOKEN_HEADER;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@RequestMapping(value = "user", method = RequestMethod.GET)
	public JwtUser getAuthenticatedUser(HttpServletRequest request) {
		String token = request.getHeader(TOKEN_HEADER);
		String username = jwtTokenUtil.getUsernameFromToken(token);
		return (JwtUser) userDetailsService.loadUserByUsername(username);
	}

}