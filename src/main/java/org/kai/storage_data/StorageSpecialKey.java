package org.kai.storage_data;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StorageSpecialKey {
    private static final String DATABASE_PATH = "special_key_data.txt";
    private static final int NUMBER_OF_LINES = 1;
    private static final String DATA_SEPARATOR = ":";
    private List<SpecialKeyModel> specialKeyData = new ArrayList<>();

    public StorageSpecialKey() {
        loadSpecialKey();
    }


    public void addSpecialKey(String specialKey) {
        if (specialKey != null) {
            if (!validationSpecialKey(specialKey)) {
                SpecialKeyModel entryData = new SpecialKeyModel(specialKey);
                specialKeyData.add(entryData);
                saveSpecialKey();
            } else {
                throw new IllegalArgumentException();
            }
        }
    }

    public boolean validationSpecialKey(String specialKey) {
        if (specialKey != null) {
            for (SpecialKeyModel entryData : specialKeyData) {
                if (entryData.getSpecialKey().equals(specialKey)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void loadSpecialKey() {
        specialKeyData.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(DATABASE_PATH))){
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(DATA_SEPARATOR);
                if (parts.length == NUMBER_OF_LINES) {
                    SpecialKeyModel entryData = SpecialKeyModel.fromFileString(parts);
                    specialKeyData.add(entryData);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveSpecialKey() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(DATABASE_PATH))) {
            for (SpecialKeyModel entryData : specialKeyData) {
                writer.println(entryData.toFileString());
            }
        } catch (IOException e ) {
            e.printStackTrace();
        }
    }

    @Data
    @AllArgsConstructor
    private static class SpecialKeyModel {
        private String specialKey;


        public static SpecialKeyModel fromFileString(String[] parts) {
            String specialKey = parts[0];

            return new SpecialKeyModel(specialKey);
        }

        public String toFileString() {
            return specialKey + DATA_SEPARATOR;
        }
    }
}
