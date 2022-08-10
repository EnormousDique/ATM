package ru.misha.bank;


import java.util.*;

public class ATM
{
    //Класс  банкомата

    public static ATM atm = new ATM();
    //Сразу создаем публичное статик поле, куда созраняем ссылку на новый объект банкомата.
    // С этим объектом программа будет взаимодействовать все время работы.

    public ArrayList<ArrayList<Integer>> totals = new ArrayList<>();

    public static boolean stop;

    public static ArrayList<LinkedHashMap<String,Integer>> maps = new ArrayList<>();


    public boolean isSet;
    //Булево значение, отвечающее за то, был ли банкомат уже настроен.


    public ArrayList<CashCassette> cassettes;
    // Поле, хранящее в себе ссылку на динамический массив кассет банкомата.

    ATM()
    {
        this.cassettes = new ArrayList<CashCassette>();
    }
    //Метод-конструктор. При создании банкомата, сразу инициируем  массив кассет.

    public void setCassettesCount(int i)
    {
        //Метод, принимающий кол-во кассет банкомата.
        for (int j =0; j< i; j++)
        {//Цикл создает новую кассету каждую итерацию. Кол-во итераций = переданное кол-во кассет.
            cassettes.add(new CashCassette());
            cassettes.get(j).setId(j);
        }
    }

    public void fill(int[][] cas)
    {//Метод заполнения кассет банкоматом. Принимает двойной массив. Массив верхнего уровня хранит в себе массивы
        //  с кол-вом купюр для каждой кассеты. Индексы массивов нижнего уровня = номера кассет.
        for(int i = 0; i < cassettes.size(); i++)
        {
            for(int j = 0; j < cas[i].length; j++)
            { //Используем цикл-в-цикле для заполнения кассет значениями, полученными из двойного массива.
                switch(j)
                {
                    case 0:
                        cassettes.get(i).showContent().put("5000", cas[i][j]);
                        break;
                    case 1:
                        cassettes.get(i).showContent().put("2000", cas[i][j]);
                        break;
                    case 2:
                        cassettes.get(i).showContent().put("1000", cas[i][j]);
                        break;
                    case 3:
                        cassettes.get(i).showContent().put("500", cas[i][j]);
                        break;
                    case 4:
                        cassettes.get(i).showContent().put("200", cas[i][j]);
                        break;
                    case 5:
                        cassettes.get(i).showContent().put("100", cas[i][j]);
                        break;
                }
            }
        }

        isSet = true; //Указываем что банкомат был  настроен.
    }


    public static CashCassette minBillAmount(List<CashCassette> cassettes)
    {//Метод принимает список кассет, и возвращает ту, которой потребуется меньше купюр для выдачи суммы.

        CashCassette winner = new CashCassette(Integer.MAX_VALUE);

        for(CashCassette cassette : cassettes)
        { //Проходимся циклом по списку и ищем кассету, которой потребуется мин. кол-во купюр.
            if(cassette.getNeededBillAmount() < winner.getNeededBillAmount())
            {
                winner = cassette;
            }
        }
        if(winner.getNeededBillAmount() == Integer.MAX_VALUE)
        {
            return null;
        }

        return winner;

    }

    public void erase()
    {
        cassettes.clear();
    } //Метод обнуляер настройку  банкомата.

    public void withdraw(int sum, int min, ArrayList<Integer> arr)
    {
        if(stop) return;

        if(sum == 0)
        {

            LinkedHashMap<String,Integer> map = toLinkedMap(arr);
            //переводим полученную раскладку суммы по купюрам в хешмапу

            for(int i = 0; i < cassettes.size(); i++)
            {

                if(check(map,cassettes.get(i).showContent()) && cassettes.get(i).isWorking())
                {
                    countBills(map, cassettes.get(i));
                    stop = true;
                }
            }




        }
        //Ниже находится код рекурсивной фунции. Принцип ее работы заключается в том, чтобы
        // выдавать все возможные "разбивки"  полученной от юзера суммы по купюрам.
        //Затем, полученные разбивки переводятся  в формат хеш-мапы, а далее идут в метод, "прогоняющий" их по
        //кассетам для поиска подходящей.
        if(min == 5000 && sum >= 5000 )
        {
            ArrayList<Integer> newArr = new ArrayList<Integer>(arr);
            newArr.add(5000);
            withdraw(sum-5000, 5000, newArr);
        }
        if(min  >= 2000 && sum >=2000)
        {
            ArrayList<Integer> newArr = new ArrayList<Integer>(arr);
            newArr.add(2000);
            withdraw(sum-2000,2000,newArr);
        }
        if(min  >= 1000 && sum >=1000)
        {
            ArrayList<Integer> newArr = new ArrayList<Integer>(arr);
            newArr.add(1000);
            withdraw(sum-1000,1000,newArr);
        }
        if(min  >= 500 && sum >=500)
        {
            ArrayList<Integer> newArr = new ArrayList<Integer>(arr);
            newArr.add(500);
            withdraw(sum-500,500,newArr);
        }
        if(min  >= 200 && sum >=200)
        {
            ArrayList<Integer> newArr = new ArrayList<Integer>(arr);
            newArr.add(200);
            withdraw(sum-200,200,newArr);
        }
        if(sum >= 100)
        {
            ArrayList<Integer> newArr = new ArrayList<Integer>(arr);
            newArr.add(100);
            withdraw(sum-100,100,newArr);
        }

    }

    public static boolean check(LinkedHashMap<String,Integer> map1, LinkedHashMap<String,Integer> map2)
    {
        //1-я мапа - вариант перебора суммы снятия, 2-я мапа - содержание кассеты.
        //Метод проверяет возможность снятия суммы с кассеты. Если вторая мапа содержит равные или большие значения,
        // чем те же ключи первой мапы, то кассета подходит.

        int count = 0;

        for(Map.Entry pair : map1.entrySet())
        {
            if(map2.containsKey(pair.getKey())
                    &&
                    map2.get(pair.getKey()) >= ( (Integer) pair.getValue()))
            {
                count++;
            }

        }

        return count == map1.size();
    }

    public static void countBills(LinkedHashMap<String, Integer> map, CashCassette cassette)
    { //Метод считает кол-во купюр, необходимое для того, чтобы удовлетворить запрос пользователя на снятие
        int amount = 0;
        for(Map.Entry<String,Integer> e : map.entrySet())
        {
            amount += e.getValue();
        }
        cassette.setNeededBillAmount(amount);
    }

    public static LinkedHashMap<String, Integer> toLinkedMap(ArrayList<Integer> arr)
    {  //Метод принимает дин. масс. купюр и переводит их вформат хешмапы.
        LinkedHashMap<String, Integer> map = new LinkedHashMap<String,Integer>();

        for(Integer i : arr)
        {
            if(map.containsKey(i.toString()))
            {
                map.put(i.toString(), (map.get(i.toString()) + 1));
            }
            if(!map.containsKey(i.toString()))
            {
                map.put(i.toString(),1);
            }
        }


        return map;
    }

    public boolean hasNoFives(int money)
    { //Метод проверяет наличие/отсутсвие в банкомате купюр с номиналом  5 000 (для уск. алг.)

        getRidOfNoFives(money); //вызываем вспомогательный метод (см. комментарии к нему)

        int counter = 0;

        for(CashCassette c: cassettes)
        {
            if(c.showContent().get("5000") != 0 && c.isWorking())
            {
                counter++;
            }
        }

        return counter == 0;
    }

    private void getRidOfNoFives(int money)
    { //Вспомогательный метод. Исключает из перебора кассеты, с которыми можно не работать. Ускоряем алгоритм.
        for(CashCassette c : cassettes)
        {
            int k5 = (c.showContent().get("5000") * 5000);

            int rest = (c.showContent().get("2000") * 2000)
                    + (c.showContent().get("1000") * 1000)
                    + (c.showContent().get("500") * 500)
                    + (c.showContent().get("200") * 200)
                    + (c.showContent().get("100") * 100);

            if(money - k5 > rest)
            {
                c.breakCassette("on");
            }

        }
    }
 //Последующие методы имеют аналогичную логику работы, за исключением номинала  купюр.
    public boolean hasNoTwos(int money)
    {
        getRidOfNoTwos(money);

        int counter = 0;

        for(CashCassette c: cassettes)
        {
            if(c.showContent().get("2000") != 0 && c.isWorking())
            {
                counter++;
            }
        }

        return counter == 0;
    }

    private void getRidOfNoTwos(int money)
    {
        for(CashCassette c : cassettes)
        {
            int k2 = (c.showContent().get("2000") * 2000);

            int rest =  (c.showContent().get("1000") * 1000)
                    + (c.showContent().get("500") * 500)
                    + (c.showContent().get("200") * 200)
                    + (c.showContent().get("100") * 100);

            if(money - k2 > rest)
            {
                c.breakCassette("on");
            }

        }
    }

    public boolean hasNoGrands(int money)
    {
        getRidOfNoGrands(money);


        int counter = 0;

        for(CashCassette c: cassettes)
        {
            if(c.showContent().get("1000") != 0 && c.isWorking())
            {
                counter++;
            }
        }

        return counter == 0;
    }

    private void getRidOfNoGrands(int money)
    {
        for(CashCassette c : cassettes)
        {
            int k1 = (c.showContent().get("1000") * 1000);

            int rest =  (c.showContent().get("500") * 500)
                    + (c.showContent().get("200") * 200)
                    + (c.showContent().get("100") * 100);

            if(money - k1 > rest)
            {
                c.breakCassette("on");
            }

        }
    }

    public boolean hasNoPyatihatka(int money)
    {
        getRidOfNoPyatihatka(money);

        int counter = 0;

        for(CashCassette c: cassettes)
        {
            if(c.showContent().get("500") != 0 && c.isWorking())
            {
                counter++;
            }
        }

        return counter == 0;
    }

    private void getRidOfNoPyatihatka(int money)
    {
        for(CashCassette c : cassettes)
        {
            int k05 = (c.showContent().get("500") * 500);

            int rest =   (c.showContent().get("200") * 200)
                    + (c.showContent().get("100") * 100);

            if(money - k05 > rest)
            {
                c.breakCassette("on");
            }

        }
    }

    public boolean allCassettesDown()
    { //Метод возвращает тру, если все кассеты сломаны.
        int count = 0;

        for(CashCassette c : cassettes)
        {
            if(!c.isWorking())
            {
                count++;
            }
        }
        return count == cassettes.size();
    }

}
