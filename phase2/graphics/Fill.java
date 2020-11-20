package graphics;

/**
 * Fill graphics
 */
public class Fill {
    private UIPanel parentPanel;
    private Point bottomLeft;
    private Point topRight;
    private char stroke;

    /**
     * Fill graphics
     *
     * @param x1 x1 coordinate
     * @param y1 y1 coordinate
     * @param x2 x2 coordinate
     * @param y2 y2 coordinate
     * @param stroke stroke
     */
    Fill(int x1, int y1, int x2, int y2, char stroke) {
        bottomLeft = new Point(x1, y1);
        topRight = new Point(x2, y2);
        this.stroke = stroke;
    }

    /**
     * setParent
     *
     * @param parentPanel parentPanel
     */
    void setParent(UIPanel parentPanel) {
        this.parentPanel = parentPanel;
    }


    /**
     * draw panel
     */
    void draw() {
        for (int j = topRight.y; j <= bottomLeft.y; j++) {
            for (int i = bottomLeft.x; i <= topRight.x; i++) {
                parentPanel.set(i, j, stroke);
            }
        }
    }

}
