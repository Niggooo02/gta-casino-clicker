import java.awt.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class GTACasinoClicker {
    static int x = 152, y = 28;      //Standard
    static Robot bot;
    static int arrayListCapacity = 1;
    static GlobalKeyListener globalKeyListener;
    static boolean configMode = false;
    private Scanner scanner = new Scanner(System.in);
    private String fileName = "/config.json";

    public static void main(String[] args) {
        new GTACasinoClicker();
    }
    public GTACasinoClicker(){
        System.out.println("Reading config: " + fileName);
        try {
            JSONParser parser = new JSONParser();
            JSONArray a = (JSONArray) parser.parse(new FileReader("src/config.json"));

            for (Object o : a) {
                JSONObject jsonObject = (JSONObject) o;

                x = (String) jsonObject.get("X");
                System.out.println(x);

                y = (String) jsonObject.get("Y");
                System.out.println(y);

                Thread.time = (long) jsonObject.get("Time");
                System.out.println(Thread.time);
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

        System.out.print("Auslösen nach (Millisekunden): ");
        Thread.time = scanner.nextInt();
        startKeyListener();
        try {
            bot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        System.out.println();
        System.out.println("Hotkey: '^'");
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