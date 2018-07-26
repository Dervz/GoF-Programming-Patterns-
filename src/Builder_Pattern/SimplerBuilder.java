package Builder_Pattern;

public class SimplerBuilder {

    public static void main(String[] args) {
        Computer computer = new ComputerBuilder()
                .buildVideoCard(VideoCardMaker.NVIDIA)
                .buildMotherBoard(MotherBoardMaker.MSI)
                .buildCPU(CPUMaker.INTEL)
                .buildCooling(CoolingType.WATER_COOLING)
                .buildRAM(32)
                .build();

        System.out.println(computer);
    }
}

enum MotherBoardMaker {
    ASUS, MSI, GIGABYTE
}

enum VideoCardMaker {
    RADEON, NVIDIA
}

enum CPUMaker {
    AMD, INTEL
}

enum CoolingType {
    WATER_COOLING, FAN_COOLING
}

class Computer {

    /**
     * Bunch of private variables that determine the state of Computer
     */
    private MotherBoardMaker motherboardMaker;
    private VideoCardMaker videoCardMaker;
    private CPUMaker cpuMaker;
    private int RAM;
    private CoolingType coolingType;

    public void setMotherboardMaker(MotherBoardMaker motherboardMaker) {
        this.motherboardMaker = motherboardMaker;
    }

    public void setVideoCardMaker(VideoCardMaker videoCardMaker) {
        this.videoCardMaker = videoCardMaker;
    }

    public void setCpuMaker(CPUMaker cpuMaker) {
        this.cpuMaker = cpuMaker;
    }

    public void setRAM(int RAM) {
        this.RAM = RAM;
    }

    public void setCoolingType(CoolingType coolingType) {
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

class ComputerBuilder {
    /**
     * Same types of variables that determine the state of Computer
     * Default values are given
     */
    private MotherBoardMaker motherboardMaker = MotherBoardMaker.ASUS;
    private VideoCardMaker videoCardMaker = VideoCardMaker.RADEON;
    private CPUMaker cpuMaker = CPUMaker.INTEL;
    private int RAM = 16;
    private CoolingType coolingType = CoolingType.FAN_COOLING;

    ComputerBuilder buildMotherBoard(MotherBoardMaker motherboardMaker) {
        this.motherboardMaker = motherboardMaker;
        return this;
    }

    ComputerBuilder buildCPU(CPUMaker cpuMaker) {
        this.cpuMaker = cpuMaker;
        return this;
    }

    ComputerBuilder buildVideoCard(VideoCardMaker videoCardMaker) {
        this.videoCardMaker = videoCardMaker;
        return this;
    }

    ComputerBuilder buildCooling(CoolingType coolingType) {
        this.coolingType = coolingType;
        return this;
    }

    ComputerBuilder buildRAM(int ram) {
        this.RAM = ram;
        return this;
    }

    /**
     * BUILDER METHOD
     * BUILDS THE COMPUTER AND RRETURNS IT
     *
     * @return
     */
    Computer build() {
        Computer comp = new Computer();
        comp.setCoolingType(coolingType);
        comp.setCpuMaker(cpuMaker);
        comp.setMotherboardMaker(motherboardMaker);
        comp.setRAM(RAM);
        comp.setVideoCardMaker(videoCardMaker);

        return comp;
    }
}