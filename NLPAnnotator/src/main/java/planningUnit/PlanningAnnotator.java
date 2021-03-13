package planningUnit;

import dataStructures.Annotator;
import planningUnit.stripsAlgorithm.Action;
import planningUnit.stripsAlgorithm.STRIPS;
import planningUnit.utils.InputReader;
import planningUnit.utils.ResultWriter;
import planningUnit.utils.enums.OutputFormat;
import planningUnit.utils.enums.PlannerState;
import planningUnit.utils.exceptions.CompleteAtStartException;
import planningUnit.utils.exceptions.StuckException;
import planningUnit.utils.exceptions.UnsolvableException;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class PlanningAnnotator extends Annotator {
    private final static Boolean PRINT_GOAL_STACK = false; // print the stack or not
    private final static OutputFormat OUTPUT_FORMAT = OutputFormat.JSON; // specifying an output format
    private final static long TIMEOUT_THRESHOLD = 2000; // set planner time threshold
    private String jsonResult;
    public final static String PLANNER_WRAPPER_STRING = "\"edu.rosehulman.aixprize.pipeline.types.Planner\":";


    @Override
    public String process(String request) throws IOException {
        // TODO: The initialState and goalState are using mock data now, need to parse it from request variable
        String[] initialState = new String[]{"ON_TABLE(a)", "ON_TABLE(b)", "CLEAR(a)", "CLEAR(b)", "ARM_EMPTY"};
        String[] goalState = new String[]{"ON_TABLE(a)", "CLEAR(b)", "ON(b,a)", "ARM_EMPTY"};
        runStripsAlgorithm(initialState, goalState);
        return "{" + PLANNER_WRAPPER_STRING + "[" + jsonResult + "]}";
    }

    public void runStripsAlgorithm(String[] initialState, String[] goalState) {
        ResultWriter resultWriter = new ResultWriter(OUTPUT_FORMAT);

        // Run the planner
        String name = "Planner Result";

        System.out.println("Start working on task: " + name);
        try {
            STRIPS strips = new STRIPS(initialState, goalState, PRINT_GOAL_STACK, TIMEOUT_THRESHOLD);
            List<Action> plan = strips.getPlan();
            jsonResult = resultWriter.writeResult(plan, name, initialState, goalState, PlannerState.NORMAL);
        } catch (UnsolvableException e) {
            jsonResult = resultWriter.writeStringResult("UNSOLVABLE", name, initialState, goalState, PlannerState.UNSOLVABLE);
        } catch (StuckException | TimeoutException e) {
            jsonResult = resultWriter.writeStringResult("STRIPS_STUCK", name, initialState, goalState, PlannerState.STUCK);
        } catch (CompleteAtStartException e) {
            jsonResult = resultWriter.writeStringResult("NOTHING_NEEDS_TO_BE_DONE", name, initialState, goalState, PlannerState.DO_NOTHING);
        }
        System.out.println("Completed task: " + name);

    }
}
