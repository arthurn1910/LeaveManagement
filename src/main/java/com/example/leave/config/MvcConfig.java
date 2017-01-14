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
        return localeChangeInterceptor;
    }

    @Bean(name = "localeResolver")
    public LocaleResolver getLocaleResolver() {
        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
        cookieLocaleResolver.setCookieName("language");
        cookieLocaleResolver.setDefaultLocale(new Locale("pl"));
        cookieLocaleResolver.setCookieMaxAge(604800);
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
            ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/error2");
            ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error2");
            ErrorPage error403Page = new ErrorPage(HttpStatus.FORBIDDEN, "/error2");
            ErrorPage error = new ErrorPage(HttpStatus.BAD_REQUEST, "/error2");
            ErrorPage error1 = new ErrorPage(HttpStatus.CONFLICT, "/error2");


            ErrorPage error2 = new ErrorPage(HttpStatus.BAD_GATEWAY, "/error2");
            ErrorPage error3 = new ErrorPage(HttpStatus.EXPECTATION_FAILED, "/error2");
            ErrorPage error4 = new ErrorPage(HttpStatus.FAILED_DEPENDENCY, "/error2");
            ErrorPage error5 = new ErrorPage(HttpStatus.FORBIDDEN, "/error2");
            ErrorPage error6 = new ErrorPage(HttpStatus.GONE, "/error2");
            ErrorPage error7 = new ErrorPage(HttpStatus.LOCKED, "/error2");
            ErrorPage error8 = new ErrorPage(HttpStatus.METHOD_NOT_ALLOWED, "/error2");
            ErrorPage error9 = new ErrorPage(HttpStatus.MULTIPLE_CHOICES, "/error2");
            ErrorPage error10 = new ErrorPage(HttpStatus.NO_CONTENT, "/error2");
            ErrorPage error11 = new ErrorPage(HttpStatus.NOT_ACCEPTABLE, "/error2");
            ErrorPage error12 = new ErrorPage(HttpStatus.NOT_EXTENDED, "/error2");
            ErrorPage error13 = new ErrorPage(HttpStatus.NOT_FOUND, "/error2");
            ErrorPage error14 = new ErrorPage(HttpStatus.NOT_MODIFIED, "/error2");
            ErrorPage error15 = new ErrorPage(HttpStatus.UNPROCESSABLE_ENTITY, "/error2");


            container.addErrorPages(error404Page, error500Page, error403Page, error, error1, error2, error3, error4,
                    error5, error6, error7, error8, error9, error10, error11, error12, error13, error14, error15);
        };
    }

}
