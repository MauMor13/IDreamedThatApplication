package com.DreamCompany.IDreamedThat.utils;

import com.DreamCompany.IDreamedThat.models.PostDream;
import com.DreamCompany.IDreamedThat.models.SocialUser;
import com.DreamCompany.IDreamedThat.services.ServicePostDream;
import java.util.Random;

public class Utilitis {

    public static void createPosts(SocialUser socialUser, int number, ServicePostDream servicePostDream){
         for (int i = 0; i < number; i++){
             PostDream newPost = new PostDream( Utilitis.generateRandomWord(), Utilitis.generateRandomWords(200), false);
             newPost.addSocialUser(socialUser);
             servicePostDream.save(newPost);
         }
    }

    public static String generateRandomWord() {
        Random random = new Random();
        int wordLength = random.nextInt(4) + 5; // Genera un número aleatorio entre 5 y 8
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < wordLength; i++) {
            char randomChar = (char) (random.nextInt(26) + 'a'); // Genera una letra minúscula aleatoria
            sb.append(randomChar);
        }
        return sb.toString();
    }

    public static String generateRandomWords(int numberOfWords) {
        StringBuilder result = new StringBuilder();
        Random random = new Random();
        int totalLength = 0;
        for (int i = 0; i < numberOfWords; i++) {
            String word = generateRandomWord();
            // Verifica si agregar la palabra excede el límite de 500 caracteres
            if (totalLength + word.length() + 1 > 500) {
                break;
            }
            if (i > 0) {
                result.append(" "); // Agrega un espacio entre palabras
                totalLength++;
            }
            result.append(word);
            totalLength += word.length();
        }
        return result.toString();
    }

}
