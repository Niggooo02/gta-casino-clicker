import org.jnativehook.keyboard.NativeKeyEvent;

import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class GTACasinoClicker {
    public static int x = 152, y = 28;      //Standard
    public static Robot bot;
    public static int arrayListCapacity = 2;
    public static GlobalKeyListener globalKeyListener;
    public static boolean configMode = false;
    private static Scanner sc;

    public static void main(String[] args) {
        System.err.println("\nKompatibilität: GTA V im Vollbildmodus, nur ein Monitor, FHD / 1920x1080px\n");
        sc = new Scanner(System.in);
        System.out.print("Zeit, die gewartet werden soll (in Millisekunden, z.B. 2050): ");
        Thread.time = sc.nextInt();
        System.out.println("\n\n");

        new GTACasinoClicker();

        System.out.println("\nHOTKEYS:");
        System.out.println("'^':\t\tScreen Reader aktivieren");
        System.out.println("'Strg' + 'Y':\tGTA force quit");
        System.out.println();
    }
    public GTACasinoClicker() {
        startKeyListener();
        try {
            bot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
    public static void startKeyListener() {
        ArrayList<ArrayList<Integer>> keybinds = new ArrayList<ArrayList<Integer>>(arrayListCapacity); //Spalten, einzelne Keybinds
        for (int i = 0; i<arrayListCapacity; i++){
            keybinds.add(new ArrayList<Integer>()); //Zeilen, int key und int modifier
        }
        keybinds.get(0).add(0, Integer.parseInt("43")); //43: ^
        keybinds.get(0).add(1, Integer.parseInt("-1")); //-1: nichts
        keybinds.get(1).add(0, NativeKeyEvent.VC_CONTROL); // : Strg
        keybinds.get(1).add(1, NativeKeyEvent.VC_Y); // : Y

        globalKeyListener = new GlobalKeyListener(keybinds);
        globalKeyListener.start();
    }
    public static void stopKeyListener() {
        globalKeyListener.interrupt();
    }
    public static void setCoords() {
        PointerInfo pointerInfo = MouseInfo.getPointerInfo();
        Point point = pointerInfo.getLocation();
        x = (int) point.getX();
        y = (int) point.getY();
        System.out.println("X: " + x + "    " + "Y: " + y);

        System.out.println("setX: " + x + "  setY: " + y + "  Red: " + bot.getPixelColor(x, y).getRed() + "  Green: " +
                bot.getPixelColor(x, y).getGreen() + "  Blue: " + bot.getPixelColor(x, y).getBlue());
    }
}