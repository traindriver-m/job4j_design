package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0;
        int changed = 0;
        int deleted = 0;
        if (!current.equals(previous)) {
            Map<Integer, String> map = current.stream()
                    .collect(Collectors.toMap(User::getId, User::getName));
            for (User u : previous) {
                int id = u.getId();
                String name = u.getName();
                String retValue = map.putIfAbsent(id, name);
                if (retValue == null) {
                    deleted++;
                } else if (!retValue.equals(name)) {
                    changed++;
                }
            }
            added = map.size() - previous.size();
        }
        return new Info(added, changed, deleted);
    }
}
