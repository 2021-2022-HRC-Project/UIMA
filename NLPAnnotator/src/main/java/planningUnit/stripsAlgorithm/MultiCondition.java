package planningUnit.stripsAlgorithm;

import planningUnit.utils.enums.ElementType;

import java.util.ArrayList;

/**
 * MultiCondition is a an Element data class for multi-part states
 */
public class MultiCondition extends Element {

    private final ArrayList<Condition> condList;
    private final String[] conditions;

    /**
     * MultiCondition constructor, extends and update required Element super class fields
     *
     * @param stateString  String describing the multi-part state
     */
    public MultiCondition(String stateString) { // ON(C,B)
        super();
        condList = new ArrayList<>();
        conditions = stateString.split(" ");
        super.setElementType(ElementType.MULTI_PART_CONDITION);

        for (String condition : conditions) {
            condList.add(new Condition(condition));
        }
    }

    /**
     * This method returns the list of conditions stored within data class
     *
     * @return condList    an arraylist of conditions of this multi-part condition
     */
    public ArrayList<Condition> getConditions() {
        return condList;
    }

    /**
     * This method returns a string of stored conditions for printing purposes
     *
     * @return string      a string of conditions
     */
    @Override
    public String toString() {
        return String.join(" ", conditions);
    }
}

