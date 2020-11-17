package graphics;

/**
 * This class is the text user interface
 */
public class TextUI {
    private static UIPanel ui = new UIPanel(200,100);
    private int width;
    private int height;

    /**
     * This method centres the given strings inside a rectangular design
     * for output. The user can then read its output within a double-bordered box.
     *
     * @param message
     *   An array of strings that will be printed,
     *   one string on each line.
     */
    public void print(String[] message) {
        width = setWidth(message);
        height = setHeight(message);

        ui.setWidth(width);
        ui.setHeight(height);

        placeBorderAndFill();
        placeMessage(message);

        ui.display(width, height);
        ui.clear();
    }

    /**
     * set Width
     * @param message message
     * @return width
     */
    private int setWidth(String[] message) {
        int width = 0;
        for (String str : message) {
            width = str.length() + 8;
        }

        return Math.max(width, 60);

    }

    /**
     * set Height
     * @param message message
     * @return Height
     */
    private int setHeight(String[] message) {
        return message.length + 3;
    }

    /**
     * place Border And Fill
     */
    private void placeBorderAndFill() {
        Fill blank = new Fill(2, height-2, width-3, 2, ' ');
        blank.setParent(ui);
        blank.draw();
    }

    /**
     * place Message
     * @param message String[] message
     */
    private void placeMessage(String[] message) {
        int y = height - 2;
        for (String str : message) {
            centrePlace(ui, str, y--);
        }
    }

    /**
     * centre Place
     *
     * @param heading heading of the UI panel
     * @param str string
     * @param y y
     */
    private void centrePlace(UIPanel heading, String str, int y) {
        int n = str.length();
        int x = (width - n) / 2;
        for (int i = 0; i < n; i++) {
            heading.set(x + i, y, str.charAt(i));
        }
    }
}
