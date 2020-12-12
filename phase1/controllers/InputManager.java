package unused.controllers;

import java.util.Scanner;

/**
 * This is the class that will manage all the input from the user as the conference system runs.
 */
public class InputManager {
    private static Scanner input = new Scanner(System.in);
    private static OutputManager out = new OutputManager();

    /**
     * Prints a prompt to the user for some String input,
     * and waits for the user String input.
     * @param msg
     *      The prompt for the user to read.
     * @return
     *      The String user entered.
     */
    public String getInputString(String msg) {
        out.printPrompt(msg);
        return input.nextLine();
    }

    /**
     * Prints a prompt to the user for an integer input,
     * and waits for the user integer input.
     * @param msg
     *      The prompt for the user to read.
     * @return
     *     The integer user enter.
     */
    public int getInputInt(String msg) {
        out.printPrompt(msg);
        String choice = input.nextLine(); //nextInt does not process the ENTER key
        if (choice.equals("")){
            return 666;
        }
        for (int i = 0; i < choice.length(); i++){
            if (!("1234567890".contains(String.valueOf(choice.charAt(i))))){
//                System.out.println("OK");
                return 666;
            }
        }
        return Integer.parseInt(choice);
    }

    /**
     * A nice break for the user while reading prompts.
     */
    public void pause() {
        getInputString("(Press \"Enter\" to continue...)");
        out.printBlankLine();
    }

    /**
     * Close system resources opened by the program.
     */
    public void closeScanner() {
        input.close();
    }
}
