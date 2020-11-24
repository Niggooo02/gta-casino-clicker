import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GlobalKeyListener extends Thread {

    public ArrayList<ArrayList<Integer>> keybinds = new ArrayList<ArrayList<Integer>>();
    public ArrayList<ArrayList<Boolean>> keybindactive = new ArrayList<ArrayList<Boolean>>(GTACasinoClicker.arrayListCapacity);
    private Runtime runtime;

    public void run(){
        runtime = Runtime.getRuntime();
        for (int i = 0; i<GTACasinoClicker.arrayListCapacity; i++){
            keybinds.add(new ArrayList<Integer>()); //Zeilen, int key und int modifier booleans
            keybindactive.add(new ArrayList<Boolean>()); //Zeilen, int key und int modifier booleans
            for (int j = 0; j < 2; j++){
                keybindactive.get(i).add(j, false);
            }
        }

        try {
            GlobalScreen.registerNativeHook();
        }
        catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());

            System.exit(1);
        }

        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setUseParentHandlers(false);
        logger.setLevel(Level.WARNING);

        GlobalScreen.addNativeKeyListener(new NativeKeyListener() {

            @Override
            public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {

//                if (nativeKeyEvent.getKeyCode() == 43){
//                    SaveBucket.startSaveBucket();
//                }

                int i = 0;
                for (ArrayList<Integer> keys : keybinds){
                    int j = 0;
                    for (int k : keys){
                        if (nativeKeyEvent.getKeyCode() == k || k == -1){
                            keybindactive.get(i).set(j, true);
                        }
                        j++;
                    }
                    i++;
                }
                int arrayPos = 0;
                for (ArrayList<Boolean> active : keybindactive){
                    int aCount = 0;
                    for (int a = 0; a < active.size(); a++){
                        if (active.get(a)){
                            aCount++;
//                            System.out.println(arrayPos + "  " + aCount + "   " + active.size());
                            if (aCount == active.size()){
//                                System.out.println("should exec " +  arrayPos);
                                execute(arrayPos);
                            }
                        }
                    }
                    arrayPos++;
                }
            }

            @Override
            public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {
                //System.out.println("Key Typed: " + nativeKeyEvent.getKeyText(nativeKeyEvent.getKeyCode()));
            }

            @Override
            public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {
                //System.out.println("Key Released: " + NativeKeyEvent.getKeyText(nativeKeyEvent.getKeyCode()));
                int i = 0;
                for (ArrayList<Integer> keys : keybinds){
                    int j = 0;
                    for (int k : keys){
                        if (nativeKeyEvent.getKeyCode() == k){
                            keybindactive.get(i).set(j, false);
                        }
                        j++;
                    }
                    i++;
                }
            }
        });
    }
    public GlobalKeyListener(ArrayList<ArrayList<Integer>> keyAndModifier){
        keybinds = keyAndModifier;
    }

    public void execute(int arrayPos){
        switch (arrayPos){
            case 0:
                if(!GTACasinoClicker.configMode) {
                    System.out.println("Screen detection active");
                    new Thread().start();
                } else {
                    System.out.println("Config Mode active");
                    GTACasinoClicker.setCoords();
                }
                break;
            case 1:
                System.out.println("GTA wird beendet");
                try {
                    runtime.exec("cmd /c taskkill /F /IM GTA5.exe");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.exit(0);
        }
    }

    @Override
    public void interrupt() {
        try {
            GlobalScreen.unregisterNativeHook();
        } catch (NativeHookException e) {
            e.printStackTrace();
        }
        super.interrupt();
    }
}
