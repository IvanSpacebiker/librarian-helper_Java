package ru.kazakov.labaratory.library;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.kazakov.labaratory.library.web.BooksController;
import ru.kazakov.labaratory.library.web.EventsController;
import ru.kazakov.labaratory.library.web.ReadersController;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class LibraryApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private BooksController booksController;
	@Autowired
	private ReadersController readersController;
	@Autowired
	private EventsController eventsController;

	@Test
	void contextLoads() throws Exception {
		this.mockMvc.perform(get("/books"))
				.andDo(print())
				.andExpect(status().isOk());
	}

}
