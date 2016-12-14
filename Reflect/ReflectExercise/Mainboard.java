package Reflect.ReflectExercise;

import javax.sound.midi.Soundbank;

/**
 * Created by Taocr on 2016/12/14.
 */
public class Mainboard {
    public void run() {
        System.out.println("main board  run");
    }

    public void usePCI(PCI pci) {
        if (pci != null) {
            pci.open();
            pci.close();
        }
    }
}
