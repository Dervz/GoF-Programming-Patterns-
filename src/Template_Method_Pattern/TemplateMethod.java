package Template_Method_Pattern;


/**
 * Suppose we got 2 classes that MOSTLY do the same thing, however, partially different.
 * In order to avoid code duplication(never do that lol), we might use a Template Method Pattern.
 * <p>
 * Suppose we got two classes {@link PeacefulCalculator} and {@link AggressiveCalculator}.
 * They got 90% of the code is the same. However, what we are looking at here is that code of
 * {@link PeacefulCalculator#printInfo()} and {@link AggressiveCalculator#printInfo()} are very similar.
 * Note since these methods are deleted (due to creation of the template method) its highlighted as red -> just ignore it.
 * <p>
 * Initially their texts were:
 * System.out.println("Hello friend! I am peaceful calculator.");
 * System.out.println("I have just calculated the sum of 2 numbers " + this.first + " " + this.second);
 * System.out.println("The result is " + sum);
 * <p>
 * &&
 * <p>
 * System.out.println("HELLO MATE, I AM AGGRESSIVE CALCULATOR");
 * System.out.println("I have just calculated the sum of 2 numbers " + this.first + " " + this.second);
 * System.out.println("The result is " + sum);
 * <p>
 * for the {@link PeacefulCalculator#printInfo()} and {@link AggressiveCalculator#printInfo()} correspondingly.
 * <p>
 * Then, we decide to create another class {@link Calculator}, with a TEMPLATE METHOD
 */
public class TemplateMethod {

    public static void main(String[] args) {
        PeacefulCalculator peacefulCalculator = new PeacefulCalculator(8910, 1093);
        peacefulCalculator.printStuff();

        System.out.println("\n*****************************\n");

        AggressiveCalculator aggressiveCalculator = new AggressiveCalculator(1313213, 776812354);
        aggressiveCalculator.printStuff();
    }
}


class PeacefulCalculator extends Calculator {

    public PeacefulCalculator(int a, int b) {
        super(a, b);
    }

    @Override
    void differenceOfCode() {
        System.out.println("Hello friend! I am peaceful calculator.");
    }
}

class AggressiveCalculator extends Calculator {

    public AggressiveCalculator(int a, int b) {
        super(a, b);
    }

    @Override
    void differenceOfCode() {
        System.out.println("HELLO MATE, I AM AGGRESSIVE CALCULATOR");
    }

}

/**
 * Abstract class that provides its children with default implementation via {@link #printStuff()}
 * TEMPLATE_METHOD which calls out abstract {@link #differenceOfCode()} which has to be overriden
 * by its children in order to set child-specific behaviour.
 */
abstract class Calculator {
    protected int first;
    int second;
    int sum;

    public Calculator(int a, int b) {
        this.first = a;
        this.second = b;
        this.sum = a + b;
    }

    /**
     * TEMPLATE METHOD is here
     * Note that we still force to execute specific to the child code in the {@link #differenceOfCode()} method.
     * Since it is abstract it must be overriden
     */
    protected void printStuff() {
        differenceOfCode();
        System.out.println("I have just calculated the sum of 2 numbers: " + this.first + " and " + this.second);
        System.out.println("The result is " + sum);
    }

    abstract void differenceOfCode();

}