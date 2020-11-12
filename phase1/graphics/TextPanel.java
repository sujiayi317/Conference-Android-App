package graphics;

/**
 * This class takes care of printing story output within double-border boxes.
 *
 * @author Dr Russell Campbell
 *
 */
public class TextPanel {
    private final static int MAX_WIDTH = 90;
    private final static int MAX_HEIGHT = 40;
    private static Panel2D heading = new Panel2D(MAX_WIDTH, MAX_HEIGHT);
    private int width;
    private int height;

    /**
     * This method centres the given strings inside a rectangular design
     * for output. The user can then read its output within a double-bordered box.
     *
     * @param message
     *   An array of strings that will be printed,
     *   one string on each line.
     *
     * @throws IllegalArgumentException
     *   If any string in the message array is longer than 82 chars,
     *   then this exception will be thrown. This obviously isn't the
     *   best way to deal with output messages, but it gives you an
     *   example of one strict (and simple) way you could deal with
     *   this issue. You can read *much* more about line breaking at
     *   <a href="https://xxyxyz.org/line-breaking/">XXYXYZ</a> site.
     */
    public void print(String... message) throws IllegalArgumentException {
        width = setWidth(message);
        height = setHeight(message);

        placeBorderAndFill();  // draw a double-border around the message
        placeMessage(message); // put the characters of the message in the panel

        heading.display(width, height); // print the formatted message to output
        heading.clear(); // this is so old data is gone for next time
        // this is because we are using a *static* Panel2D, one instance shared
    }

    // A private helper method should not be part of the documentation.
    // You are free to read the code to help yourself study.
    private void centrePlace(Panel2D heading, String str, int y) {
        int n = str.length();
        int x = (width - n) / 2;
        for (int i = 0; i < n; i++) {
            heading.set(x + i, y, str.charAt(i));
        }
    }

    // an example of parameter validation: is the value passed in good?
    // our definition of "good" could be whatever we decide it to be
    private int setWidth(String[] message) throws IllegalArgumentException {
        int max = 0;
        for (String str : message) {
            if (str.length() + 8 > MAX_WIDTH) { // includes padding and border
                throw new IllegalArgumentException(
                        // since "good" is arbitrary, you also should describe what is "bad"
                        "Part of the message is too wide for the TextPanel"
                                + " (more than 82 chars):\n\""
                                + str + "\""
                );
            } else if (str.length() + 8 > max){
                max = str.length() + 8;
            }
        }
        if (max > 50)
            return max;
        else
            return 50; // default width of textpanel
    }

    // an example of parameter validation: is the value passed in good?
    // our definition of "good" could be whatever we decide it to be
    private int setHeight(String[] message) throws IllegalArgumentException {
        if (message.length + 8 > MAX_HEIGHT) { // includes padding and border
            throw new IllegalArgumentException(
                    // since "good" is arbitrary, you also should describe what is "bad"
                    "Too many lines in the message for the TextPanel"
                            + " (more than 36 lines)."
            );
        }
        return message.length + 8;
    }

    private void placeBorderAndFill() {
        Fill blank = new Fill(2, height-3, width-3, 2, ' ');
        blank.setParent(heading);
        blank.draw();
        Box border = new Box(0, height-1, width-1, 0, '*');
        border.setParent(heading);
        border.draw();
        border.shiftCorners(2, -2, -2, 2);
        border.draw();
    }

    private void placeMessage(String[] message) {
        // unfortunately, the y value in a 2D plane decreases
        // in the direction humans read down...
        int y = height - 5;
        for (String str : message) {
            centrePlace(heading, str, y--);
        }
    }
}
