package com.DreamCompany.IDreamedThat.models;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.*;

@Component
public class Keystore {
    private static final Map<String, Recovery> recoveryStorage = new HashMap<>();

    private static final PriorityQueue<Map.Entry<String, Recovery>> dateQueue = new PriorityQueue<>(
            Comparator.comparing(e -> e.getValue().getRequestedDate())
    );

    public static void addKey(String key, Recovery recovery){
        recoveryStorage.put(key, recovery);
    }

    public static String getterEmail(String key){
        return recoveryStorage.get(key).getEmailVerification();
    }

    public static void clearKey(String key){
        recoveryStorage.remove(key);
    }
    @Scheduled(cron = "0 0 0 * * ?")
    public static void clearStorage(){
        LocalDateTime oneDayAgo = LocalDateTime.now().minusDays(1);
        while (!dateQueue.isEmpty() && dateQueue.peek().getValue().getRequestedDate().isBefore(oneDayAgo)) {
            Map.Entry<String, Recovery> entry = dateQueue.poll();
            recoveryStorage.remove(entry.getKey());
        }
    }

}
