package graphics;

/**
 * A shape of a filled rectangle that can be drawn in its <code>Panel2D parentPanel</code>.
 * This is also an example of a class that is only used inside its package. Remember, no access
 * modifier means that the code is package private, so only other classes inside the graphics
 * package can use it.
 *
 * @author Dr Russell Campbell
 */
class Fill {
    private Panel2D parentPanel;
    private Point2D topLeft;
    private Point2D bottomRight;
    private char stroke;

    /**
     * Create an instance of a rectangle shape.
     *
     * @param x1
     *   Horizontal position of the top-left corner.
     * @param y1
     *   Vertical position of the top-left corner.
     * @param x2
     *   Horizontal position of the bottom-right corner.
     * @param y2
     *   Vertical position of the bottom-right corner.
     * @param stroke
     *   The character used to fill all characters within the rectangle.
     */
    Fill(int x1, int y1, int x2, int y2, char stroke) {
        topLeft = new Point2D(x1, y1);
        bottomRight = new Point2D(x2, y2);
        this.stroke = stroke;
    }

    /**
     * Set the <code>parentPanel</code> to draw inside for later.
     *
     * @param parentPanel
     *   An instance of a <code>Panel2D</code> to be used for drawing inside later.
     */
    void setParent(Panel2D parentPanel) {
        this.parentPanel = parentPanel;
    }

    /**
     * Draws this filled rectangle shape in its <code>parentPanel</code>.
     */
    void draw() {
        for (int j = bottomRight.y; j <= topLeft.y; j++) {
            for (int i = topLeft.x; i <= bottomRight.x; i++) {
                parentPanel.set(i, j, stroke);
            }
        }
    }
}
