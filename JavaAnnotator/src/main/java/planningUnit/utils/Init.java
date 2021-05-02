package planningUnit.utils;

import planningUnit.stripsAlgorithm.Action;
import planningUnit.stripsAlgorithm.Condition;
import planningUnit.stripsAlgorithm.Element;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Stack;


/**
 * Initializing the available actions. Provide functions for initializing the goal stack with conditions.
 */
public class Init {

    /**
     * Return all available actions within this STRIPS world
     *
     * @return allActions   A linkedlist of actions contaning all actions with their preconditions and postconditions
     */
    public static LinkedList<Action> initializeAllActions() {
        LinkedList<Action> allActions = new LinkedList<>();

        Action stack = new Action("STACK(top,bottom)", "CLEAR(bottom) HOLDING(top)", "ARM_EMPTY ON(top,bottom) CLEAR(top)");
        allActions.add(stack);

        Action unstack = new Action("UNSTACK(top,bottom)", "ON(top,bottom) CLEAR(top) ARM_EMPTY", "clear(bottom) HOLDING(top)");
        allActions.add(unstack);

        Action pickUp = new Action("PICK_UP(item)", "CLEAR(item) ON_TABLE(item) ARM_EMPTY", "HOLDING(item)");
        allActions.add(pickUp);

        Action putDown = new Action("PUT_DOWN(item)", "HOLDING(item)", "ON_TABLE(item) CLEAR(item) ARM_EMPTY");
        allActions.add(putDown);

        return allActions;
    }

    /**
     * Return a stack containing all elements inputted into the function
     *
     * @param goalStackArr      An array of String containing information about the goal condition
     * @return elementStack     A stack of element with all inputted goal strings converted to conditions
     */
    public static Stack<Element> initializeGoalStackWith(String[] goalStackArr) {
        //put multi part goal on stack
        Stack<Element> elementStack = new Stack<>();

        //put single part goals on stack
        for (String s : goalStackArr) {
            elementStack.add(new Condition(s));
        }
        return elementStack;
    }

    /**
     * Return a hashset of states containing all elements inputted into the function
     *
     * @param conditionStrArr   An array of String containing information about states
     * @return stateHashSet     A hashset containing all conditions of inputted state with String converted to Condition data class
     */
    public static HashSet<Condition> initializeStateWith(String[] conditionStrArr) {
        HashSet<Condition> stateHashSet = new HashSet<>();
        for (String conditionStr : conditionStrArr) {
            stateHashSet.add(new Condition(conditionStr));
        }
        return stateHashSet;
    }
}
