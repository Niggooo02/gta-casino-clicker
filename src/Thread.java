import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Thread extends java.lang.Thread {
    private Color color;
    static long time = 1000;        //Standard
    private String fileName = "C:\\Users\\nicog\\Desktop\\gta-cc-log.txt";
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

        System.out.println();
        System.out.println("Pixel entdeckt");

        long sysTime = System.nanoTime();

        try {
            sleep(time);
        } catch (InterruptedException e){
            e.printStackTrace();
            System.exit(-1);
        }

        GTACasinoClicker.bot.keyPress(KeyEvent.VK_S);
        GTACasinoClicker.bot.keyRelease(KeyEvent.VK_S);

        double d = ((System.nanoTime() - sysTime) / 1000000000.0);
        double differenz = (d - ((double)time / 1000)) * 1000;  //in Millisekunden
        System.out.println();
        System.out.println("Taste 'S' wurde nach " + d + " Sekunden betätigt (Differenz zur Eingabe: " + differenz + " Millisekunden)");

        try (FileOutputStream fos = new FileOutputStream(fileName, true)) {
            String text = "";
            if(differenz > 0){
                text += "+";
            }
            text += differenz + " Millisekunden\t(" + (double)time / 1000 + " | " + d + ")\n";
            byte[] mybytes = text.getBytes();

            fos.write(mybytes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        GTACasinoClicker.stopKeyListener();
        System.exit(0);
    }
}