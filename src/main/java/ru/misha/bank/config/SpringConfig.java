package ru.misha.bank.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

@Configuration //конфигурация spring
@ComponentScan("ru.misha.bank") //spring будет искать компоненты (бины) в указаном пакете
@EnableWebMvc //указываем, чтобы внести изменения в дефолт конфиг. spring приложения (нужно для шаблонизатора thymeleaf)

public class SpringConfig implements WebMvcConfigurer
{

    private final ApplicationContext context;
    //Данное поле будет хранить информацию о контексте приложения (тут спринг хранит созданные бины).

    @Autowired //Внедяем зависимость. Спринг сам подставит сюда бин контекта. (Используем IoC)
    public SpringConfig (ApplicationContext context)
    {
        this.context = context;
    }

    @Bean
    public SpringResourceTemplateResolver templateResolver()
    { //Метод создает бин объекта шаблонизатора. Необходимо для работы Thymeleaf шаблонизатора.
        //Создаем объект шаблонизатора, передаем ему наш контекст и путь до директории с шаблонами/представлениями.
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setApplicationContext(context);
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".html");
        return resolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine()
    {//Метод отвечает за движок шаблонизатора.
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }

    //@Override
    public void configureViewResolvers(ViewResolverRegistry registry)
    {//Метод создаетобект, работающий с представлениями.
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        registry.viewResolver(resolver);
    }

}
