package theKazantsev.RESTful_Calulator_02;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class RESTfulCalulator02Tests {

	@Autowired
	private MockMvc mockMvc;

	@ParameterizedTest
	@CsvSource({
		"1, 2, 3",
		"5, 10.1, 15.1",
		"0, -10, -10"
	})
	void addTest(double arg1, double arg2, double expectedSum) throws Exception {

		this.mockMvc
			.perform(get("/add").param("arg1", String.valueOf(arg1)).param("arg2", String.valueOf(arg2)))
			.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.result").value(String.valueOf(expectedSum)));
	}

	@Test
	void divideByZeroTest() throws Exception {
		double arg1 = 1;
		double arg2 = 0;

		this.mockMvc
			.perform(get("/divide").param("arg1", String.valueOf(arg1)).param("arg2", String.valueOf(arg2)))
			.andDo(print())
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.error").value("Can't divide by zero"));
	}
}
