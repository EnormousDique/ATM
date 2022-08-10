package ru.misha.bank;

public class MyTimer
{
    private static long start;



    public static void setTimer()
    {
        start = System.currentTimeMillis();
    }

    public static long getTime()
    {
        return System.currentTimeMillis() - start;
    }
}
