package spittr.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/*
 * any class that extends
 * AbstractAnnotationConfigDispatcherServletInitializer will automatically be
 * used to configure DispatcherServlet and the Spring application context in
 * the application¡¯s servlet context.
 * 
 * In Spring web applications, there¡¯s two application context. One is created
 * by the DispatcherServlet; The other application context is created by ContextLoaderListener.
 * 
 * Under the covers, AbstractAnnotationConfigDispatcherServletInitializer creates both 
 * a DispatcherServletand a ContextLoaderListener.
 */
public class SpittrWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	
	/*
	 * identifies one or more paths that DispatcherServletwill be mapped to
	 */
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
	
	/*
	 * asked that DispatcherServlet load its application context with beans
	 * defined in the WebConfigconfiguration class
	 * 
	 * DispatcherServletis expected to load beans containing web components such
	 * as controllers, view resolvers, and handler mappings
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		
		return new Class<?>[] { WebConfig.class };
	}
	
	/*
	 * the @Configuration class¡¯s returned getRootConfigClasses()will be
	 * used to configure the application context created by
	 * ContextLoaderListener.
	 * 
	 * ContextLoaderListener is expected to load the beans, which are
	 * typically the middle-tier and data-tier components that drive the
	 * back end of the application.
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		
		return new Class<?>[] { RootConfig.class };
	}

	
}
