package Abstract_Factory_Pattern;

public class AbstractFactory {

    public static void main(String[] args) {
        /**
         * The main purpose of the abstract factory is covered in the lines below.
         * DeviceFactory is initialised depending on the countryCode passed to the method getFactoryByCountryCode(String countryCode).
         * Then, created DEVICE_FACTORY creates all of the corresponding PRODUCTS.
         *
         * Now, note that in order to change TYPE OF THE PRODUCT,
         * the only thing developer has to do is to supply new countryCode (or create new obj)
         */
        DeviceFactory devFactory = getFactoryByCountryCode("ENG");
        Mouse mouse = devFactory.getMouse();
        Keyboard keyboard = devFactory.getKeyboard();
        TouchPad touchPad = devFactory.getTouchPad();

        mouse.click();
        mouse.doubleClick();
        mouse.scroll(1);

        keyboard.sendInput();

        touchPad.track(3, 5);
    }

    private static DeviceFactory getFactoryByCountryCode(String countryCode) {
        switch (countryCode) {
            case "RUS":
                return new RussianDeviceFactory();
            case "ENG":
                return new EnglishDeviceFactory();
            default:
                throw new RuntimeException("Unknown country code : " + countryCode);
        }
    }
}

/**
 * Three abstractions of the PRODUCTS are created.
 * Note that ALL of these products can be of DIFFERENT TYPE.
 * It is said that products belong to either of the existing families.
 *
 * However, they still have the same behaviour (outlined by these abstractions)
 */
interface Mouse {
    void click();

    void doubleClick();

    void scroll(int direction);
}

interface TouchPad {
    void track(int xPos, int yPos);
}

interface Keyboard {
    void takeInput();

    void sendInput();

}

/**
 * Abstract Factory
 * The difference between abstract factory and factory method is that
 * abstract factory generally implements SEVERAL factory methods.
 *
 * Notice that in my example of Factory Method we generate either
 * 216EncryptionAlgorithm or 512EncryptionAlgorithm depending on the FACTORY_METHOD called.
 *
 * Implementations of these abstractions are said to be FACTORY METHODS
 */
interface DeviceFactory {
    Mouse getMouse();

    TouchPad getTouchPad();

    Keyboard getKeyboard();
}

/**
 * Russian implementation of first product
 */
class RussianMouse implements Mouse {

    @Override
    public void click() {
        System.out.println("Клик");
    }

    @Override
    public void doubleClick() {
        System.out.println("Клик Клик");
    }

    @Override
    public void scroll(int direction) {
        if (direction > 0) System.out.println("Скролл вверх!");
        else System.out.println("Скролл вниз");
    }
}

/**
 * Russian implementation of the second product
 */
class RussianKeyboard implements Keyboard {

    @Override
    public void takeInput() {
        System.out.println("Получаем ввод");
    }

    @Override
    public void sendInput() {
        System.out.println("Отправляем ввод");
    }
}

/**
 * Russian implementation of third product
 */
class RussianTouchPad implements TouchPad {

    @Override
    public void track(int xPos, int yPos) {
        System.out.println("Едем вертикально на " + yPos + " и горизонтально на " + xPos);
    }
}

/**
 * English implementation of first product
 */
class EnglishMouse implements Mouse {

    @Override
    public void click() {
        System.out.println("Click");
    }

    @Override
    public void doubleClick() {
        System.out.println("Click Click");
    }

    @Override
    public void scroll(int direction) {
        if (direction > 0) System.out.println("Scroll up!");
        else System.out.println("Scroll down");
    }
}

/**
 * English implementation of second product
 */
class EnglishKeyboard implements Keyboard {

    @Override
    public void takeInput() {
        System.out.println("Taking input");
    }

    @Override
    public void sendInput() {
        System.out.println("Sending input");
    }
}
/**
 * English implementation of third product
 */
class EnglishTouchPad implements TouchPad {

    @Override
    public void track(int xPos, int yPos) {
        System.out.println("Moving vertically by " + yPos + " and horizontally by " + xPos);
    }
}

/**
 * FACTORY METHODS that return corresponding products
 * RUSSIAN FAMILY in this case
 */
class RussianDeviceFactory implements DeviceFactory {

    @Override
    public Mouse getMouse() {
        return new RussianMouse();
    }

    @Override
    public TouchPad getTouchPad() {
        return new RussianTouchPad();
    }

    @Override
    public Keyboard getKeyboard() {
        return new RussianKeyboard();
    }
}

/**
 * FACTORY METHODS that return corresponding products
 * ENGLISH FAMILY in this case
 */
class EnglishDeviceFactory implements DeviceFactory {

    @Override
    public Mouse getMouse() {
        return new EnglishMouse();
    }

    @Override
    public TouchPad getTouchPad() {
        return new EnglishTouchPad();
    }

    @Override
    public Keyboard getKeyboard() {
        return new EnglishKeyboard();
    }
}