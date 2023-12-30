package org.kai.storage_data;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.kai.tools.generation.GenerationSpecialKey;
import org.kai.tools.generation.GetDataTime;


import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class StorageUserData {
    private static final String DATABASE_PATH = "user_data.txt";
    private static final int NUMBER_OF_LINES = 5;
    private static final String DATA_SEPARATOR = ":";
    private List<UserDataModel> userData = new ArrayList<>();

    private final GenerationSpecialKey generateSpecialKey = new GenerationSpecialKey();
    private final GetDataTime userDataTime = new GetDataTime();
    private final StorageSpecialKey storageSpecialKey = new StorageSpecialKey();

    private static final int LOGIN = 0;
    private static final int PASSWORD = 1;
    private static final int FULL_NAME = 2;

    public StorageUserData() {
        loadDatabase();
    }


    public void addNewUser(List<String> data) {
        if (data != null && !data.isEmpty() && data.size() == 3) {
            String newSpecialKey = generateSpecialKey.createSpecialKey();
            String createAccountDataTime = userDataTime.dataTime();


            UserDataModel newUser = new UserDataModel(
                    newSpecialKey,
                    data.get(LOGIN),
                    data.get(PASSWORD),
                    data.get(FULL_NAME),
                    createAccountDataTime
            );
            storageSpecialKey.addSpecialKey(newSpecialKey); // Добавление спец-ключа пользователя в хранилище ключей
            userData.add(newUser);
            saveDatabase();
        }
    }

    private void loadDatabase() {
        userData.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(DATABASE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(DATA_SEPARATOR);
                if (parts.length == NUMBER_OF_LINES) {
                    UserDataModel entryData = UserDataModel.fromFileString(parts);
                    userData.add(entryData);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private void saveDatabase() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(DATABASE_PATH))){
            for (UserDataModel entryData : userData) {
                writer.println(entryData.toFileString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Data
    @AllArgsConstructor
    private static class UserDataModel {
        private String specialKey;
        private String login;
        private String password;
        private String fullName;
        private String dataTime;


        public static UserDataModel fromFileString(String[] parts) {
            String specialKey = parts[0];
            String login = parts[1];
            String password = parts[2];
            String fullName = parts[3];
            String dataTime = parts[4];

            return new UserDataModel(specialKey, login, password, fullName, dataTime);
        }

        public String toFileString() {
            return specialKey + DATA_SEPARATOR + login + DATA_SEPARATOR + password + DATA_SEPARATOR + fullName + DATA_SEPARATOR + dataTime;
        }
    }
}
