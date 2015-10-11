package spittr.web;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import static
org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static
org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static
org.springframework.test.web.servlet.setup.MockMvcBuilders.*;



public class HomeControllerTest {

	@Test
	public void testHomePage() throws Exception {
		HomeController controller = new HomeController();
		//set up mock mvc
		MockMvc mockMvc = standaloneSetup(controller).build();
		//perform GET, expect home view
		mockMvc.perform(get("/")).andExpect(view().name("home"));
	}

}
