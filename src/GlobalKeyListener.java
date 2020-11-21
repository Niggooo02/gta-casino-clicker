import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GlobalKeyListener extends Thread {

    private ArrayList<ArrayList<Integer>> keybinds;
    private ArrayList<ArrayList<Boolean>> keybindactive;
    private GTACasinoClicker cc;
    private boolean active = false;

    public void run(){
        cc = new GTACasinoClicker();
        keybindactive = new ArrayList<ArrayList<Boolean>>(cc.getArrayListCapacity());
        for (int i = 0; i<cc.getArrayListCapacity(); i++){
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
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        switch (arrayPos){
            case 0:
                if(!cc.isConfigMode() && !active) {
                    active = true;
                    new Thread().start();
                } else if(cc.isConfigMode() && !active){
                    active = true;
                    System.out.println("Config Mode active");
                    cc.setCoords();
                }
                break;
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
