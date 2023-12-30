package org.kai.storage_data;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.kai.tools.generation.GenerationNoteID;
import org.kai.tools.generation.GetDataTime;


import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class StorageNotes {
    private static final String DATABASE_PATH = "notes_data.txt";
    private static final int NUMBER_OF_LINES = 5;
    private static final String DATA_SEPARATOR = ":";
    private List<NotesModel> notesData = new ArrayList<>();

    private final GetDataTime userDatatime = new GetDataTime();
    private final StorageSpecialKey storageSpecialKey = new StorageSpecialKey();
    private final GenerationNoteID generationNoteID = new GenerationNoteID();

    private static final int SPECIAL_KEY = 0;
    private static final int TITLE = 1;
    private static final int DESCRIPTION = 2;

    public StorageNotes() {
        loadNotes();
    }


    public void addNote(List<String> data) {
        if (data != null && !data.isEmpty() && data.size() == 3) {
            // Создание хранилище для заметок
            String createDataTime = userDatatime.dataTime();
            int noteID = generationNoteID.createNoteID();
            NotesModel newNotes = new NotesModel(
                    data.get(SPECIAL_KEY),
                    noteID,
                    data.get(TITLE),
                    data.get(DESCRIPTION),
                    createDataTime
            );
            notesData.add(newNotes);
            saveNotes();
        } else {
            System.out.println("Опа опа ошибочка: Class:StorageNotes, Method:addNotes");
        }
    }


    public void deleteNote(int noteID) {
        NotesModel noteToRemove = null;

        // Поиск заметки с указанным noteID
        for (NotesModel note : notesData) {
            if (note.getNoteID() == noteID) {
                noteToRemove = note;
                break;
            }
        }

        if (noteToRemove != null) {
            notesData.remove(noteToRemove);
            saveNotes();
        } else {
            System.out.println("Опа ошибка! Class:StorageNotes | Method:deleteNote");
        }
    }



    private void loadNotes() {
        notesData.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(DATABASE_PATH))){
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(DATA_SEPARATOR);
                if (parts.length == NUMBER_OF_LINES) {
                    NotesModel entryData = NotesModel.fromFileString(parts);
                    notesData.add(entryData);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveNotes() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(DATABASE_PATH))) {
            for (NotesModel notes : notesData) {
                writer.println(notes.toFileString());
            }
        } catch (IOException e ) {
            e.printStackTrace();
        }
    }


    @Data
    @AllArgsConstructor
    private static class NotesModel {
        private String specialKey;
        private int noteID;
        private String title;
        private String description;
        private String dataTime;

        public static NotesModel fromFileString(String[] parts) {
            String specialKey = parts[0];
            int noteID = Integer.parseInt(parts[1]);
            String title = parts[2];
            String description = parts[3];
            String dataTime = parts[4];

            return new NotesModel(specialKey, noteID ,title, description, dataTime);
        }

        public String toFileString() {
            return specialKey + DATA_SEPARATOR + noteID  + DATA_SEPARATOR + title + DATA_SEPARATOR + description + DATA_SEPARATOR + dataTime;
        }
    }
}
