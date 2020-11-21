import java.awt.*;
import java.awt.event.KeyEvent;

public class Thread extends java.lang.Thread {
    private Color color;
    @Override
    public void run() {
        do {
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            color = GTACasinoClicker.bot.getPixelColor(GTACasinoClicker.x, GTACasinoClicker.y);
        } while (color.getRed() != color.getBlue() || color.getRed() != color.getGreen() || color.getRed() != 229);
        long time = System.nanoTime();

        try {
            sleep(1400);
        } catch (InterruptedException e){
            e.printStackTrace();
            System.exit(-1);
        }

        GTACasinoClicker.bot.keyPress(KeyEvent.VK_S);
        GTACasinoClicker.bot.keyRelease(KeyEvent.VK_S);

        double d = ((System.nanoTime() - time) / 1000000000.0);
        System.out.println(d + " Sekunden");

    }
}