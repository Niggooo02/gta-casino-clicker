import java.awt.*;
import java.util.ArrayList;

public class GTACasinoClicker {
    public static int x = 152, y = 28;      //Standard
    public static Robot bot;
    public static int arrayListCapacity = 1;
    public static GlobalKeyListener globalKeyListener;
    public static boolean configMode = false;

    public static void main(String[] args) {
        new GTACasinoClicker();
    }
    public GTACasinoClicker(){
        startKeyListener();
        try {
            bot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
    public static void startKeyListener(){
        ArrayList<ArrayList<Integer>> keybinds = new ArrayList<ArrayList<Integer>>(arrayListCapacity); //Spalten, einzelne Keybinds
        for (int i = 0; i<arrayListCapacity; i++){
            keybinds.add(new ArrayList<Integer>()); //Zeilen, int key und int modifier
        }
        keybinds.get(0).add(0, Integer.parseInt("43")); //43: ^
        keybinds.get(0).add(1, Integer.parseInt("-1")); //-1: nichts

        globalKeyListener = new GlobalKeyListener(keybinds);
        globalKeyListener.start();
    }
    public static void stopKeyListener(){
        globalKeyListener.interrupt();
    }
    public static void setCoords(){
        PointerInfo pointerInfo = MouseInfo.getPointerInfo();
        Point point = pointerInfo.getLocation();
        x = (int) point.getX();
        y = (int) point.getY();
        System.out.println("X: " + x + "    " + "Y: " + y);

        System.out.println("setX: " + x + "  setY: " + y + "  Red: " + bot.getPixelColor(x, y).getRed() + "  Green: " +
                bot.getPixelColor(x, y).getGreen() + "  Blue: " + bot.getPixelColor(x, y).getBlue());
    }
}