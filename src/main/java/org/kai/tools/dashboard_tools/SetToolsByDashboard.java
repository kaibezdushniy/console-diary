package org.kai.tools.dashboard_tools;

public class SetToolsByDashboard {
    private static final int NUMBER_SPACE_BY_CONSOLE = 400;
    private static final int TIME_DELAY = 2800; // mils - seconds (2,8 sec)
    private static final int TIME_LIGHT_DELAY = 500; // mils - seconds (1 sec)
    public void clearConsole() {
        for (int i = 0; i < NUMBER_SPACE_BY_CONSOLE; i++) {
            System.out.println();
        }
    }
    public void stopAnimation() {
        try {
            Thread.sleep(TIME_DELAY);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void stopAnimationLight() {
        try {
            Thread.sleep(TIME_LIGHT_DELAY);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public boolean validationUserInput(String input) {
        if (input != null && !input.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}
