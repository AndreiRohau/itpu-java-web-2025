package com.arohau.webmvc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	private Long userId;
	private String name;
	private String login;
	private String password1;
	private String password2;
}

