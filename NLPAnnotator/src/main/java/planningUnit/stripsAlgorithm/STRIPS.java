package planningUnit.stripsAlgorithm;

import planningUnit.utils.*;
import planningUnit.utils.enums.ElementType;
import planningUnit.utils.exceptions.*;

import java.util.*;
import java.util.concurrent.TimeoutException;

public class STRIPS {

    private final Stack<Element> goalStack;  // The Goal Stack
    private final HashSet<Condition> currentState; // The current state we have
    private HashSet<Condition> goalState; // The goal state that we want to achieve
    private final List<Action> allActions; // The List that stored all the possible Actions
    private final List<Action> plan; // The action plan in Action type
    private final boolean printGoalStack; // This is an indicator on whether debugging information about goal stack needs to be printed, true for printing the goal stack, otherwise false
    private int whileLoopCount = 0; // This is the counter for while loop in step(). This is used for debugging purposes
    private final StuckDetector stuckDetector; // This is the stuck detector that helps us determine whether STRIPS planner is stuck.
    private final long timeThreshold; // Time threshold set for terminating STRIPS planner

    /**
     * This is where the planning works get done
     *
     * @param initStatesArr  this is the description of the initial state in String Array
     * @param goalStateArr   this is the description of the goal state in String Array
     * @param printGoalStack This is an indicator on whether debugging information about goal stack needs to be printed, true for printing the goal stack, otherwise false
     * @param timeThreshold Time threshold set for terminating STRIPS planner
     * @throws CompleteAtStartException If the initial state matches the goal state at the beginning, this exception will be thrown
     */
    public STRIPS(String[] initStatesArr, String[] goalStateArr, Boolean printGoalStack, long timeThreshold) throws CompleteAtStartException {

        if (Arrays.equals(initStatesArr, goalStateArr)) { // When the starting conditions is equal to the goal conditions
            throw new CompleteAtStartException();
        }

        this.plan = new LinkedList<>();
        this.goalState = new HashSet<>();

        this.allActions = Init.initializeAllActions();
        this.currentState = Init.initializeStateWith(initStatesArr);
        this.goalState = Init.initializeStateWith(goalStateArr);
        this.goalStack = Init.initializeGoalStackWith(goalStateArr);
        this.printGoalStack = printGoalStack;
        stuckDetector = new StuckDetector();
        this.timeThreshold = timeThreshold;
    }

    /**
     * This method will help us get the List of Actions in sequence to achieve the goal state
     *
     * @return the plan in List of Actions in sequence to achieve the goal state
     * @throws UnsolvableException If the problem cannot be solved by the planner, we will throw this UnsolvableException
     * @throws StuckException      If the planner got stuck, we will throw this StuckException
     * @throws TimeoutException    If the planner runs over the time threshold, throw TimeoutException
     */
    public List<Action> getPlan() throws UnsolvableException, StuckException, TimeoutException {
        if (printGoalStack) {
            System.out.println();
            System.out.println("------------------------------");
            System.out.println("Debug Information - Goal Stack");
            System.out.println("------------------------------");
        }
        long startTime = System.currentTimeMillis();
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;

        while (!goalStack.empty() && elapsedTime <= timeThreshold) {
            stuckDetector.logGoalStack(goalStack);
            if (stuckDetector.detectCycle(this.plan)) {
                throw new StuckException();
            }
            step();
            endTime = System.currentTimeMillis();
            elapsedTime = endTime - startTime;
        }

        if (elapsedTime > timeThreshold){
            throw new TimeoutException();
        }

        if (printGoalStack) {
            System.out.println("-------------------------------------");
            System.out.println("End of Debug Information - Goal Stack");
            System.out.println("-------------------------------------");
            System.out.println();
        }
        return plan;
    }

    /**
     * This method will plan the next goal in the goal stack
     *
     * @throws UnsolvableException If the problem cannot be solved by the planner, we will throw this UnsolvableException
     */
    public void step() throws UnsolvableException {
        Element nextElement = goalStack.peek();
        if (nextElement.elementType == ElementType.ACTION) {
            plan.add((Action) nextElement);
            doAction(nextElement);
        } else if (isConditionFullyAchieved(nextElement)) { // It is either a single part goal or a multi part goal
            goalStack.pop();
        } else if (nextElement.elementType == ElementType.MULTI_PART_CONDITION) {
            // Multi-con
            planMultiPartCondition((MultiCondition) nextElement);
        } else if (nextElement.elementType == ElementType.SINGLE_PART_CONDITION) { // The goal is single part goal
            planSinglePartCondition((Condition) nextElement);
        } else {
            throw new IllegalStateException("[STRIPS.attemptNextGoal] The goal is not an Action, not a SinglePartGoal, also not a MultiPartGoal");
        }

        if (printGoalStack) {
            System.out.printf("While Loop Count: %d%n", whileLoopCount);
            System.out.println(Arrays.asList(StuckDetector.getGoalStackStringArr(goalStack)));
            System.out.println("-----Plan-------");
            for (Action action : this.plan){
                System.out.println(action.getOutputString());
            }
            System.out.println("-----END Plan-------");
            whileLoopCount++;
        }
    }

    /**
     * This method will apply the action and update the current state using that item's preconditions and results
     *
     * @param actionFromStack this is an Action comes from the goal stack
     */
    private void doAction(Element actionFromStack) {
        // This method will convert the plan using Action type to plan using String type
        for (Action action : plan) {
            if ((actionFromStack.toString().equalsIgnoreCase(action.toString()))) {
                // Remove the action from the Goal Stack
                goalStack.pop();
                // Perform the action and update current state
                updateCurrentState(action);
                break;
            }
        }
    }

    /**
     * This method will update the currentState after an action is performed
     *
     * @param newAction this is the action we will apply to update the current state with its preconditions and results
     */
    private void updateCurrentState(Action newAction) {
        for (int i = 0; i < newAction.preconditions.size(); i++) {
            for (Condition condition : this.currentState) {
                if (newAction.preconditions.get(i).equals(condition)) {
                    this.currentState.remove(condition);
                    break;
                }
            }
        }

        // add from results/(post-conditions) of action in currentState
        for (int i = 0; i < newAction.results.size(); i++) {
            this.currentState.add(new Condition(newAction.results.get(i).toString()));
        }
    }

    /**
     * This method will help us replace block names in action itself, its preconditions, and its results
     *
     * @param action      the action that the items need to be replaced in
     * @param target      the target item to be replaced by the replacement
     * @param replacement the replacement for the target item
     */
    private void replaceBlockNames(Action action, String target, String replacement) {
        // replace all occurrence in action target with replacement in:
        // Action Preconditions
        replaceBlockNamesIn(action.preconditions, target, replacement);

        // Action Results
        replaceBlockNamesIn(action.results, target, replacement);

        // Action items
        String[] actionItems = action.getItems();
        for (int i = 0; actionItems != null && i < actionItems.length; i++) {
            if (actionItems[i].equalsIgnoreCase(target)) {
                actionItems[i] = replacement;
                break;
            }
        }
    }

    /**
     * This method will help us replace the block names in the list of either preconditions or results of an action
     *
     * @param conditions  list of preconditions or results of an action
     * @param target      the target item to be replaced by the replacement
     * @param replacement the replacement for the target item
     */
    private void replaceBlockNamesIn(ArrayList<Condition> conditions, String target, String replacement) {
        for (Condition condition : conditions) {
            String[] conditionItems = condition.getItems();
            for (int i = 0; conditionItems != null && i < conditionItems.length; i++) {
                if (conditionItems[i].equalsIgnoreCase(target)) {
                    conditionItems[i] = replacement;
                    break;
                }
            }
        }
    }

    /**
     * This method will help us plan the Condition with multiple parts
     *
     * @param condition this is the element of goal stack that describes a multi part goal that we want to plan
     */
    private void planMultiPartCondition(MultiCondition condition) {
        for (Condition goalPartNeedToDo : condition.getConditions()) {
            // If not all goals are achieved, we will store all parts of goals on Goal Stack
            // Each part of the goal is a single part goal
            goalStack.push(goalPartNeedToDo);
        }
    }

    /**
     * This method will help us plan the Single Part Condition / Single part handler
     *
     * @param condition this is an condition that we want to solve by finding best action for it
     * @throws UnsolvableException This exception will be thrown if we are not able to find a best action
     */
    private void planSinglePartCondition(Condition condition) throws UnsolvableException {

        // If we did not achieved the sub goal, we will find the best actions to achieve it.
        // After that, we will store it in the stack
        Action clone = findBestAction(condition);
        pushPreconditionsToGoalStack(clone);
    }

    /**
     * This method can help us check whether a goal has been achieved under current state
     *
     * @param element this is the condition we want to check about whether it has been achieved
     * @return it will return a Boolean value that is true if the input is achieved, otherwise false.
     */
    private boolean isConditionFullyAchieved(Element element) {
        if (element.elementType == ElementType.MULTI_PART_CONDITION) {
            ArrayList<Condition> conditions = ((MultiCondition) element).getConditions();
            for (Condition condition : conditions){ // For all goal states
                boolean thisGoalSatisfied = false;
                for (Condition state : currentState){ // check if current states contain the goal state
                    if (condition.equals(state)){
                        thisGoalSatisfied = true;
                        break;
                    }
                }
                if (!thisGoalSatisfied) { // If a goal is not satisfies, return false
                    return false;
                }
            }
            return true;
        } else if (element.elementType == ElementType.SINGLE_PART_CONDITION) {
            boolean conditionAchieved = false;

            for (Condition currentStateCondition : currentState) {
                if (currentStateCondition.toString().equalsIgnoreCase(element.toString())) {
                    // If we achieved our sub goal, we will remove it from the stack.
                    conditionAchieved = true;
                    break;
                }
            }
            return conditionAchieved;
        } else {
            throw new IllegalStateException("[STRIPS.isGoalFullyAchieved] The condition is nether a SinglePartCondition, nor a MultiPartCondition");
        }
    }

    /**
     * This method will push the preconditions of the given action to goal stack
     *
     * @param action this is the action that we want to store in the goal stack
     */
    private void pushPreconditionsToGoalStack(Action action) {
        this.goalStack.pop();

        // Store the Action in the GoalStack
        this.goalStack.push(action);

        // Store the multi part goals in the GoalStack
        this.goalStack.push(new MultiCondition(action.getPreconditionString()));
    }

    /**
     * This is the Heuristic method that help us get the best action for the given state that we want to achieve
     *
     * @param condition this is the condition that we want to find action for
     * @return an Action will be returned to achieve the given condition. If we cannot achieve the given condition, it will return null
     * @throws UnsolvableException This exception will be thrown if we are not able to find the best action
     */
    private Action findBestAction(Condition condition) throws UnsolvableException {
        // Heuristic

        Action currentAction = null;

        String[] items = new String[2];

        if (condition.getNameOfActionOrState().equalsIgnoreCase("HOLDING")) {
            // If we want to HOLDING an item, there are two actions we can take: UNSTACK or PICK_UP
            for (Condition currentStateCondition : currentState) {
                if (currentStateCondition.nameOfActionOrState.equalsIgnoreCase("ON")
                        && currentStateCondition.getItems()[0].equalsIgnoreCase(condition.getItems()[0])) {
                    currentAction = getUnstackAction(currentStateCondition, items);
                }
            }
            if (currentAction == null) {
                currentAction = getAction("PICK_UP");

                items[0] = condition.getItems()[0];
            }
        } else if (condition.getNameOfActionOrState().equalsIgnoreCase("ARM_EMPTY")) {
            // If we want to get our ARM_EMPTY, there are two actions we can take: STACK or PUT_DOWN
            // We will use the cloneActionHelper to determine which one is the best
            currentAction = findBestActionHelper(items);
        } else if (condition.getNameOfActionOrState().equalsIgnoreCase("CLEAR")) {
            // If we want to get an item CLEAR, there are three actions we can take: STACK, UNSTACK, or PUT_DOWN
            boolean isHoldingItem = false;
            for (Condition currentStateCondition : currentState) {
                if (currentStateCondition.nameOfActionOrState.equalsIgnoreCase("HOLDING")
                        && currentStateCondition.getItems()[0].equalsIgnoreCase(condition.getItems()[0])) {
                    isHoldingItem = true;
                    break;
                }
            }

            if (isHoldingItem) {
                // If we are HOLDING an item now, we need to put it down some way
                // We will use the cloneActionHelper to determine which way is the best
                currentAction = findBestActionHelper(items);
            } else {
                // If we are not HOLDING any item. Alternatively, we have our ARM_EMPTY
                for (Condition currentStateCondition : currentState) {
                    if (currentStateCondition.nameOfActionOrState.equalsIgnoreCase("ON")
                            && currentStateCondition.getItems()[1].equalsIgnoreCase(condition.getItems()[0])) {
                        currentAction = getUnstackAction(currentStateCondition, items);
                        break;
                    }
                }
            }
        } else {
            // If we are not working on StateDescriptions, we will do an Action
            for (Action action : allActions) {
                if (action.isGoalAchieved(condition.getNameOfActionOrState())) {
                    currentAction = getAction(action.getName());

                    System.arraycopy(condition.getItems(), 0, items, 0, condition.getItems().length);
                    break;
                }
            }
        }

        if (currentAction == null) {
            throw new UnsolvableException();
        }

        // replace block names using clones so that we can add it to the planWithActions list
        for (int j = 0; j < currentAction.getItems().length; j++) {
            replaceBlockNames(currentAction, currentAction.getItems()[j], items[j]);
        }

        return currentAction;
    }

    /**
     * This is the method that will return the UNSTACK action with proper items
     * @param condition The current condition when the UNSTACK operation needs to be applied
     * @param items an String Array that contains all the items the UNSTACK action will apply on
     * @return the UNSTACK operation
     */
    private Action getUnstackAction(Condition condition, String[] items) {
        Action currentAction = getAction("UNSTACK");

        items[0] = condition.getItems()[0];
        items[1] = condition.getItems()[1];
        return currentAction;
    }

    /**
     * This method will deal with the case when we need to achieve ARM_EMPTY or HOLDING goal
     *
     * @param items this is a string array that contains all the element that the action will apply on
     * @return an Action object will be returned to achieve the condition "HOLDING" or "ARM_EMPTY"
     */
    private Action findBestActionHelper(String[] items) {
        Action action = null;
        for (Condition condition : goalState) {
            for (Condition value : currentState) {
                if (condition.nameOfActionOrState.equalsIgnoreCase("ON")
                        && value.nameOfActionOrState.equalsIgnoreCase("HOLDING")
                        && condition.getItems()[0].equalsIgnoreCase(value.getItems()[0])) {
                    action = getAction("STACK");

                    items[0] = condition.getItems()[0];
                    items[1] = condition.getItems()[1];
                }
            }
        }
        if (action == null) {
            action = getAction("PUT_DOWN");

            for (Condition condition : currentState) {
                if (condition.nameOfActionOrState.equalsIgnoreCase("HOLDING")) {
                    items[0] = condition.getItems()[0];
                    break;
                }
            }
        }
        return action;
    }

    /**
     * Get a clone of the action from the allAction list
     *
     * @param actionName the name of the action we want
     * @return a clone of the action with given action name from the allAction list
     */
    private Action getAction(String actionName) {
        for (Action action : allActions) {
            if (action.nameOfActionOrState.equalsIgnoreCase(actionName)) {
                return action.clone();
            }
        }
        throw new IllegalStateException("[STRIPS.getAction] No Such Action named: <" + actionName + ">");
    }
}

