package org.kai.main_dashboard;

import org.kai.storage_data.StorageNotes;
import org.kai.tools.dashboard_tools.Animation;
import org.kai.tools.dashboard_tools.SetToolsByDashboard;
import org.kai.user_profile.ActivityUserData;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManageNote {
    private final StorageNotes storageNotes = new StorageNotes();
    private final ActivityUserData activityUserData = new ActivityUserData();
    private final Animation animation = new Animation();
    private final Scanner scanner = new Scanner(System.in);
    private final SetToolsByDashboard tools = new SetToolsByDashboard();

    private List<String> storageUserInput = new ArrayList<>();

    public static void main(String[] args) {
        ManageNote manageNote = new ManageNote();
        manageNote.deleteNote("Салам", 761515);
    }

    public void addNewNote() {
        boolean isExitLoop = false;
        while (!isExitLoop) {
            System.out.println(activityUserData.getFullName() + " , Придумайте заголовок для вашей новой Заметки!");
            System.out.print("--> ");
            String title = scanner.nextLine();

            if (!tools.validationUserInput(title)) {
                System.out.println("Вы оставили заголовок пустым!, " + activityUserData.getFullName() + " Пожалуйста попробуйте еще раз.");
                tools.stopAnimation();
                tools.clearConsole();
                continue;
            }

            System.out.println("Отлично! теперь введите описане для Заметки " + title);
            String description = scanner.nextLine();

            if (!tools.validationUserInput(description)) {
                System.out.println("Упс..Вы оставили описание пустым, " + activityUserData.getFullName() + " Пожалуйста попробуйте еще раз.");
                tools.stopAnimation();
                tools.clearConsole();
                continue;
            }


            // Передаем полученные данные в метод созданий заметок
            storageUserInput.add(activityUserData.getSpecialKey());
            storageUserInput.add(title);
            storageUserInput.add(description);
            storageNotes.addNote(storageUserInput);



            animation.successfullyAnimation();
            System.out.println("\n" + activityUserData.getFullName() + ", Заметка успешно создана!");
            tools.stopAnimation();
        }
    }
    public void deleteNote(String title, int noteID) {
        boolean isExitLoop = false;
        while (!isExitLoop) {
            System.out.println("Вы точно хотите удалить заметку " + title + " ?");
            System.out.println("1.Да");
            System.out.println("2.Нет");
            System.out.print("--> ");
            String choose = scanner.nextLine();

            if (choose.trim().equalsIgnoreCase("1")) {
                storageNotes.deleteNote(noteID);
                animation.successfullyAnimation();
                System.out.println(activityUserData.getFullName() + ", Заметка успешно удалена!");
            } else if (choose.trim().equalsIgnoreCase("2")){
                tools.clearConsole();
                isExitLoop = true;
            } else {
                tools.clearConsole();
                continue;
            }
        }
    }
    public void editNote(int noteID) {
        boolean isExitLoop = false;
        while (!isExitLoop) {

        }
    }

}
