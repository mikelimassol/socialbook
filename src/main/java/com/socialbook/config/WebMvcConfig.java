package com.socialbook.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * 
 * @author Michael Papamichael
 * 
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.socialbook.*")
public class WebMvcConfig extends WebMvcConfigurerAdapter {
        
//    @Value("${tiles.location}")
//    private String tilesLocation;
//
//    /**
//     * View Resolver.
//     * 
//     * @return resolver
//     */
//    @Bean
//    public ViewResolver viewResolver() {
//        UrlBasedViewResolver resolver = new UrlBasedViewResolver();
//        resolver.setViewClass(TilesView.class);
//        return resolver;
//    }
//
//    /**
//     * Tiles Configuration.
//     * 
//     * @return Tiles Configurer
//     */
//    @Bean
//    public TilesConfigurer tilesConfigurer() {
//        TilesConfigurer tiles = new TilesConfigurer();
//        tiles.setDefinitions(new String[] { tilesLocation });
//        tiles.setCheckRefresh(true);
//        return tiles;
//    }

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
