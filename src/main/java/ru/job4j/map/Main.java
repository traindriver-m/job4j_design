package ru.job4j.map;

import java.util.Calendar;
import java.util.Calendar.*;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Calendar birthday = new GregorianCalendar(1976, Calendar.FEBRUARY, 18, 19, 20);
        Calendar birthday2 = new GregorianCalendar(1976, Calendar.FEBRUARY, 18, 19, 20);
        User user1 = new User("Sergey", 2, birthday);
        User user2 = new User("Sergey", 2, birthday2);
        Map<User, Object> map = new HashMap<>();
        map.put(user1, new Object());
        map.put(user2, new Object());
        System.out.println(map);
    }
}
