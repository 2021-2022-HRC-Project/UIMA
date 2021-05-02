package planningUnit.utils;

import planningUnit.stripsAlgorithm.Action;
import org.json.JSONObject;
import org.json.XML;
import planningUnit.utils.enums.PlannerState;
import planningUnit.utils.enums.OutputFormat;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * For writing output of STRIPS algo to designated files given designated formats
 */
public class ResultWriter {
    private final OutputFormat outputFormat; // specified output format, could be print xml, JSON, txt
    private Boolean isFirstOutput = true;
    private static FileWriter fileWriter;
    private final static String FOLDER_PATH = "./ResultOutput/"; // folder name
    private final static String FILE_NAME = "ActionPlan"; // file prefix
    private String jsonResult = "";
    private JSONObject outputJsonObject;

    /**
     * ResultWriter constructor for specifying an outputFormat for the output
     *
     * @param outputFormat         inputted output format, could be print, xml, JSON, txt
     */
    public ResultWriter(OutputFormat outputFormat) {
        this.outputFormat = outputFormat;
    }

    /**
     * Write result for an unsolvable situation
     *
     * @param result            output from STRIPS algo
     * @param name              the name of the problem/situation
     * @param initialStates     the initial states of the problem/situation
     * @param goalStates        the goal states of the problem/situation
     * @param plannerState      planner's output status: "UNSOLVABLE"
     */
    public String writeStringResult(String result, String name, String[] initialStates, String[] goalStates, PlannerState plannerState) {
        Action UnsolvableAction = new Action(result, "", "");
        return writeResult(new ArrayList<>(Collections.singletonList(UnsolvableAction)), name, initialStates, goalStates, plannerState);
    }

    /**
     * Write string or file results for every situations of STRIPS world
     *
     * @param plan              outputted best action plan from STRIPS algo
     * @param name              the name of the problem/situation
     * @param initialStates     the initial states of the problem/situation
     * @param goalStates        the goal states of the problem/situation
     * @param plannerState      planner's output status: "NORMAL", etc.
     */
    public String writeResult(List<Action> plan, String name, String[] initialStates, String[] goalStates, PlannerState plannerState) {
        outputJsonObject = getJSONOutput(plan, name, initialStates, goalStates, plannerState);
        jsonResult = outputJsonObject.toString(2);

        if (outputFormat == OutputFormat.PRINT_ONLY) {
            writePrintOnly(plan, name, initialStates, goalStates);
        } else {
            writeFile(plan, name, initialStates, goalStates, plannerState, outputFormat);
        }
        return outputJsonObject.getJSONObject("ActionPlan").getString("plan");
    }

    /**
     * Write print result and outputted to the console
     *
     * @param plan              outputted best action plan from STRIPS algo
     * @param name              the name of the problem/situation
     * @param initialStates     the initial states of the problem/situation
     * @param goalStates        the goal states of the problem/situation
     */
    private void writePrintOnly(List<Action> plan, String name, String[] initialStates, String[] goalStates) {
        System.out.println();
        System.out.println("--------------------------");
        System.out.printf("*******%s*******%n", name);
        System.out.println("--------------------------");
        System.out.println("|     Initial State      |");
        System.out.println("--------------------------");
        for (String initialState : initialStates) {
            System.out.println(initialState);
        }
        System.out.println("--------------------------");
        System.out.println("|       Goal State       |");
        System.out.println("--------------------------");
        for (String goalState : goalStates) {
            System.out.println(goalState);
        }
        System.out.println("--------------------------");
        System.out.println("|    Plan of Actions     |");
        System.out.println("--------------------------");
        System.out.println(planToString(plan));
        System.out.println("--------------------------");
        System.out.println();
    }

    /**
     * Helper method for converting an action plan to a string
     *
     * @param plan         a list of actions showing the plan outputted by STRIPS
     * @return a string of all actions of the plan
     */
    private String planToString(List<Action> plan) {
        return String.join("\n", planToStringArr(plan));
    }

    /**
     * Write result to a file specified by the output format
     *
     * @param plan              outputted best action plan from STRIPS algo
     * @param name              the name of the problem/situation
     * @param initialStates     the initial states of the problem/situation
     * @param goalStates        the goal states of the problem/situation
     * @param plannerState      the state of the output: "NORMAL", "STUCK", etc.
     * @param outputFormat      specified format for the output file: XML, JSON, TXT
     */
    private void writeFile(List<Action> plan, String name, String[] initialStates, String[] goalStates, PlannerState plannerState, OutputFormat outputFormat) {
        String filePath = FOLDER_PATH + FILE_NAME + "_" + name.replace(" ", "_");
        try {
            File directory = new File(FOLDER_PATH);

            if (isFirstOutput) {
                deleteDirectory(directory);
                isFirstOutput = false;
            }
            if (!directory.exists()) {
                boolean mkdirResult = directory.mkdir();
                if (!mkdirResult) {
                    throw new IOException("[ResultWriter.writeFile] Failed to create a new directory: " + directory.getAbsolutePath());
                }
            }
            switch (outputFormat) {
                case JSON:
                    fileWriter = new FileWriter(filePath + ".json");
                    fileWriter.write(jsonResult);
                    break;
                case TXT:
                    fileWriter = new FileWriter(filePath + ".txt");
                    fileWriter.write(getTXTOutput(plan, name, initialStates, goalStates));
                    break;
                case XML:
                    fileWriter = new FileWriter(filePath + ".xml");
                    fileWriter.write(getXMLOutput(plan, name, initialStates, goalStates, plannerState));
                    break;
                default:
                    throw new UnsupportedOperationException("Output in this file type is not supported yet!");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Convert an JSON object to XML
     *
     * @param plan              outputted best action plan from STRIPS algo
     * @param name              the name of the problem/situation
     * @param initialStates     the initial states of the problem/situation
     * @param goalStates        the goal states of the problem/situation
     * @param plannerState      the state of the output: "NORMAL", "STUCK", etc.
     * @return xml string object
     */
    private String getXMLOutput(List<Action> plan, String name, String[] initialStates, String[] goalStates, PlannerState plannerState) {
        JSONObject jsonObject = getJSONOutput(plan, name, initialStates, goalStates, plannerState);
        return XML.toString(jsonObject);
    }

    /**
     * Return a string for writing text results
     *
     * @param plan              outputted best action plan from STRIPS algo
     * @param name              the name of the problem/situation
     * @param initialStates     the initial states of the problem/situation
     * @param goalStates        the goal states of the problem/situation
     * @return a string containing text results
     */
    private String getTXTOutput(List<Action> plan, String name, String[] initialStates, String[] goalStates) {
        System.out.println();

        System.out.println();
        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("--------------------------");
        stringList.add(String.format("*******%s*******", name));
        stringList.add("--------------------------");
        stringList.add("|     Initial State      |");
        stringList.add("--------------------------");
        stringList.addAll(Arrays.asList(initialStates));
        stringList.add("--------------------------");
        stringList.add("|       Goal State       |");
        stringList.add("--------------------------");
        stringList.addAll(Arrays.asList(goalStates));
        stringList.add("--------------------------");
        stringList.add("|    Plan of Actions     |");
        stringList.add("--------------------------");
        stringList.add(planToString(plan));
        stringList.add("--------------------------");
        return String.join("\n", stringList);
    }

    /**
     * Return JSON object for the result
     *
     * @param plan              outputted best action plan from STRIPS algo
     * @param name              the name of the problem/situation
     * @param initialStates     the initial states of the problem/situation
     * @param goalStates        the goal states of the problem/situation
     * @param plannerState      the state of the output: "NORMAL", "STUCK", etc.
     * @return jSONObject       the JSON object containing necessary information of the result
     */
    private JSONObject getJSONOutput(List<Action> plan, String name, String[] initialStates, String[] goalStates, PlannerState plannerState) {
        JSONObject actionPlanJSON = new JSONObject();
        actionPlanJSON.put("name", name);
        actionPlanJSON.put("initialState", Arrays.asList(initialStates));
        actionPlanJSON.put("goalState", Arrays.asList(goalStates));

        JSONObject actionPlanStateJSON = new JSONObject();
        switch (plannerState) {
            case NORMAL:
                actionPlanJSON.put("plan", planToStringArr(plan));
                actionPlanStateJSON.put("unsolvable", false);
                actionPlanStateJSON.put("stuck", false);
                actionPlanStateJSON.put("doNothing", false);
                actionPlanStateJSON.put("normal", true);
                break;
            case UNSOLVABLE:
                actionPlanJSON.put("plan", new ArrayList<String>());
                actionPlanStateJSON.put("unsolvable", true);
                actionPlanStateJSON.put("stuck", false);
                actionPlanStateJSON.put("doNothing", false);
                actionPlanStateJSON.put("normal", false);
                break;
            case STUCK:
                actionPlanJSON.put("plan", new ArrayList<String>());
                actionPlanStateJSON.put("unsolvable", false);
                actionPlanStateJSON.put("stuck", true);
                actionPlanStateJSON.put("doNothing", false);
                actionPlanStateJSON.put("normal", false);
                break;
            case DO_NOTHING:
                actionPlanJSON.put("plan", new ArrayList<String>());
                actionPlanStateJSON.put("unsolvable", false);
                actionPlanStateJSON.put("stuck", false);
                actionPlanStateJSON.put("doNothing", true);
                actionPlanStateJSON.put("normal", false);
                break;
        }
        actionPlanJSON.put("state", actionPlanStateJSON);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ActionPlan", actionPlanJSON);
        return jsonObject;
    }

    /**
     * Helper method for converting a list of action to an arraylist of strings.
     *
     * @param plan              a list of actions for outputted best action plan from STRIPS algo
     * @return stringPlan       an arraylist of string of the plan's actions
     */
    private ArrayList<String> planToStringArr(List<Action> plan) {
        ArrayList<String> stringPlan = new ArrayList<>();
        for (Action action : plan) {
            stringPlan.add(action.getOutputString());
        }
        return stringPlan;
    }

    /**
     * This method will delete all the files and directories within the provided directory.
     * This will also delete the provided directory itself.
     *
     * @param directoryToBeDeleted the directory and all files under it to be removed
     */
    private static void deleteDirectory(File directoryToBeDeleted) throws IOException {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        if (directoryToBeDeleted.exists()) {
            boolean deleteResult = directoryToBeDeleted.delete();
            if (!deleteResult) {
                throw new IOException("[ResultWriter.deleteDirectory] Failed to delete given directory: " + directoryToBeDeleted.getAbsolutePath());
            }
        }

    }
}
