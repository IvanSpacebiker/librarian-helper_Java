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
import ru.kazakov.labaratory.library.entity.EventType;
import ru.kazakov.labaratory.library.web.BooksController;
import ru.kazakov.labaratory.library.web.EventsController;
import ru.kazakov.labaratory.library.web.ReadersController;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
	private final String testEdit = "B";

	private UUID getBookId() {
		return booksController.getBookByTitleAndAuthor(test,test).getBody().get(0).id();
	}

	private UUID getReaderId() {
		return readersController.getReaderByNameAndSurname(test,test).getBody().get(0).id();
	}


	// ========================================== Books and Readers tests ==============================================
	@Test
	// add book
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

	@Test
	// add book that was already added
	void step1_2() throws Exception {
		String url = new StringBuilder()
				.append("/books?title=").append(test)
				.append("&author=").append(test)
				.append("&quantity=1").toString();
		this.mockMvc.perform(post(url))
				.andDo(print())
				.andExpect(status().isConflict());
	}

	@Test
	// wrong book id
	void step1_3() throws Exception {
		String url = new StringBuilder().append("/books/").append(UUID.randomUUID()).toString();
		this.mockMvc.perform(get(url))
				.andDo(print())
				.andExpect(status().isNotFound());
	}

	@Test
	// add reader
	void step1_4() throws Exception {
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

	@Test
	// wrong reader id
	void step1_5() throws Exception {
		String url = new StringBuilder().append("/readers/").append(UUID.randomUUID()).toString();
		this.mockMvc.perform(get(url))
				.andDo(print())
				.andExpect(status().isNotFound());
	}

	@Test
	// edit book
	void step1_6() throws Exception {
		UUID bookid = getBookId();
		String url = new StringBuilder()
				.append("/books/").append(bookid)
				.append("?title=").append(testEdit)
				.append("&author=").append(testEdit)
				.append("&quantity=5").toString();
		this.mockMvc.perform(put(url))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(
						new ObjectMapper().writer().writeValueAsString(
								booksController.getBookByTitleAndAuthor(testEdit,testEdit).getBody().get(0))
				));
		booksController.editBook(bookid, test, test, 1);
	}

	@Test
	// edit reader
	void step1_7() throws Exception {
		UUID readerid = getReaderId();
		String url = new StringBuilder()
				.append("/readers/").append(readerid)
				.append("?name=").append(testEdit)
				.append("&surname=").append(testEdit)
				.toString();
		this.mockMvc.perform(put(url))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(
						new ObjectMapper().writer().writeValueAsString(
								readersController.getReaderByNameAndSurname(testEdit,testEdit).getBody().get(0))
				));
		readersController.editReader(readerid, test, test);
	}


	// ========================================== Events tests ====================================================

	@Test
	// wrong event id
	void step2_1() throws Exception {

		String url = new StringBuilder().append("/events/").append(UUID.randomUUID()).toString();

		this.mockMvc.perform(get(url))
				.andDo(print())
				.andExpect(status().isNotFound());
	}

	@Test
	// add take event
	void step2_2() throws Exception {
		UUID bookid = getBookId();
		UUID readerid = getReaderId();

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

	@Test
	// take book when its amount is 0
	void step2_3() throws Exception {
		UUID bookid = getBookId();
		UUID readerid = getReaderId();

		String url = new StringBuilder()
				.append("/events?bookid=").append(bookid)
				.append("&readerid=").append(readerid)
				.append("&type=TAKE").toString();

		this.mockMvc.perform(post(url))
				.andDo(print())
				.andExpect(status().isConflict());
	}


	@Test
	// add return event
	void step2_4() throws Exception {

		UUID bookid = getBookId();
		UUID readerid = getReaderId();

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

	@Test
	// add event with wrong book and author
	void step2_5() throws Exception {

		UUID bookid = UUID.randomUUID();
		UUID readerid = UUID.randomUUID();

		String url = new StringBuilder()
				.append("/events?bookid=").append(bookid)
				.append("&readerid=").append(readerid)
				.append("&type=TAKE").toString();

		this.mockMvc.perform(post(url))
				.andDo(print())
				.andExpect(status().isNotFound());
	}

	// ========================================== Top tests ====================================================

	@Test
	// get top book
	void step3_1() throws Exception {

		booksController.editBook(getBookId(), "A", "A", 3);
		booksController.addBook("B","B",1);
		booksController.addBook("C","C",1);

		readersController.addReader("B","B");
		readersController.addReader("C","C");

		eventsController.addEvent(
				getBookId(),
				getReaderId(),
				EventType.TAKE
		);
		eventsController.addEvent(
				getBookId(),
				getReaderId(),
				EventType.TAKE
		);
		eventsController.addEvent(
				booksController.getBookByTitleAndAuthor("B", "B").getBody().get(0).id(),
				readersController.getReaderByNameAndSurname("B", "B").getBody().get(0).id(),
				EventType.TAKE
		);
		eventsController.addEvent(
				booksController.getBookByTitleAndAuthor("C", "C").getBody().get(0).id(),
				readersController.getReaderByNameAndSurname("C", "C").getBody().get(0).id(),
				EventType.TAKE
		);

		String url = "/books/top";

		this.mockMvc.perform(get(url))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(
						new ObjectMapper()
								.setDateFormat(new StdDateFormat())
								.writer().writeValueAsString(booksController.getTopBook("", "").getBody())
				));
	}

	@Test
	// get top reader
	void step3_2() throws Exception {

		String url = "/readers/top";

		this.mockMvc.perform(get(url))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(
						new ObjectMapper()
								.setDateFormat(new StdDateFormat())
								.writer().writeValueAsString(readersController.getTopReader("", "").getBody())
				));
	}

	@Test
		// get top book from time
	void step3_3() throws Exception {

		LocalDateTime timestamp = eventsController.getAllEvents().getBody().get(3).time().toLocalDateTime();
		String url = new StringBuilder().append("/books/top?from=").append(timestamp).toString();

		this.mockMvc.perform(get(url))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(
						new ObjectMapper()
								.setDateFormat(new StdDateFormat())
								.writer().writeValueAsString(booksController.getTopBook(timestamp.toString(), "").getBody())
				));
	}

	@Test
		// get top reader from time
	void step3_4() throws Exception {

		LocalDateTime timestamp = eventsController.getAllEvents().getBody().get(3).time().toLocalDateTime();
		String url = new StringBuilder().append("/readers/top?from=").append(timestamp).toString();

		this.mockMvc.perform(get(url))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(
						new ObjectMapper()
								.setDateFormat(new StdDateFormat())
								.writer().writeValueAsString(readersController.getTopReader(timestamp.toString(), "").getBody())
				));
	}


	// ========================================== Delete method tests ====================================================

	@Test
		// delete event
	void step4_1() throws Exception {

		UUID eventid = eventsController.getAllEvents().getBody().get(0).id();

		String url = new StringBuilder().append("/events/").append(eventid).toString();

		this.mockMvc.perform(delete(url))
				.andDo(print())
				.andExpect(status().isOk());

		eventsController.getAllEvents().getBody().stream().forEach(eventDTO -> eventsController.deleteEvent(eventDTO.id()));
	}

	@Test
		// delete reader
	void step4_2() throws Exception {

		String url = new StringBuilder().append("/readers/").append(getReaderId()).toString();

		this.mockMvc.perform(delete(url))
				.andDo(print())
				.andExpect(status().isOk());

		readersController.getReaderByNameAndSurname("", "").getBody().stream().forEach(readerDTO -> readersController.deleteReader(readerDTO.id()));
	}

	@Test
		// delete book
	void step4_3() throws Exception {

		String url = new StringBuilder().append("/books/").append(getBookId()).toString();

		this.mockMvc.perform(delete(url))
				.andDo(print())
				.andExpect(status().isOk());

		booksController.getBookByTitleAndAuthor("", "").getBody().stream().forEach(bookDTO -> booksController.deleteBook(bookDTO.id()));
	}

}
