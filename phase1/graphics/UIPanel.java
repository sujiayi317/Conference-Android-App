package graphics;

import java.util.Arrays;

public class UIPanel {
    private static StringBuilder output = new StringBuilder();
    private int width;
    private int height;
    private char border = '*';
    private char[][] panel;

    public UIPanel(int width, int height) {
        this.width = width;
        this.height = height;
        panel = new char[height][width];
        clear();
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void clear() {
        for (int i = 0; i < panel.length; i++) {
            Arrays.fill(panel[i], border);
        }
        Arrays.fill(panel[0],' ');
        Arrays.fill(panel[panel.length-1], ' ');
    }

    public void set(int x, int y, char c) {
        if (0 <= x && x < width && 0 <= y && y < height) {
            panel[y][x] = c;
        }
    }

    public void display(int width, int height) {
        for (int y = height - 1; y >= 0; y--) {
            output.append(Arrays.copyOf(panel[y], width)); // append an entire row of the 2D array
            output.append('\n'); // finish the line for this row
        }
        System.out.print(output);
        output.delete(0, output.length());
    }

}
