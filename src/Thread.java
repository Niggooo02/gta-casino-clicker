import java.awt.*;
import java.awt.event.KeyEvent;

public class Thread extends java.lang.Thread {
    private Color color;
    private int x, y;
    @Override
    public void run() {
        GTACasinoClicker cc = new GTACasinoClicker();
        x = cc.getX();
        y = cc.getY();
        do {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            color = GTACasinoClicker.bot.getPixelColor(x, y);
        } while (color.getRed() != color.getBlue() || color.getRed() != color.getGreen() || color.getRed() != 229);
        long time = System.nanoTime();

        try {
            sleep(1400);
        } catch (InterruptedException e){
            e.printStackTrace();
            System.exit(-1);
        }

        cc.bot.keyPress(KeyEvent.VK_S);
        GTACasinoClicker.bot.keyRelease(KeyEvent.VK_S);

        double d = ((System.nanoTime() - time) / 1000000000.0);
        System.out.println(d + " Sekunden");

        cc.stopKeyListener();
        this.interrupt();
    }
}