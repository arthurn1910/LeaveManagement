package com.example.leave.config;

import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Locale;

@Configuration
@EnableWebMvc
public class MvcConfig  extends WebMvcConfigurerAdapter{
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if(!registry.hasMappingForPattern("/css/*")){
            registry.addResourceHandler("/css/*").addResourceLocations("classpath:/templates/css/");
        }
        if(!registry.hasMappingForPattern("/js/**")){
            registry.addResourceHandler("/js/**").addResourceLocations("classpath:/templates/js/");
        }

    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("language");
        System.out.println("!@#");
        return localeChangeInterceptor;
    }

    @Bean(name = "localeResolver")
    public LocaleResolver getLocaleResolver() {
        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
        cookieLocaleResolver.setCookieName("language");
        cookieLocaleResolver.setDefaultLocale(new Locale("pl"));
        cookieLocaleResolver.setCookieMaxAge(604800);
        System.out.println("*()");
        return cookieLocaleResolver;
    }

    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasenames("languages/messages");
        source.setUseCodeAsDefaultMessage(true);
        source.setDefaultEncoding("UTF-8");
        source.setFallbackToSystemLocale(false);
        return source;
    }

    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {

        return container -> {
            ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/error404");
            ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error2");
            ErrorPage error403Page = new ErrorPage(HttpStatus.FORBIDDEN, "/error403");

            container.addErrorPages(error404Page,error500Page,error403Page);
        };
    }

}
