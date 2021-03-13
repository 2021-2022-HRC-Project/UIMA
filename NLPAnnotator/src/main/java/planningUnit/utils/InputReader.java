package planningUnit.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Provide methods for initializing the STRIPS environment with situations, initial states, and goal states.
 */
public class InputReader {

    /**
     * Return a list of available situations in this STRIPS world
     *
     * @return an ArrayList of strings of the name of available situations
     */
    private static List<String> readNames() {
        return new ArrayList<>(Arrays.asList(
                "Easy Condition",
                "Do Nothing",
                "Not Possible",
                "Paper Example",
                "Sussman Anomaly",
                "New Condition"
        ));
    }

    /**
     * Return a list of initial states of every situation
     *
     * @return an ArrayList of arrays of Strings of condition.
     */
    private static List<String[]> readInitStates() {
        return new ArrayList<>(Arrays.asList(
                new String[]{"ON_TABLE(a)", "ON_TABLE(b)", "CLEAR(a)", "CLEAR(b)", "ARM_EMPTY"}, // easyInitialState
                new String[]{"ON_TABLE(a)", "CLEAR(b)", "ON(b,a)", "ARM_EMPTY"}, // doNothingInitialState
                new String[]{"ON_TABLE(a)", "ON_TABLE(b)", "CLEAR(a)", "ARM_EMPTY"}, // notPossibleInitialState
                new String[]{"ON_TABLE(a)", "ON_TABLE(b)", "ON(c,a)", "CLEAR(b)", "CLEAR(c)", "ARM_EMPTY"}, // paperExampleInitialState
                new String[]{"ON_TABLE(a)", "ON_TABLE(b)", "ON(c,a)", "CLEAR(b)", "CLEAR(c)", "ARM_EMPTY"}, // sussmanAnomalyInitialState
                new String[]{"ON_TABLE(a)", "ON_TABLE(b)", "ON_TABLE(c)", "ON(e,c)", "ON(d,b)", "ON(f,a)", "CLEAR(e)", "CLEAR(f)", "CLEAR(d)", "ARM_EMPTY"}
        ));
    }

    /**
     * Return a list of goal states of every situation
     *
     * @return an ArrayList of arrays of Strings of condition for the destination of each problem
     */
    private static List<String[]> readGoalStates() {

        return new ArrayList<>(Arrays.asList(
                new String[]{"ON_TABLE(a)", "CLEAR(b)", "ON(b,a)", "ARM_EMPTY"}, // easyGoalState
                new String[]{"ON_TABLE(a)", "CLEAR(b)", "ON(b,a)", "ARM_EMPTY"}, // doNothingGoalState
                new String[]{"ON_TABLE(a)", "CLEAR(b)", "ON(b,a)", "ARM_EMPTY"}, // notPossibleGoalState
                new String[]{"ON_TABLE(b)", "CLEAR(a)", "ON(c,b)", "ON(a,c)", "ARM_EMPTY"}, // paperExampleGoalState
                new String[]{"ON_TABLE(c)", "CLEAR(a)", "ON(b,c)", "ON(a,b)", "ARM_EMPTY"}, // sussmanAnomalyGoalState
                new String[]{"CLEAR(b)", "ON(b,d)", "ON(d,c)", "ON(c,e)", "ON(e,f)", "ON(f,a)", "ON_TABLE(a)", "ARM_EMPTY"}
        ));
    }

    /**
     * For adding a new situation to the STRIPS world.
     *
     * @param inputFormat   the name of the new situation
     * @return namesList    created new list of strings of name of the situations of the STRIPS world
     */
    public static List<String> readNames(String inputFormat) {
        List<String> namesList = new ArrayList<>();
        switch (inputFormat) {
            case "":
            case "d":
            case "default":
                return readNames();
            case "C":
            case "c":
                throw new UnsupportedOperationException();
        }
        return namesList;
    }

    /**
     * For adding new initial states for a new problem/situation
     *
     * @param inputFormat           a string containing information about the initial states
     * @return initialStatesList    created new initial state lists
     */
    public static List<String[]> readInitStates(String inputFormat) {
        List<String[]> initialStatesList = new ArrayList<>();
        switch (inputFormat) {
            case "":
            case "d":
            case "default":
                return readInitStates();
            case "C":
            case "c":
                throw new UnsupportedOperationException();
        }
        return initialStatesList;
    }

    /**
     * For adding new goal states for a new problem/situation
     *
     * @param inputFormat           a string containing information about the goal states
     * @return goalStatesList       created new goal state lists
     */
    public static List<String[]> readGoalStates(String inputFormat) {
        List<String[]> goalStatesList = new ArrayList<>();
        switch (inputFormat) {
            case "":
            case "d":
            case "default":
                return readGoalStates();
            case "C":
            case "c":
                throw new UnsupportedOperationException();
        }
        return goalStatesList;
    }
}
