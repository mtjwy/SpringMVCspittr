package spittr.web;

import static org.hamcrest.Matchers.hasItems;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceView;

import spittr.Spittle;
import spittr.data.SpittleRepository;

public class SpittleControllerTest {

	@Test
	public void shouldShowPagedSpittles() throws Exception {
		/*
		 * creating a mock implementation of the SpittleRepository interface
		 * that will return a list of 20 Spittle objects from its findSpittles()
		 * method.
		 */
		List<Spittle> expectedSpittles = createSpittleList(20);
		SpittleRepository mockRepository = mock(SpittleRepository.class);
		when(mockRepository.findSpittles(238900, 20)).thenReturn(expectedSpittles);

		/*
		 * It then injects that repository into a new SpittleControllerinstance
		 * and sets up MockMvcto use that controller.
		 */
		SpittleController controller = new SpittleController(mockRepository);
		MockMvc mockMvc = standaloneSetup(controller)
				.setSingleView(new InternalResourceView("/WEB-INF/views/spittles.jsp")).build();
				/*
				 * Notice that unlike HomeControllerTest, this test calls
				 * setSingleView() on the MockMvcbuilder. This is so the mock
				 * framework won¡¯t try to resolve the view name coming from the
				 * controller on its own. In many cases, this is unnecessary.
				 * 
				 * But for this controller method, the view name will be similar
				 * to the request¡¯s path;
				 * 
				 * left to its default view resolution, MockMvcwill fail because
				 * the view path will be confused with the controller¡¯s path.
				 * The actual path given when constructing the
				 * InternalResourceViewis unimportant in this test, but you set
				 * it to be consistent with how you¡¯ve configured
				 * InternalResourceViewResolver.
				 */

		/*
		 * asserting the model has an attribute named spittleListwith the
		 * expected contents.
		 */
		mockMvc.perform(get("/spittles?max=238900&count=20")).andExpect(view().name("spittles"))
				.andExpect(model().attributeExists("spittleList"))
				.andExpect(model().attribute("spittleList", hasItems(expectedSpittles.toArray())));
	}

	@Test
	public void testSpittle() throws Exception {
		Spittle expectedSpittle = new Spittle("Hello", new Date());
		SpittleRepository mockRepository = mock(SpittleRepository.class);
		when(mockRepository.findOne(12345)).thenReturn(expectedSpittle);

		SpittleController controller = new SpittleController(mockRepository);
		MockMvc mockMvc = standaloneSetup(controller).build();

		//request resource via path
		mockMvc.perform(get("/spittles/12345")).andExpect(view().name("spittle"))
				.andExpect(model().attributeExists("spittle")).andExpect(model().attribute("spittle", expectedSpittle));
	}

	private List<Spittle> createSpittleList(int count) {
		List<Spittle> spittles = new ArrayList<Spittle>();
		for (int i = 0; i < count; i++) {
			spittles.add(new Spittle("Spittle " + i, new Date()));
		}
		return spittles;
	}

}
