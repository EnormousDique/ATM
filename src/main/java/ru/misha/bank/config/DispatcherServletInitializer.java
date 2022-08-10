package ru.misha.bank.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


public class DispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

    //Данный класс позволяет добавить в программу dispatcher servlet. Отвечает за прослушку http запросов,
    // отправляет запросы по нужному адресу,чтобы впоследствии их мог получить наш контроллер.
    //Наследуемся от класса, написанным командой spring. Он реализует интерфейс web initializer, необходимый
    // для инициализации dispatcher servlet'а.

    @Override
    protected Class<?>[] getRootConfigClasses() {
        // TODO Auto-generated method stub
        return null;
    } //метод не потребуется, ставим заглушку.

    @Override
    protected Class<?>[] getServletConfigClasses() {
        // TODO Auto-generated method stub
        return new Class[] {SpringConfig.class};
    }//метод не потребуется, ставим заглушку.


    @Override
    protected String[] getServletMappings() {
        // TODO Auto-generated method stub
        return new String[] {"/"};
    } //Указываем mapping "/" чтобы запросы по любому адресу на ip сервера попадали диспетчеру, а оттуда в контроллер(ы)

}
