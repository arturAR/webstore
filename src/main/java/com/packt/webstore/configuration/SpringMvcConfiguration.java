package com.packt.webstore.configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;
import org.springframework.web.util.UrlPathHelper;
import org.springframework.webflow.mvc.servlet.FlowHandlerAdapter;
import org.springframework.webflow.mvc.servlet.FlowHandlerMapping;

import com.packt.webstore.interceptor.AuditingInterceptor;
import com.packt.webstore.interceptor.PerformanceMonitorInterceptor;
import com.packt.webstore.interceptor.PromoCodeInterceptor;
import com.packt.webstore.model.Product;
import com.packt.webstore.viewResolvers.Jaxb2MarshallingXmlViewResolver;
import com.packt.webstore.viewResolvers.JsonViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.packt.webstore")
@PropertySource(value = {"classpath:codes.properties"})
@EnableTransactionManagement
@EnableScheduling
@EnableAsync
public class SpringMvcConfiguration extends WebMvcConfigurerAdapter {
	
	@Autowired
	private SpringWebflowConfiguration webflowConfig;
    @Autowired
    private Environment environment;  
	@Autowired
	private PerformanceMonitorInterceptor performanceMonitorInterceptor;
	@Autowired
	private AuditingInterceptor auditingInterceptor;
	
	@Bean
	public FlowHandlerMapping flowHandlerMapping() {
		FlowHandlerMapping handlerMapping = new FlowHandlerMapping();
		handlerMapping.setOrder(-1);
		handlerMapping.setFlowRegistry(this.webflowConfig.flowRegistry());
		return handlerMapping;
	}

	@Bean
	public FlowHandlerAdapter flowHandlerAdapter() {
		FlowHandlerAdapter handlerAdapter = new FlowHandlerAdapter();
		handlerAdapter.setFlowExecutor(this.webflowConfig.flowExecutor());
		handlerAdapter.setSaveOutputToFlashScopeOnRedirect(true);
		return handlerAdapter;
	}
	
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        UrlPathHelper urlPathHelper = new UrlPathHelper();
        urlPathHelper.setRemoveSemicolonContent(false);
        configurer.setUrlPathHelper(urlPathHelper);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
            .addResourceHandler("/resource/**")
            .addResourceLocations("/resources/");
    }
	
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
    
    @Bean
    public ViewResolver restViewResolver(ContentNegotiationManager manager) {
        ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
        List<ViewResolver> resolvers = new ArrayList<ViewResolver>();
        resolvers.add(jsonViewResolver());
        resolvers.add(jaxb2MarshallingXmlViewResolver());
        return resolver;
    }
	
    @Bean
    public ViewResolver jsonViewResolver() {
    	return new JsonViewResolver();
    }
    
    @Bean
    public ViewResolver jaxb2MarshallingXmlViewResolver() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setClassesToBeBound(Product.class);
        return new Jaxb2MarshallingXmlViewResolver(marshaller);
    }
    
    @Bean
    public TilesViewResolver getTilesViewResolver() {
    	TilesViewResolver tilesViewResolver = new TilesViewResolver();
    	tilesViewResolver.setViewClass(TilesView.class);
    	tilesViewResolver.setOrder(-2);
    	return tilesViewResolver;
    }

    @Bean
    public TilesConfigurer getTilesConfigurer() {
    	TilesConfigurer tilesConfigurer = new TilesConfigurer();
    	tilesConfigurer.setCheckRefresh(true);
    	tilesConfigurer.setDefinitions("/WEB-INF/tiles/definition/tile-definition.xml");
    	return tilesConfigurer;
    }
    
    @Bean(name="multipartResolver")
    public CommonsMultipartResolver getMultipartResolver() {
    	CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
    	multipartResolver.setMaxUploadSize(10240000);
    	return multipartResolver;
    }
    
    @Bean(name="localeResolver")
    public SessionLocaleResolver getLocaleResolver() {
    	SessionLocaleResolver localeResolver = new SessionLocaleResolver();
    	localeResolver.setDefaultLocale(new Locale("en"));
    	return localeResolver;
    }
    
    @Bean(name="messageSource")
    public ResourceBundleMessageSource getResourceBundleMessageSource() {
    	ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
    	resourceBundleMessageSource.setBasename("messages");
    	return resourceBundleMessageSource;
    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
         registry.addInterceptor(performanceMonitorInterceptor);
         registry.addInterceptor(auditingInterceptor);
         registry.addInterceptor(promoCodeInterceptor());
         registry.addInterceptor(localeChangeInterceptor());
    }
    
    @Bean
    public PromoCodeInterceptor promoCodeInterceptor() {
    	PromoCodeInterceptor promoCodeInterceptor = new PromoCodeInterceptor();
    	promoCodeInterceptor.setPromoCode(environment.getRequiredProperty("promo.offer.code"));
    	promoCodeInterceptor.setErrorRedirect(environment.getRequiredProperty("promo.offer.errorRedirect"));
    	promoCodeInterceptor.setOfferRedirect(environment.getRequiredProperty("promo.offer.offerRedirect"));
    	return promoCodeInterceptor;
    }
    
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
    	LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
    	localeChangeInterceptor.setParamName("language");
    	return localeChangeInterceptor;
    }
    
}
