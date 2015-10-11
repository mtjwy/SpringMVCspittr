package spittr.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*
 * Because HomeControlleris annotated with @Controller, the component
 * scanner will automatically pick up HomeController and declare it as a bean
 * in the Spring application context.
 */
@Controller
public class HomeController {

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String home() {
		return "home";
		/*
		 * This String will be interpreted by Spring MVCas the name of the view
		 * that will be rendered. DispatcherServletwill ask the view resolver to
		 * resolve this logical view name into an actual view.
		 */
	}
	

}
