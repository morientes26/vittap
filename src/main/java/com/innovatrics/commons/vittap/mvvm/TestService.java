package com.innovatrics.commons.vittap.mvvm;

import org.springframework.stereotype.Service;

@Service
public class TestService {


	public String getName() {
		return "date " + System.nanoTime();
	}

}
