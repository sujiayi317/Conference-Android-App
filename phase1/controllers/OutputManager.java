package controllers;

/**
 * This is the class that will manage all the output as the conference system runs.
 */
public class OutputManager {


//    /**
//     * This is used to present the
//     * @param description
//     */
//    public void printNicely(Object menu){
//        //connect to the menu presenter
//    }

    /**
     * This is used by the InputManager for prompts.
     * @param prompt
     *      Prompt for the user.
     */
    public void printPrompt(String prompt) {
        System.out.print(prompt);
    }

    /**
     * Prints out a blank line.
     */
    public void printBlankLine() {
        System.out.println();
    }
}
