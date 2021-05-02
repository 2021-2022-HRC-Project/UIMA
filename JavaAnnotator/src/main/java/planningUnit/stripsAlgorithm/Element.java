package planningUnit.stripsAlgorithm;

import planningUnit.utils.enums.ElementType;

/**
 * Element is a superclass datastructure for STRIPTS
 * @impl Cloneable   for cloning element and subclasses
 */
public class Element implements Cloneable {
    public ElementType elementType;
    private String[] items;
    public String nameOfActionOrState;  // If the Element records an action, this will be the name of that action
    // Example: "PICK_UP", "STACK", "UNSTACK", "PUT_DOWN"
    // Otherwise, it will record the name of the State Description
    // Example: "HOLDING", "ON_TABLE", "ON", "ARM_EMPTY"

    public Element() {}

    /**
     * Set elementType field
     *
     * @param elementType  Value to be set for elementType field
     */
    public void setElementType(ElementType elementType) {
        this.elementType = elementType;
    }

    /**
     * Return a string version of element data type
     *
     * @return stateDescString   a string containing this element's name and contained items
     */
    @Override
    public String toString() {
        String stateDescString = nameOfActionOrState;

        if (items != null) {
            stateDescString += "(";
            stateDescString += String.join(",", items);
            stateDescString += ")";
        }

        return stateDescString;
    }

    /**
     * Get the name of this element
     */
    public String getNameOfActionOrState() {
        return nameOfActionOrState;
    }

    /**
     * Return the current list of string items
     *
     * @return items  a field of Element storing contained items in String
     */
    public String[] getItems() {
        return items;
    }

    /**
     * Comparing two element based on their name and mached items.
     *
     * @param element  An element to be compared to
     * @return whether this element is the same in all fields values with respect to the parameter element
     */
    @Override
    public boolean equals(Object element){
        if (element.getClass() != this.getClass()) {
            return false;
        }
        return this.toString().equalsIgnoreCase(element.toString());
    }

    /**
     * Convert a string containing element data to Element data type. Store the data in the String.
     *
     * @param name   A String containing element data
     */
    public void parseStateString(String name) {
        // "On(a,b)"
        int openBracket = name.indexOf('(');
        int closeBracket = name.indexOf(')');

        if (openBracket != -1) {
            this.nameOfActionOrState = name.substring(0, openBracket);
            items = name.substring(openBracket + 1, closeBracket).split(",");
        } else {
            this.nameOfActionOrState = name;
        }
    }
}
