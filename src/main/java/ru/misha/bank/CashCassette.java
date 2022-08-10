package ru.misha.bank;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class CashCassette
{
    public LinkedHashMap<String, Integer> contents;
    //Поле, хранящее в себе ссылку на связанную хеш мапу, хранящую в себе данные о кол-во купюр и их номинале.
    private int id;
    //Поле хранящее информацию об айдишнике кассеты.

    private boolean isBroken = false, isWinner = false;
    //Булева переменная, хранящее инфо. о состоянии кассеты (сломана ли). по умолч. не сломана.


    private int neededBillAmount = Integer.MAX_VALUE;
    //Поле хранит инфо. о кол-ве купюр, необходимых этой кассете для выдачи нал. по запросу. По умолч. имеет макс. знач.

    CashCassette()
    {
        contents = new LinkedHashMap<String, Integer>();
    }
    //Стандартный конструктор. При создании кассеты сразу иниц. хешмапу.

    CashCassette(int i)
    {//Конструктор, принимающий значения длятребуемого кол-вакупюр.
        // Потребуется в методе поиска кассеты - "победителя".
        this.setNeededBillAmount(i);
    }

    public LinkedHashMap<String, Integer> showContent()
    {
        return contents;
    }
    //Геттер для содержимого кассеты. Метод возращает мапу с купюрами.

    public void setId(int id)
    {
        this.id = id;
    }
    //Сеттер для поля айди

    public int getId()
    { //Геттер для поля айди.
        return id;
    }

    @Override
    public String toString()
    {//Переопределяем метод toString(). При передаче в модель и далее на html страницу получим красивый текст.
        return "cassette no. : " + (this.id + 1) + " needed amount of bills: " + this.getNeededBillAmount();
    }

    public int getNeededBillAmount()
    {
        return this.neededBillAmount;
    }
    //Геттер для поля, хранящего кол-во купюр, необходимого для выдачи нал. по запросу.

    public void setNeededBillAmount(int i)
    {
        this.neededBillAmount = i;
    }
    //Сеттер для поля, хранящего кол-во купюр, необходимого для выдачи нал. по запросу.

    public void breakCassette(String s)
    { //Метод поломки кассеты. В метод передается полученное значение от юзера из html формы, в зависимости от значения
        //меняем булеву переменную.
        if(s!=null && s.equals("on"))
        {
            isBroken = true;
        }
    }

    public boolean isWorking()
    {
        return !isBroken;
    }
    //метод проверки поломки кассеты.


}

