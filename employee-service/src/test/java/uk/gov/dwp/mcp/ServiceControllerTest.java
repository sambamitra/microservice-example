package uk.gov.dwp.mcp;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(ServiceController.class)
public class ServiceControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void getEmployeeByIdShouldReturnStatus200() throws Exception {
		this.mockMvc.perform(get("/api/employee/1234")).andExpect(status().isOk())
				.andExpect(content().string("Hello Docker World!"));
	}

}
