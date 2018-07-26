package Iterator_Pattern;

import java.util.LinkedList;

class IteratorPattern{
    public static void main(String[] args){
        ConcreteAggregate collection = new ConcreteAggregate();
        Iterator collectionIterator = collection.getIterator();

        while(collectionIterator.hasNext()){
            System.out.println(collectionIterator.next());
        }
    }
}

/**
 * We got Iterator interface that states the general principles of working with our
 * collection and its elements. It can have several different methods, however,
 * it must be able to take first, next or current element.
 * <p>
 * Concrete Aggregate is just a Concrete Collection.
 * <p>
 * Concrete Iterator is implemented as a private inner class of the Concrete Collection
 */
interface Iterator {
    boolean hasNext();

    Object next();
}


/**
 * Interface Aggregate simply tells classes that implement it to implement its method
 * {@link #getIterator()}. This is a compulsory requirement for our Aggregate (Collection)
 */
interface Aggregate {
    Iterator getIterator();
}

/**
 * Our main class. All other classes and interfaces are built around this class.
 * It contains a collection and a bunch of methods, one of which MUST be
 * something similar to {@link ConcreteAggregate#getIterator()}.
 *
 * Also, Iterator itself is being generated in the inner class {@link TaskIterator}.
 */
class ConcreteAggregate implements Aggregate {
    LinkedList<String> toDoList = new LinkedList<>();

    public ConcreteAggregate() {
        this.toDoList.add("Quick ride to a grocery store");
        this.toDoList.add("Skiing in Alps");
        this.toDoList.add("Drink a beer");
        this.toDoList.add("Go to bed");
    }


    @Override
    public Iterator getIterator() {
        return new TaskIterator();
    }

    /**
     * NOTE: This is a private class placed inside of our Concrete Aggregate.
     * This is done solely because {@link TaskIterator} is not going to be used by anything else.
     * Rather it is designed specifically for the {@link ConcreteAggregate} class.
     */
    private class TaskIterator implements Iterator {
        int index = 0;

        @Override
        public boolean hasNext() {
            /**
             * If index is less than the total size of the list, return true
             * Else returns false
             */
            return (index < toDoList.size());
        }

        @Override
        public Object next() {
            return toDoList.get(index++);
        }
    }
}


