import controllers.Conference;

/**
 * This is the only class in the top-level src folder for executing our program.
 * Note that this class cannot be accessed from any other packages/classes, even though it is public.
 */
public class Console {

    /**
     * Program execution starts here.
     *
     * @param args
     *      An array of Strings that we can use to pass data into our program when it runs.
     */
    public static void main(String[] args) {
        FileReadWriter reader = new FileReadWriter();

        Conference conference = new Conference();
        conference.run();
    }
}
