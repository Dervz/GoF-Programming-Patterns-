package Builder_Pattern;

public class BetterBuilderPattern {

    public static void main(String[] args) {

        Director director = new Director();

        director.setComputerBuilderB(new GamingComputerB());
        director.buildComputerB();

        ComputerB computerB = director.computerBuilderB.getComputerB();
        System.out.println(computerB);
    }
}

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

class ComputerB {

    /**
     * Bunch of private variables that determine the state of Computer
     */
    private MotherBoardMakerB motherboardMaker;
    private VideoCardMakerB videoCardMaker;
    private CPUMakerB cpuMaker;
    private int RAM;
    private CoolingTypeB coolingType;

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