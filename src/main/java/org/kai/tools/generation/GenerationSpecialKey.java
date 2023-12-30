package org.kai.tools.generation;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenerationSpecialKey {
    private final List<Integer> gameCodeStorage = new ArrayList<>();

    private static final String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int codeLength = 3;

    private final Random random = new Random();
    private final SecureRandom secureRandom = new SecureRandom();


    public String createSpecialKey() {
        StringBuilder sb = new StringBuilder();

        // Генерация кода из символов
        for (int i = 0; i < codeLength; i++ ) {
            // Выбираем случайный символ из строки characters
            char randomChar = characters.charAt(random.nextInt(characters.length()));

            // Добавляем символ к паролю
            sb.append(randomChar);
        }
        // Получаем строковое представление
        String code = sb.toString().toUpperCase();


        // Генерация кода из цифер
        while (true) {
            // Создаем код из цифер
            int specialNumbers = 1000 + secureRandom.nextInt(9000);

            if (isCodeExists(specialNumbers)) {
                continue;
            } else {
                gameCodeStorage.add(specialNumbers);

                // Собираем код для приватной игрыы
                String specialGameCode = code + "-" + specialNumbers;

                // Возвращаем готовый код
                return specialGameCode;
            }
        }
    }

    private boolean isCodeExists(int code) {
        if (gameCodeStorage.contains(code)) {
            return true;
        }
        return false;
    }
}

