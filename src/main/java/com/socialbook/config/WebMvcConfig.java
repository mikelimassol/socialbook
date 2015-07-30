package com.socialbook.config;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;

/**
 * 
 * @author Michael Papamichael
 * 
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.movie.portal.*")
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Bean(name = "viewResolver")
    public InternalResourceViewResolver getViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }


	/**
	 * 
	 */
	@Override
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}


	/**
	 * Locale Resolver Configuration.
	 * 
	 * @return localResolver
	 */
	@Bean
	FixedLocaleResolver localResolver() {
		FixedLocaleResolver localResolver = new FixedLocaleResolver();
		localResolver.setDefaultLocale(Locale.ENGLISH);
		return localResolver;
	}
	

	

}
