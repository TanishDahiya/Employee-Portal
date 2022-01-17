package com.nagarro.ticketmanagement;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.ui.Model;
import org.springframework.util.Assert;

import com.app.ticketmanagement.controller.MainController;


@SpringBootTest
class SpringbootApplicationTests {
	
	@Autowired
	MainController mainController;

	@Test
	@DirtiesContext
	void testControllerBean() {
		Assert.notNull(mainController, "Object not null");
	}
	@Test
	void testControllerBeanMethod() {
		Assert.notNull(mainController.home(Mockito.mock(Model.class)),"Object not null");
	}

}
