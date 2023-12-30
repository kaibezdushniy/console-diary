package org.kai.storage_data;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StorageMyAccounts {
    private static final String DATABASE_PATH = "my_accounts_data.txt";
    private static final int NUMBER_OF_LINES = 4;
    private static final String DATA_SEPARATOR = ":";
    private List<MyAccountsModel> storageMyAccounts = new ArrayList<>();


    public void addMyAccount() {

    }


    private void loadMyAccounts() {
        try (BufferedReader reader = new BufferedReader(new FileReader(DATABASE_PATH))){
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(DATA_SEPARATOR);
                if (parts.length == NUMBER_OF_LINES) {
                    MyAccountsModel entryData = MyAccountsModel.fromFileString(parts);
                    storageMyAccounts.add(entryData);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveMyAccounts() {

    }




    @Data
    @AllArgsConstructor
    private static class MyAccountsModel {
        private String specialKey;
        private String login;
        private String password;
        private String name;

        public static MyAccountsModel fromFileString(String[] parts ) {
            String specialKey = parts[0];
            String login = parts[1];
            String password = parts[2];
            String name = parts[3];

            return new MyAccountsModel(specialKey, login, password, name);
        }

        public String toFileString() {
            return specialKey + DATA_SEPARATOR + login + DATA_SEPARATOR + password + DATA_SEPARATOR + name;
        }
    }
}
