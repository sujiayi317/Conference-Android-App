package controllers;

import graphics.TextUI;

/**
 * This is the class that will manage all the output as the conference system runs.
 */
public class OutputManager {
    private TextUI textUI = new TextUI();

//    /**
//     * This is used to present the
//     * @param description
//     */

    public void printMenu(Object menu) {
        String[] strings = menu.toString().split("\n");
        textUI.print(strings);
    }

    /**
     * This is used by the InputManager for prompts.
     * @param prompt
     *      Prompt for the user.
     */
    public void printPrompt(Object prompt) {
        String[] strings = prompt.toString().split("\n");
        textUI.print(strings);
    }

    /**
     * Prints out a blank line.
     */
    public void printBlankLine() {
        System.out.println();
    }
}
