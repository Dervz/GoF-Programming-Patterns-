package Prototype_Pattern;

public class Prototype {


    public static void main(String[] args) {

        /**
         * Instantiates new Agentsmith smith1296.
         * Clones that agent.
         * Prints both of them.
         * Factory method is used to illustrate patterns synergy.
         */
        AgentSmith smith1296 = new AgentSmith(1296, "Ivan");
        AgentSmith copyOf1296 = smith1296.clone();

        System.out.println(smith1296);
        System.out.println(copyOf1296);

        copyOf1296.ID = 919191;

        AgentSmithFactory factoryOfAgents = new AgentSmithFactory(copyOf1296);
        AgentSmith copyOfCopyOfSmith1296 = factoryOfAgents.copyAgent();

        System.out.println(copyOfCopyOfSmith1296);

    }

}

/**
 * The key feature that describes the prototype class is that it implements Cloneable.
 * Since cloneable is a marker interface, i.e has no methods, it just allows us to override Object's method clone().
 * If we do so, then upon creation of one instance of the prototype we can copy it several times.
 * Note, that there are light and deep clones, so please make sure you implement cloning properly to suit your needs.
 * https://stackoverflow.com/questions/2156120/java-recommended-solution-for-deep-cloning-copying-an-instance
 */
class AgentSmith implements Cloneable {

    int ID;
    String fakeName;

    public AgentSmith(int id, String name) {
        this.ID = id;
        this.fakeName = name;
    }

    @Override
    protected AgentSmith clone() {
        return new AgentSmith(this.ID, this.fakeName);
    }

    @Override
    public String toString() {
        return "Agent Smith " + this.ID + " and fake name " + this.fakeName + " is brought to life!";
    }

}

/**
 * just a factory method to generate new agent smiths.
 * Not relevant to the prototype pattern, made just for simplifications ^^
 */
class AgentSmithFactory {
    AgentSmith agentSmith;

    public AgentSmithFactory(AgentSmith agentSmith) {
        this.agentSmith = agentSmith;
    }

    AgentSmith copyAgent() {
        return this.agentSmith.clone();
    }
}