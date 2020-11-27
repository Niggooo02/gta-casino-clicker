import java.awt.*;
import java.awt.event.KeyEvent;

public class Thread extends java.lang.Thread {
    private Color color;
    public static int time;
    @Override
    public void run() {
        Robot r = new GTACasinoClicker().bot;
        do {
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            color = GTACasinoClicker.bot.getPixelColor(GTACasinoClicker.x, GTACasinoClicker.y);
            //System.out.println("R: " + color.getRed() + " G: " + color.getGreen() + " B: " + color.getBlue());
        } while (color.getRed() + color.getBlue() + color.getGreen() != 687);   //Ingame: 687, Screenshot: 698
        long sysNanoTime = System.nanoTime();

        try {
            sleep(time);    //+ ca 25ms Laufzeit
        } catch (InterruptedException e){
            e.printStackTrace();
            System.exit(-1);
        }

        r.keyPress(KeyEvent.VK_S);
        r.keyRelease(KeyEvent.VK_S);

        double d = ((System.nanoTime() - sysNanoTime) / 1000000000.0);
        System.err.println("\nTaste wurde nach " + d + " Sekunden betätigt (+" + (d - (time / 1000.0)) * 1000 + " Millisekunden)\n");

        try {
            sleep(30000);    //30s
        } catch (InterruptedException e){
            e.printStackTrace();
            System.exit(-1);
        }
        GTACasinoClicker.stopKeyListener();
        System.exit(0); //Nach 30s automatisch beenden
    }
}