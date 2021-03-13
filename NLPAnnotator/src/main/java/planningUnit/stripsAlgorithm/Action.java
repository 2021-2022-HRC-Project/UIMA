package planningUnit.stripsAlgorithm;

import planningUnit.utils.enums.ElementType;

import java.util.ArrayList;
import java.util.List;

/**
 * Action data sturcture for STRIPS algorithm, extending Element super class
 */
public class Action extends Element {

    public final ArrayList<Condition> preconditions = new ArrayList<>(); // Preconditions necessary for an action to happen
    public final ArrayList<Condition> results = new ArrayList<>(); // Postconditions after an action has been done

    /**
     * Action constructor, constructing a new action based on given name, its preconditions, and its postconditions. Extend Element's fields
     *
     * @param name              the name of this action
     * @param preconditions     preconditions necessary for an action to happen
     * @param results           postconditions after an action has been done
     */
    public Action(String name, String preconditions, String results) {
        super();
        parseStateString(name);
        setElementType(ElementType.ACTION);

        for (String precondition : preconditions.split(" ")){
            this.preconditions.add(new Condition(precondition));
        }

        for (String result: results.split(" ")) {
            this.results.add(new Condition(result));
        }
    }

    /**
     * Get the name of this action
     *
     * @return nameOfActionOrState  the name field of Element
     */
    public String getName(){
        return nameOfActionOrState;
    }

    /**
     * Return a String containing all preconditions of this action
     *
     * @return a String containing all the preconditions joined together separated with space
     */
    public String getPreconditionString() {
        // Store the Preconditions for an action in the GoalStack
        List<String> preconditionList = new ArrayList<>();
        for (Condition precondition : preconditions) {
            preconditionList.add(precondition.toString());
        }
        return String.join(" ", preconditionList);
    }

    /**
     * Check whether the goal is achieved based on whether the postconditions of this action meets with the goal
     *
     * @param goal              a String describing the goal to be met
     * @return goalAchieved     true if one of postconditions satisfies the goal, false otherwise
     */
    public boolean isGoalAchieved(String goal) {
        boolean goalAchieved = false;

        for (Condition result : results) {
            if (result.nameOfActionOrState.equalsIgnoreCase(goal)) {
                goalAchieved = true;
                break;
            }
        }

        return goalAchieved;
    }

    /**
     * Deep copying this action element and return a new action
     *
     * @return new Action with every fields hold the same value as this action
     */
    @Override
    public Action clone() {
        try {
            super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        ArrayList<String> preconditionsStringList = new ArrayList<>();
        for (Condition precondition : this.preconditions){
            preconditionsStringList.add(precondition.toString());
        }

        ArrayList<String> resultsStringList = new ArrayList<>();
        for (Condition result : this.results){
            resultsStringList.add(result.toString());
        }

        return new Action(
                super.toString(),
                String.join(" ", preconditionsStringList),
                String.join(" ", resultsStringList)
        );
    }

    /**
     * Return this action in a string format. For printing purposes
     *
     * @return calling Element's toString methods.
     */
    public String getOutputString(){
        return super.toString();
    }

    /**
     * Return this action in a string format with preconditions and postconditions tagged properly. For printing purposes
     *
     * @return a String containing the name, preconditions, and postconditions of this action
     */
    @Override
    public String toString() {
        return ("action: " + super.toString()
                + "\n  preconditions: " + preconditions
                + "\n  results:          " + results);
    }
}

