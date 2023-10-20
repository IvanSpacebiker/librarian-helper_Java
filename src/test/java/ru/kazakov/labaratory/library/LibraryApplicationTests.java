package ru.kazakov.labaratory.library;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.kazakov.labaratory.library.web.BooksController;
import ru.kazakov.labaratory.library.web.EventsController;
import ru.kazakov.labaratory.library.web.ReadersController;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class LibraryApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private BooksController booksController;
	@Autowired
	private ReadersController readersController;
	@Autowired
	private EventsController eventsController;

	private final String test = "A";

	// add book
	@Test
	void step1_1() throws Exception {
		String url = new StringBuilder()
				.append("/books?title=").append(test)
				.append("&author=").append(test)
				.append("&quantity=1").toString();
		this.mockMvc.perform(post(url))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(
						new ObjectMapper().writer().writeValueAsString(
								booksController.getBookByTitleAndAuthor(test,test).getBody().get(0))
				));
	}

	// add reader
	@Test
	void step1_2() throws Exception {
		String url = new StringBuilder()
				.append("/readers?name=").append(test)
				.append("&surname=").append(test)
				.toString();
		this.mockMvc.perform(post(url))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(
						new ObjectMapper().writer().writeValueAsString(
								readersController.getReaderByNameAndSurname(test,test).getBody().get(0))
				));
	}

	// add take event
	@Test
	void step1_3() throws Exception {
		UUID bookid = booksController.getBookByTitleAndAuthor(test,test).getBody().get(0).id();
		UUID readerid = readersController.getReaderByNameAndSurname(test,test).getBody().get(0).id();

		String url = new StringBuilder()
				.append("/events?bookid=").append(bookid)
				.append("&readerid=").append(readerid)
				.append("&type=TAKE").toString();

		this.mockMvc.perform(post(url))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(
						new ObjectMapper()
								.setDateFormat(new StdDateFormat())
								.writer().writeValueAsString(eventsController.getAllEvents().getBody().get(0))
				));
	}

	// take book when its amount is 0
	@Test
	void step2_1() throws Exception {
		UUID bookid = booksController.getBookByTitleAndAuthor(test,test).getBody().get(0).id();
		UUID readerid = readersController.getReaderByNameAndSurname(test,test).getBody().get(0).id();

		String url = new StringBuilder()
				.append("/events?bookid=").append(bookid)
				.append("&readerid=").append(readerid)
				.append("&type=TAKE").toString();

		this.mockMvc.perform(post(url))
				.andDo(print())
				.andExpect(status().isConflict());
	}

	// add return event
	@Test
	void step2_2() throws Exception {

		UUID bookid = booksController.getBookByTitleAndAuthor(test,test).getBody().get(0).id();
		UUID readerid = readersController.getReaderByNameAndSurname(test,test).getBody().get(0).id();

		String url = new StringBuilder()
				.append("/events?bookid=").append(bookid)
				.append("&readerid=").append(readerid)
				.append("&type=RETURN").toString();

		this.mockMvc.perform(post(url))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(
						new ObjectMapper()
								.setDateFormat(new StdDateFormat())
								.writer().writeValueAsString(eventsController.getAllEvents().getBody().get(1))
				));
	}



}
