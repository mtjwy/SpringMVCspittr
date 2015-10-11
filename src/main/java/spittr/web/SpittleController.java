package spittr.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import spittr.Spittle;
import spittr.data.SpittleRepository;

@Controller
@RequestMapping("/spittles")
public class SpittleController {

	private static final String MAX_LONG_AS_STRING = "9223372036854775807";

	private SpittleRepository spittleRepository;

	@Autowired
	public SpittleController(SpittleRepository spittleRepository) {
		this.spittleRepository = spittleRepository;
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<Spittle> spittles(@RequestParam(value = "max", defaultValue = MAX_LONG_AS_STRING) long max,
			@RequestParam(value = "count", defaultValue = "20") int count) {
		return spittleRepository.findSpittles(max, count);
		/*
		 * When a handler method returns an object or a collection like this,
		 * the value returned is put into the model, and the model key is
		 * inferred from its type (spittleList in this case)
		 * 
		 * As for the logical view name, it¡¯s inferred from the request path.
		 * Because this method handles GET requests for /spittles, the view name
		 * is spittles (chopping off the leading slash).
		 */

		/*
		 * the result is: A list of Spittle objects is stored in the model with
		 * a key of spittleList and given to the view whose name is spittles.
		 * Given the way you¡¯ve configured InternalResourceViewResolver, that
		 * view is a JSP at /WEB-INF/views/spittles.jsp.
		 */

		/*
		 * The Model is essentially a map (that is, a collection of key-value
		 * pairs) that will be handed off to the view so that the data can be
		 * rendered to the client
		 */
	}

	/*
	 * This handler method uses placeholders to accept a Spittle ID as part of
	 * the path.
	 * 
	 * Although all the other parts of the path need to match exactly for the
	 * request to be handled, the placeholder can carry any value.
	 */
	@RequestMapping(value = "/{spittleId}", method = RequestMethod.GET)
	public String spittle(@PathVariable("spittleId") long spittleId, Model model) {
		/*
		 * @PathVariable("spittleId"). This indicates that whatever value is at
		 * the placeholder position in the request path will be passed into the
		 * handler method¡¯s spittleId parameter.
		 */
		model.addAttribute(spittleRepository.findOne(spittleId));
		/*
		 * will add that Spittle to the model. The model key will be spittle,
		 * inferred by the type passed in to addAttribute().
		 */
		return "spittle";
	}

}
