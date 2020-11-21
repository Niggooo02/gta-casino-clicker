import java.awt.*;
import java.util.ArrayList;

public class GTACasinoClicker {
    private int x = 152, y = 28;      //Standardwerte
    public static Robot bot;
    private int arrayListCapacity = 1;
    private GlobalKeyListener globalKeyListener;
    private boolean configMode = false;

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
    public void startKeyListener(){
        ArrayList<ArrayList<Integer>> keybinds = new ArrayList<>(arrayListCapacity); //Spalten, einzelne Keybinds
        for (int i = 0; i<arrayListCapacity; i++){
            keybinds.add(new ArrayList<Integer>()); //Zeilen, int key und int modifier
        }
        keybinds.get(0).add(0, Integer.parseInt("43")); //43: ^
        keybinds.get(0).add(1, Integer.parseInt("-1")); //-1: nichts

        globalKeyListener = new GlobalKeyListener(keybinds);
        globalKeyListener.start();
    }
    public void stopKeyListener(){
        globalKeyListener.interrupt();
    }
    public void setCoords(){
        PointerInfo pointerInfo = MouseInfo.getPointerInfo();
        Point point = pointerInfo.getLocation();
        x = (int) point.getX();
        y = (int) point.getY();
        System.out.println("X: " + x + "    " + "Y: " + y);

        System.out.println("setX: " + x + "  setY: " + y + "  Red: " + bot.getPixelColor(x, y).getRed() + "  Green: " +
                bot.getPixelColor(x, y).getGreen() + "  Blue: " + bot.getPixelColor(x, y).getBlue());
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getArrayListCapacity() {
        return arrayListCapacity;
    }
    public boolean isConfigMode() {
        return configMode;
    }
}