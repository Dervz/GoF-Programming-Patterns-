package Adapter_Pattern;

/**
 * The main point here, that in the client code, i.e main method
 * we are not allowed to use the class Vehicle. However, the only thing
 * we are restricted to use is the interface. In order to successfully use both interface
 * and the class Vehicle, we ought to create an Adapter class.
 * So, initially we only have a class {@link Vehicle} and interface {@link Movable}
 * <p>
 * There are two ways of implementing Adapter Pattern.
 * <p>
 * First way is via inheritance. We can create a class {@link AdapterVehicle}
 * which EXTENDS our given class {@link Vehicle} and implements our given interface {@link Movable}.
 * Then, we simply implement the methods of the interface in {@link AdapterVehicle}, however, in the body
 * of these methods we simply call corresponding methods of {@link Vehicle}.
 * <p>
 * The second way is via composition. So we create a new class {@link AdapterVehicle2} which
 * only implements our interface {@link Movable} and does not extend any class.
 * In oppose, this class actually has a {@link Vehicle} type variable in its state.
 * Therefore, {@link AdapterVehicle2} just creates an instance of the class {@link Vehicle} in its constructor.
 * And also provides methods which simply reach out to the corresponding methods of the actual {@link Vehicle}.
 */
public class AdapterPattern {
    public static void main(String[] args) {

        /**
         * First way to implement Adapter Pattern via inheritance
         */
        Movable vehicle = new AdapterVehicle(25, 225, 150);
        vehicle.startMovement();
        vehicle.move();
        vehicle.endMovement();

        System.out.println("\n************************\n");
        /**
         * Second way to implement Adapter Pattern via composition
         */
        AdapterVehicle2 adapterVehicle = new AdapterVehicle2(40, 300, 200);
        adapterVehicle.startMovement();
        adapterVehicle.move();
        adapterVehicle.endMovement();
    }

}


interface Movable {
    void startMovement();

    void move();

    void endMovement();
}

class Vehicle {
    int averageVelocity;
    int maxVelocity;
    int averageAcceleration;

    public Vehicle(int acceleration, int maxVelocity, int velocity) {
        this.averageAcceleration = acceleration;
        this.averageVelocity = velocity;
        this.maxVelocity = maxVelocity;
    }

    void accelerate() {
        System.out.println("Accelerating at " + averageAcceleration + " m/s^2");
    }

    void move() {
        System.out.println("Moving with velocity " + this.averageVelocity + "km/h");
    }

    void applyBreaks() {
        System.out.println("Applying breaks and decelerating at rate of " + averageAcceleration * 3 + " m/s^2");
    }
}


class AdapterVehicle extends Vehicle implements Movable {

    public AdapterVehicle(int acceleration, int maxVelocity, int velocity) {
        super(acceleration, maxVelocity, velocity);
    }

    @Override
    public void startMovement() {
        accelerate();
    }

    @Override
    public void move() {
        super.move();
    }

    @Override
    public void endMovement() {
        applyBreaks();
    }
}


class AdapterVehicle2 implements Movable {
    Vehicle vehicle;

    public AdapterVehicle2(int acceleration, int maxV, int averageV) {
        vehicle = new Vehicle(acceleration, maxV, averageV);
    }

    @Override
    public void startMovement() {
        vehicle.accelerate();
    }

    @Override
    public void move() {
        vehicle.move();
    }

    @Override
    public void endMovement() {
        vehicle.applyBreaks();
    }
}