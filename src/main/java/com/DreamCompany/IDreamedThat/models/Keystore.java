package com.DreamCompany.IDreamedThat.models;

import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.*;

@NoArgsConstructor
public class Keystore {
    private static final Map<String, Recovery> recoveryStorage = new HashMap<>();

    private static final PriorityQueue<Map.Entry<String, Recovery>> dateQueue = new PriorityQueue<>(
            Comparator.comparing(e -> e.getValue().getRequestedDate())
    );

    public static void addKey(String email, Recovery recovery){
        recoveryStorage.put(email, recovery);
    }

    public static String getterKey(String email){
        return recoveryStorage.get(email).getKeyGenerated();
    }

    public static void clearKey(String email){
        recoveryStorage.remove(email);
    }

    public static void clearStorage(){
        LocalDateTime oneDayAgo = LocalDateTime.now().minusDays(1);
        while (!dateQueue.isEmpty() && dateQueue.peek().getValue().getRequestedDate().isBefore(oneDayAgo)) {
            Map.Entry<String, Recovery> entry = dateQueue.poll();
            recoveryStorage.remove(entry.getKey());
        }
    }

}
