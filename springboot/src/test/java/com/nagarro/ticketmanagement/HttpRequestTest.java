package com.nagarro.ticketmanagement;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;

//@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
//public class HttpRequestTest {
//	
//	@LocalServerPort
//	private int port;
//	
//	@Autowired
//	private TestRestTemplate testRestTemplate;
//	
//	@Test
//	void testWebRequest() {
//		assertThat(this.testRestTemplate.getForObject("http://localhost:"+ port + "/", String.class))
//		.contains("Ticket Management Application");
//	}

   @WebMvcTest
   public class HttpRequestTest{
	   

	   
	   @Autowired
	   MockMvc mockMvc;
	   
	   @Test
	   void testWebRequest() throws Exception {
		   assertThat(this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk()));
		   
	   }
	
}
