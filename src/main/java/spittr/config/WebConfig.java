package spittr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan("spittr.web")
@ImportResource("classpath:applicationContext.xml")
public class WebConfig extends WebMvcConfigurerAdapter {

	/*
	 * config a JSP view resolver
	 * 
	 * it¡¯s configured to look for JSPfiles by wrapping view names with a
	 * specific prefix and suffix (for example, a view name of home will be
	 * resolved as /WEB-INF/views/home.jsp)
	 */
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		resolver.setExposeContextBeansAsAttributes(true);
		return resolver;
	}

	/*
	 * config static content handler
	 */
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		/*
		 * By calling enable() on the given DefaultServletHandlerConfigurer,
		 * you¡¯re asking DispatcherServlet to forward requests for static
		 * resources to the servlet container¡¯s default servlet and not to try
		 * to handle them itself.
		 */
		configurer.enable();
	}

}
