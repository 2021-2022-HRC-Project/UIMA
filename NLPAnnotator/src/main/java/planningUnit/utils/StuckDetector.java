package planningUnit.utils;

import planningUnit.stripsAlgorithm.Action;
import planningUnit.stripsAlgorithm.Element;
import planningUnit.utils.enums.ElementType;

import java.util.*;

/**
 * For detecting when the STRIPS algo is stuck using undone actions and cycles
 */
public class StuckDetector {

    private final LinkedList<String[]> goalStackLog = new LinkedList<>(); // a log of the goal stack
    private static Map<String, String> undoActionMap = new HashMap<String, String>() {{ // dictionary for potential repetitive actions
        put("STACK", "UNSTACK");
        put("UNSTACK", "STACK");
        put("PICK_UP", "PUT_DOWN");
        put("PUT_DOWN", "PICK_UP");
    }};

    /**
     * Constructor
     */
    public StuckDetector(){
    }

    /**
     * Put the inputted stack of elements to the stack log
     * 
     * @param goalStack     a stack of element of the goal stack
     */
    public void logGoalStack(Stack<Element> goalStack){
        goalStackLog.add(getGoalStackStringArr(goalStack));
    }

    /**
     * Convert a stack of element to an array of strings for better output reading
     * 
     * @param goalStack             a stack of element of the goal stack
     * @return goalStackStringArr   an array of string of the inputted goal stack
     */
    public static String[] getGoalStackStringArr(Stack<Element> goalStack){
        String[] goalStackStringArr = new String[goalStack.size()];
        for (int i = 0; i < goalStack.size(); i++){
            Element element = goalStack.elementAt(i);
            if (element.elementType == ElementType.ACTION){
                goalStackStringArr[i] = ((Action) element).getOutputString() + "\n";
            } else {
                goalStackStringArr[i] = element.toString() + "\n";
            }
        }
        return reverseArray(goalStackStringArr);
    }

    /**
     * Reverse the order of inputted array string
     * 
     * @param goalStackStringArr     an array of strings of the goal stack
     * @return a reversed goalStackStringArr
     */
    public static String[] reverseArray(String[] goalStackStringArr) {
        List<String> goalStackStringLst = Arrays.asList(goalStackStringArr);
        Collections.reverse(goalStackStringLst);
        return goalStackStringLst.toArray(goalStackStringArr);
    }

    /**
     * Check whether there are pushing of repetitive actions to the goal stack for detecting whether the algo is stuck
     * 
     * @param currentPlan   a list of actions showing the current action plan
     * @return true if there are repetitions, false otherwise. 
     */
    public boolean detectCycle(List<Action> currentPlan) {
        for (int i = 0; i < currentPlan.size() - 1; i++){
            Action currAction = currentPlan.get(i);
            String currActionName = currAction.getName();
            Action nextAction = currentPlan.get(i + 1);
            String nextActionName = nextAction.getName();
            if (Arrays.equals(currAction.getItems(), nextAction.getItems())){
                return nextActionName.equals(undoActionMap.get(currActionName));
            }
        }

        return false;
    }
}
