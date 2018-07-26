package Builder_Pattern;

public class BetterBuilder {

    public static void main(String[] args) {

        Director director = new Director();

        director.setComputerBuilderB(new GamingComputerB());      //all we have to do here is to just supply different builder
        director.buildComputerB();

        ComputerB gamingComputerB = director.computerBuilderB.getComputerB();
        System.out.println(gamingComputerB);

        director.setComputerBuilderB(new ProgrammingComputerB()); //like this && it gives awesome flexibility. Only client code gets changed
        director.buildComputerB();

        ComputerB programmingComputerB = director.computerBuilderB.getComputerB();
        System.out.println("\n*************************************************\n\n" + programmingComputerB);
    }
}

/**
 * Bunch of enums that define the state of
 * ComputerB objects by supplying the value of
 */
enum MotherBoardMakerB {
    ASUS, MSI, GIGABYTE
}

enum VideoCardMakerB {
    RADEON, NVIDIA
}

enum CPUMakerB {
    AMD, INTEL
}

enum CoolingTypeB {
    WATER_COOLING, FAN_COOLING
}

/**
 * This class is the PRODUCT
 * We will be building this product with the BUILDER classes.
 */
class ComputerB {

    /**
     * Bunch of private variables that determine the state of ComputerB
     */
    private MotherBoardMakerB motherboardMaker;
    private VideoCardMakerB videoCardMaker;
    private CPUMakerB cpuMaker;
    private int RAM;
    private CoolingTypeB coolingType;

    /**
     * Bunch of setters for the private variables
     * The builders will use these setters in order to instantiate the object of ComputerB
     */
    public void setMotherboardMaker(MotherBoardMakerB motherboardMaker) {
        this.motherboardMaker = motherboardMaker;
    }

    public void setVideoCardMaker(VideoCardMakerB videoCardMaker) {
        this.videoCardMaker = videoCardMaker;
    }

    public void setCpuMaker(CPUMakerB cpuMaker) {
        this.cpuMaker = cpuMaker;
    }

    public void setRAM(int RAM) {
        this.RAM = RAM;
    }

    public void setCoolingType(CoolingTypeB coolingType) {
        this.coolingType = coolingType;
    }

    @Override
    public String toString() {
        return "This computer has the following spec:" +
                "\nVideo Card maker: " + videoCardMaker +
                "\nMotherBoard maker: " + motherboardMaker +
                "\nCPU maker: " + cpuMaker +
                "\nCooling type: " + coolingType +
                "\nRAM (GB): " + RAM;
    }
}

/**
 * Abstract builder of ComputerB.
 * Note that it is abstract for one simple reason: possible extends by the new classes in the future
 */
abstract class ComputerBuilderB {

    ComputerB computerB;

    void createComputerB() {
        computerB = new ComputerB();
    }

    abstract void buildMotherBoard();

    abstract void buildVideoCard();

    abstract void buildCPU();

    abstract void buildCoolingSystem();

    abstract void buildRAM();

    ComputerB getComputerB() {
        return computerB;
    }
}

/**
 * Concrete BUILDER for the gaming computer.
 * Notice that it properly sets the values for all of variables of the computer - i.e it sets its state.
 */
class GamingComputerB extends ComputerBuilderB {

    @Override
    void buildMotherBoard() {
        getComputerB().setMotherboardMaker(MotherBoardMakerB.MSI);
    }

    @Override
    void buildVideoCard() {
        getComputerB().setVideoCardMaker(VideoCardMakerB.NVIDIA);
    }

    @Override
    void buildCPU() {
        getComputerB().setCpuMaker(CPUMakerB.INTEL);
    }

    @Override
    void buildCoolingSystem() {
        getComputerB().setCoolingType(CoolingTypeB.WATER_COOLING);
    }

    @Override
    void buildRAM() {
        getComputerB().setRAM(32);
    }
}


/**
 * Concrete BUILDER for the programming computer.
 * Notice that it properly sets the values for all of variables of the computer - i.e it sets its state.
 */
class ProgrammingComputerB extends ComputerBuilderB {

    @Override
    void buildMotherBoard() {
        getComputerB().setMotherboardMaker(MotherBoardMakerB.ASUS);
    }

    @Override
    void buildVideoCard() {
        getComputerB().setVideoCardMaker(VideoCardMakerB.RADEON);
    }

    @Override
    void buildCPU() {
        getComputerB().setCpuMaker(CPUMakerB.AMD);
    }

    @Override
    void buildCoolingSystem() {
        getComputerB().setCoolingType(CoolingTypeB.FAN_COOLING);
    }

    @Override
    void buildRAM() {
        getComputerB().setRAM(16);
    }
}

/**
 * Director class plays important role in the builder pattern
 * 1) Declares computerBuilder
 * 2) Sets computer builder via constructor
 * 3) Builds computer with the corresponding builder
 * 4) Returns build computer
 */
class Director {
    ComputerBuilderB computerBuilderB;

    public void setComputerBuilderB(ComputerBuilderB computerBuilderB) {
        this.computerBuilderB = computerBuilderB;
    }

    ComputerB buildComputerB() {
        computerBuilderB.createComputerB();
        computerBuilderB.buildCPU();
        computerBuilderB.buildMotherBoard();
        computerBuilderB.buildVideoCard();
        computerBuilderB.buildRAM();
        computerBuilderB.buildCoolingSystem();

        return computerBuilderB.getComputerB();

    }
}