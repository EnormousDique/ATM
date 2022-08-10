package ru.misha.bank.controllers;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.misha.bank.ATM;
import ru.misha.bank.CashCassette;
import ru.misha.bank.MyTimer;

@Controller
public class ClientController
{

    //Класс - контроллер. Принимает http запросы от dispatcher servlet.
    // Далее, в зависимости от типа и содержания запроса, вызываем тот или иной метод.с

	@GetMapping("/1/hello") //Метод возвращает стартовую страницу по get запросу на указанный адрес.
	public String helloPage()
	{ // Метод, возвращающий домашнюю станицу по get-запросу.
		return "hello";
	}

    @GetMapping("/1/setup") //Метод возвращает страницу настройки банкомата по get запросу на указанный адрес.
    public String startATMSetup()
    {// Метод, возвращающий станицу настроек банкомата по get-запросу.
        return "setup";
    }

    @PostMapping("/1/constructor")
    public String constructATM(HttpServletRequest request)
    { // Метод, совершающий настройку банкомата, используя данные post-запроса, полученные из html-формы.
        //Методу необходимо передать объект http-запроса, чтобы  подтягивать данные из его тела.

        //Получаем информацию из хтмл-формы с пост запроса. Сохраняем информацию для дальнейшей сборки банкомата.

        ATM atm = ATM.atm;
        // Создаем переменную, в которую для удобства сохраняем ссылку на наш банкомат.

        try//Блок трай/кетч необходим для обработки возможных ошибок и перехвата исключений.
        {
            //На html-странице, с которой мы получаем запрос есть несколько форм. Данные с этих форм метод сораняет
            //опеределенным образом, для дальшейшей настройки банкомата.

            int b5k1 = Integer.parseInt(request.getParameter("5k1"));
            int b2k1 = Integer.parseInt(request.getParameter("2k1"));
            int b1k1 = Integer.parseInt(request.getParameter("1k1"));
            int b05k1 = Integer.parseInt(request.getParameter("05k1"));
            int b02k1 = Integer.parseInt(request.getParameter("02k1"));
            int b01k1 = Integer.parseInt(request.getParameter("01k1"));

            int[] cassette1 = new int[]{b5k1, b2k1, b1k1, b05k1, b02k1, b01k1};
            //Получили данные о купюрах  для  1-й кассеты. Для удобства сохраняем в массив.


            int b5k2 = Integer.parseInt(request.getParameter("5k2"));
            int b2k2 = Integer.parseInt(request.getParameter("2k2"));
            int b1k2 = Integer.parseInt(request.getParameter("1k2"));
            int b05k2 = Integer.parseInt(request.getParameter("05k2"));
            int b02k2 = Integer.parseInt(request.getParameter("02k2"));
            int b01k2 = Integer.parseInt(request.getParameter("01k2"));

            int[] cassette2 = new int[]{b5k2, b2k2, b1k2, b05k2, b02k2, b01k2};
            //Получили данные о купюрах  для  2-й кассеты. Для удобства сохраняем в массив.

            int b5k3 = Integer.parseInt(request.getParameter("5k3"));
            int b2k3 = Integer.parseInt(request.getParameter("2k3"));
            int b1k3 = Integer.parseInt(request.getParameter("1k3"));
            int b05k3 = Integer.parseInt(request.getParameter("05k3"));
            int b02k3 = Integer.parseInt(request.getParameter("02k3"));
            int b01k3 = Integer.parseInt(request.getParameter("01k3"));

            int[] cassette3 = new int[]{b5k3, b2k3, b1k3, b05k3, b02k3, b01k3};
            //Получили данные о купюрах  для  3-й кассеты. Для удобства сохраняем в массив.


            int b5k4 = Integer.parseInt(request.getParameter("5k4"));
            int b2k4 = Integer.parseInt(request.getParameter("2k4"));
            int b1k4 = Integer.parseInt(request.getParameter("1k4"));
            int b05k4 = Integer.parseInt(request.getParameter("05k4"));
            int b02k4 = Integer.parseInt(request.getParameter("02k4"));
            int b01k4 = Integer.parseInt(request.getParameter("01k4"));

            int[] cassette4 = new int[]{b5k4, b2k4, b1k4, b05k4, b02k4, b01k4};
            //Получили данные о купюрах  для  4-й кассеты. Для удобства сохраняем в массив.


            int b5k5 = Integer.parseInt(request.getParameter("5k5"));
            int b2k5 = Integer.parseInt(request.getParameter("2k5"));
            int b1k5 = Integer.parseInt(request.getParameter("1k5"));
            int b05k5 = Integer.parseInt(request.getParameter("05k5"));
            int b02k5 = Integer.parseInt(request.getParameter("02k5"));
            int b01k5 = Integer.parseInt(request.getParameter("01k5"));

            int[] cassette5 = new int[]{b5k5, b2k5, b1k5, b05k5, b02k5, b01k5};
            //Получили данные о купюрах  для  5-й кассеты. Для удобства сохраняем в массив.


            int b5k6 = Integer.parseInt(request.getParameter("5k6"));
            int b2k6 = Integer.parseInt(request.getParameter("2k6"));
            int b1k6 = Integer.parseInt(request.getParameter("1k6"));
            int b05k6 = Integer.parseInt(request.getParameter("05k6"));
            int b02k6 = Integer.parseInt(request.getParameter("02k6"));
            int b01k6 = Integer.parseInt(request.getParameter("01k6"));

            int[] cassette6 = new int[]{b5k6, b2k6, b1k6, b05k6, b02k6, b01k6};
            //Получили данные о купюрах  для  6-й кассеты. Для удобства сохраняем в массив.


            int b5k7 = Integer.parseInt(request.getParameter("5k7"));
            int b2k7 = Integer.parseInt(request.getParameter("2k7"));
            int b1k7 = Integer.parseInt(request.getParameter("1k7"));
            int b05k7 = Integer.parseInt(request.getParameter("05k7"));
            int b02k7 = Integer.parseInt(request.getParameter("02k7"));
            int b01k7 = Integer.parseInt(request.getParameter("01k7"));

            int[] cassette7 = new int[]{b5k7, b2k7, b1k7, b05k7, b02k7, b01k7};
            //Получили данные о купюрах  для  7-й кассеты. Для удобства сохраняем в массив.


            int b5k8 = Integer.parseInt(request.getParameter("5k8"));
            int b2k8 = Integer.parseInt(request.getParameter("2k8"));
            int b1k8 = Integer.parseInt(request.getParameter("1k8"));
            int b05k8 = Integer.parseInt(request.getParameter("05k8"));
            int b02k8 = Integer.parseInt(request.getParameter("02k8"));
            int b01k8 = Integer.parseInt(request.getParameter("01k8"));

            int[] cassette8 = new int[]{b5k8, b2k8, b1k8, b05k8, b02k8, b01k8};
            //Получили данные о купюрах  для  8-й кассеты. Для удобства сохраняем в массив.

            int[][] cassettes = new int[][]{cassette1, cassette2, cassette3, cassette4,
                    cassette5, cassette6, cassette7, cassette8};
            //Создали массив массивов. Каждый из массивов нижнего уровня хранит информацию о кол-ве купюр для кассеты
            //с номером, соответствующим индексу этого массива.

            if(atm.isSet) atm.erase();
            //Если банкомат уже был настроен ранее, сбрасываем настройку, чтобы заполнить банкомат согласно данным
            // переданным нам  через  форму.

            atm.setCassettesCount(cassettes.length);
            atm.fill(cassettes);//передаем наш двойной массив в метод заполнения банкомата. Метод возьмет данные
            //из массива как инструкцию для заполнения кассет банкнотами.

            //Методы ниже отвечают за "поломку" кассет. Если пользователь указал,что какая(ие)-то из кассет должна быть
            //сломана, то мы увидим это в теле запроса и передадим в нужный метод.
            atm.cassettes.get(0).breakCassette(request.getParameter("c1b"));
            atm.cassettes.get(1).breakCassette(request.getParameter("c2b"));
            atm.cassettes.get(2).breakCassette(request.getParameter("c3b"));
            atm.cassettes.get(3).breakCassette(request.getParameter("c4b"));
            atm.cassettes.get(4).breakCassette(request.getParameter("c5b"));
            atm.cassettes.get(5).breakCassette(request.getParameter("c6b"));
            atm.cassettes.get(6).breakCassette(request.getParameter("c7b"));
            atm.cassettes.get(7).breakCassette(request.getParameter("c8b"));
        }
        catch(Exception e)
        {//Блок обработки исключений. Поскольку ошибка может возникнуть только при неправильном формате данных,
            //введенных юзером в поле html-формы, то я указываю просто Exception (спасибо, полиморфизм) и возвращаю
            //юзеру страницу с ошибкой и инструкциями по ее недопущению.
            return "error";
        }

        //Если настройка банкомата прошла в штатном режиме, возвращаем юзеру домашнюю страницу, где он может
        //снять наличные или проверить настройки банкомата.
        return "hello";
    }

    @GetMapping("/1/show")
    public String showPage(Model model)
    { //метод отвечает за отображение настроек банкомата на соотв. странице.
        //Для передачи данных из контроллера в представление (html стр.) используем модель. Указываю ее в сигнаруте
        //метода. Спринг сам внедрит объект модели.


        ATM atm = ATM.atm; //Для удобства сохраняем ссылку на банкомат в локальную переменную.

        for(int i = 0; i<atm.cassettes.size();i++)
        { //Цикл проходится по всем кассетам банкомата, проверяет их наполнение и передает данные в модель.
            for(Map.Entry<String, Integer> e : atm.cassettes.get(i).showContent().entrySet())
            {//Внутренний цикл отвечает за формирование уникального имени для каждого атрибута модели, сохраняющего в
                //себя информацию о кол-ве купюр в кассетах.
                String name =  "c" + e.getKey() + (i+1);
                int value = e.getValue();
                model.addAttribute(name ,value);
            }
        }

        for(int i = 0; i < atm.cassettes.size(); i++)
        {//Цикл проходит по кассетам, проверяет какая из них сломана, чтобы передать информацию об этом в модель
            //и отобразить на странице
            model.addAttribute("c"+i,atm.cassettes.get(i).isWorking());
        }
        return "show";
    }

    @GetMapping("/1/use")//Метод возвращает страницу использования банкомата  по get-запросу
    public String useATM()
    {
        return "use";
    }

    @PostMapping("/1/withdrawal")
    public String withdrawal(HttpServletRequest request, Model model)
    {
        //Метод, принимающий сумму к снятию по post-запросу. Отсюда будут вызываться методы банкомата, проверяющие
        //возможность снятия средств из той или иной кассеты банкомата.

        MyTimer.setTimer(); //Запускаем таймер, чтобы вывести время, ушедшее на расчёты (по условиям задачи).

        ATM atm = ATM.atm; //Для удобства сохраянем ссылку на банкомат в локальную переменную

        int money; //В данной переменной будет храниться сумма для снятия

        try
        { // Сохраняем в переменную money, данные о сумме снятия, полученной через post-запрос.
            money = Integer.parseInt(request.getParameter("money"));
        }
        catch (Exception e)
        {//Ловим возможные исключения, возвращаем страничку с ошибкой, если что-то пошло не так.
            return "wrongSum";
        }



        //Если нам удалось получить сумму из post-запроса, продолжаем работать далее в штатном режиме.

        //****************************************************************************

            int min = 5000;
            if(money < 5000 && money >= 2000 || (atm.hasNoFives(money))) min = 2000;
            if(money < 2000 && money >= 1000 || (atm.hasNoFives(money) && atm.hasNoTwos(money))) min = 1000;
            if(money < 1000 && money >= 500 || (atm.hasNoFives(money) && atm.hasNoTwos(money) && atm.hasNoGrands(money))) min = 500;
            if(money < 500 && money >= 200|| (atm.hasNoFives(money) && atm.hasNoTwos(money) && atm.hasNoGrands(money) && atm.hasNoPyatihatka(money))) min = 200;
            if(money < 200) min = 100;

            if(atm.allCassettesDown())
            {
                return "maintenance";
            }

            atm.withdraw(money,min,new ArrayList<Integer>());
            //Передаем полученную от юзера сумму в метод банкомата.
            //Метод проверит каким образом можно снять сумму, и отметит конкретную кассету.


        CashCassette winner = ATM.minBillAmount(atm.cassettes);
        //Сохраняем в переменную ссылку на кассету, которую вернет метод.
        //Метод пройдется по всем кассетам и найдет  ту, которую метод withdraw отметил подходящей.

        System.out.println(winner);

        long time = MyTimer.getTime();

        model.addAttribute("cassetteNo", winner);
        model.addAttribute("timer", time);


        return "result"; //Возрващаем страницу с результатами.

    }


}
