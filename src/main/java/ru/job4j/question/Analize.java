package ru.job4j.question;

import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0;
        int changed = 0;
        int deleted = 0;
        if (!current.equals(previous)) {
            for (User user2 :current) {
                if (!previous.contains(user2)) {
                    added++;
                }
            }
            for (User user1 : previous) {
                if (!current.contains(user1)) {
                    deleted++;
                }
                for (User user2 : current) {
                    if (user1.getId() == user2.getId() && !user1.getName().equals(user2.getName())) {
                        changed++;
                        deleted--;
                        added--;
                        break;
                    }
                }
            }
        }
        return new Info(added, changed, deleted);
    }
}
