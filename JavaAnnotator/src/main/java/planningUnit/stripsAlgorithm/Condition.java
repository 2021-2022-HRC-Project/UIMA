package planningUnit.stripsAlgorithm;

import planningUnit.utils.enums.ElementType;

/**
 * Single part condition extending the element super class
 */
public class Condition extends Element {

//    private final String[] conditions;

    /**
     * Condition constructor populated with state information and element type being set to Single Part Condition
     *
     * @param stateString   String containing state information of this condition
     */
    public Condition(String stateString) { // ON(C,B)
        super();
//        String[] conditions = stateString.split(" ");

//        if (conditions.length > 1) { // Multi Part
//            this.multiConString = stateString;
//            throw new IllegalStateException("[Condition Initializer] Multi-part string in Single Condition");
//        }
//        else if (conditions.length == 1){
        super.parseStateString(stateString);
        super.setElementType(ElementType.SINGLE_PART_CONDITION);
//        } else {
//            throw new IllegalStateException("[Condition Initializer] ");
//        }
    }


}
