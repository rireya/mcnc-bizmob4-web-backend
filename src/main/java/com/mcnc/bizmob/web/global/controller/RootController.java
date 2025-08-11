package com.mcnc.bizmob.web.global.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

	@GetMapping("/health")
	 public ResponseEntity<String> healthCheck() {
		return ResponseEntity.ok("BizMOB Web healthCheck ok");
	}
}
