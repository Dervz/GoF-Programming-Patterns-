package Facade_Pattern;


/**
 * Whole idea of these classes:
 * 1) Turn on the PowerSupply of the computer
 * 2) Load DVDRom
 * 3) Copy data from DVD to HDD
 */
public class Facade {

    public static void main(String[] args) {

        /**
         * The logic here would be to perform something similar to the following:

         PowerSupply ps = new PowerSupply();
         DVDRom dvd = new DVDRom();
         HDD hdd = new HDD();

         ps.turnON();
         dvd.load();
         hdd.copyDataFromDVD(dvd, ps);

         However, all of this logic will be performed by the FACADE class.
         Therefore, we only need to instantiate it and order it to do its tasks.

         HOWEVER once we order him to do stuff, we think its him who does that stuff.
         It is not true, since he orders other classes to do something.
         So Computer Class only works as a facade class (i.e manages tasks under the hood)
         */

        Computer computer = new Computer();
        computer.performCopy();
    }
}


class PowerSupply {
    private boolean turnedON = false;

    public void turnON() {
        System.out.println("Power is ON");
        turnedON = true;
    }

    public void turnOFF() {
        System.out.println("Power is OFF");
        turnedON = false;
    }

    public boolean isTurnedON() {
        return turnedON;
    }
}

class DVDRom {
    private boolean loaded = false;

    public void load() {
        System.out.println("DVDRom is LOADED");
        loaded = true;
    }

    public void unload() {
        System.out.println("DVDRom is UNLOADED");
        loaded = false;
    }

    public boolean isLoaded() {
        return loaded;
    }
}

class HDD {
    public void copyDataFromDVD(DVDRom dvd, PowerSupply supply) {
        if (supply.isTurnedON() && dvd.isLoaded()) {
            System.out.println("********** Copying data from DVD to the HDD **********");
        } else if (supply.isTurnedON() && !dvd.isLoaded()) {
            System.out.println("Data cannot be copied. Supply is ON. But DVDRom is UNLOADED");
        } else if (!supply.isTurnedON() && dvd.isLoaded()) {
            System.out.println("Data cannot be copied. DVDRom is LOADED. But supply is OFF");
        }
        else{
            System.out.println("Data cannot be copied. DVDRom is UNLOADED and the supply is OFF");
        }
    }

}

/**
 * ********** FACADE CLASS ***********
 */
class Computer {
    PowerSupply ps = new PowerSupply();
    DVDRom dvd = new DVDRom();
    HDD hdd = new HDD();

    /**
     * This method actually contains all the logic.
     * As you can notice, it is very similar to the Delegate Pattern.
     * The most obvious difference here between these two patterns
     * is that Facade class actually calls out to MORE THAN ONE class to do stuff.
     * On the other hand, Manager from Delegate Pattern only called out work() once on the
     * object that he received thia his mutator method.
     */
    public void performCopy() {
        ps.turnON();
        dvd.load();
        hdd.copyDataFromDVD(dvd, ps);

        System.out.println("\n");
        ps.turnOFF();
        hdd.copyDataFromDVD(dvd, ps);

        System.out.println("\n");
        dvd.unload();
        hdd.copyDataFromDVD(dvd, ps);

        System.out.println("\n");
        dvd.load();
        ps.turnON();
        hdd.copyDataFromDVD(dvd, ps);
    }
}

