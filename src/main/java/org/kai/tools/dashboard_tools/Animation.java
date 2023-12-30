package org.kai.tools.dashboard_tools;

public class Animation {

    public static void main(String[] args) {
        Animation animation = new Animation();
        animation.successfullyAnimation();
    }
    public void successfullyAnimation() {
        try {
            int length = 10;
            int angle = 0;


            int count = 0;
            while (true) {
                count++;
                if (count == 20) {
                    break;
                }
                System.out.print("-");
                int position = (int) (length * Math.sin(Math.toRadians(angle)));
                System.out.print("\r");
                printSpaces(position);
                System.out.print("-");
                Thread.sleep(100);
                angle = (angle + 10) % 360;
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private static void printSpaces(int count) {
        for (int i = 0; i < count; i++) {
            System.out.print(" ");
        }
    }
}
