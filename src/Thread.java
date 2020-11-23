import java.awt.*;
import java.awt.event.KeyEvent;

public class Thread extends java.lang.Thread {
    private Color color;
    private int time = 3950;
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
            System.out.println("R: " + color.getRed() + " G: " + color.getGreen() + " B: " + color.getBlue());
        } while (color.getRed() + color.getBlue() + color.getGreen() != 698);   //Ingame: 687, Screenshot: 698
        long time = System.nanoTime();

        try {
            sleep(time);    //+ ca 25ms Laufzeit
        } catch (InterruptedException e){
            e.printStackTrace();
            System.exit(-1);
        }

        r.keyPress(KeyEvent.VK_S);
        r.keyRelease(KeyEvent.VK_S);

        double d = ((System.nanoTime() - time) / 1000000000.0);
        System.out.println(d + " Sekunden");

    }
}