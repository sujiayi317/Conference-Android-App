package unused.graphics;

import java.util.Arrays;

/**
 * Class for UI panel
 */
public class UIPanel {
    private static StringBuilder output = new StringBuilder();
    private int width;
    private int height;
    private char border = '*';
    private char[][] panel;

    /**
     *
     * @param width width for UI panel
     * @param height height for UI panel
     */
    public UIPanel(int width, int height) {
        this.width = width;
        this.height = height;
        panel = new char[height][width];
        clear();
    }

    /**
     * setWidth
     * @param width width for UI panel
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * set Height
     * @param height  height for UI panel
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * clear
     */
    public void clear() {
        for (int i = 0; i < panel.length; i++) {
            Arrays.fill(panel[i], border);
        }
        Arrays.fill(panel[0],' ');
        Arrays.fill(panel[panel.length-1], ' ');
    }

    /**
     * set
     *
     * @param x x
     * @param y y
     * @param c c
     */
    public void set(int x, int y, char c) {
        if (0 <= x && x < width && 0 <= y && y < height) {
            panel[y][x] = c;
        }
    }

    /**
     * display
     *
     * @param width width
     * @param height height
     */
    public void display(int width, int height) {
        for (int y = height - 1; y >= 0; y--) {
            output.append(Arrays.copyOf(panel[y], width)); // append an entire row of the 2D array
            output.append('\n'); // finish the line for this row
        }
        System.out.print(output);
        output.delete(0, output.length());
    }

}
