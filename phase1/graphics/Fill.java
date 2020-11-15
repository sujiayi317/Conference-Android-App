package graphics;

public class Fill {
    private UIPanel parentPanel;
    private Point bottomLeft;
    private Point topRight;
    private char stroke;

    Fill(int x1, int y1, int x2, int y2, char stroke) {
        bottomLeft = new Point(x1, y1);
        topRight = new Point(x2, y2);
        this.stroke = stroke;
    }

    void setParent(UIPanel parentPanel) {
        this.parentPanel = parentPanel;
    }


    void draw() {
        for (int j = topRight.y; j <= bottomLeft.y; j++) {
            for (int i = bottomLeft.x; i <= topRight.x; i++) {
                parentPanel.set(i, j, stroke);
            }
        }
    }

}
