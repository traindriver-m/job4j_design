package ru.job4j.question;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int added;
        int changed = 0;
        int deleted = 0;
            Map<Integer, String> currentMap = current.stream()
                    .collect(Collectors.toMap(User::getId, User::getName));
            for (User user : previous) {
                int id = user.getId();
                String name = user.getName();
                String returnValue = currentMap.putIfAbsent(id, name);
                if (returnValue == null) {
                    deleted++;
                } else if (!returnValue.equals(name)) {
                    changed++;
                }
            }
            added = currentMap.size() - previous.size();
        return new Info(added, changed, deleted);
    }
}
