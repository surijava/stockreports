package com.stockmarket.controllers;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.stockmarket.connections.MySqlConnection;

@Controller
public class LoginController {

	@RequestMapping("/login")
	public ModelAndView login() throws SQLException {

		String message = "Hello World, Spring 3.0!";
		
		Connection connection = MySqlConnection.getConnection();
		System.out.println(connection.isClosed());
		
		return new ModelAndView("create", "message", message);
	}
}
