package sample;

import java.awt.*;
import javax.swing.*;

public class BarGraph extends JPanel {
    private static final Color BACKGROUND_COLOR = Color.white;
    private static final Color BAR_COLOR = Color.red;
    private int x = 78;
    private final int[] inputData;
    private final String name1;
    private final String name2;
    private final int MAX;

    public BarGraph(final int[] inputData,String name1, String name2, int MAX) {
        this.name1 = name1;
        this.name2 = name2;
        this.inputData = inputData;
        this.MAX = MAX;
    }
    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);

        drawBars(g);
    }

    private void drawBars(final Graphics g) {

        int /*i,*/ OUTER_MARGIN = 20,
                WIDTH = 800 + 2 * OUTER_MARGIN,
                HEIGHT = 600 + 2 * OUTER_MARGIN;
        /*SPACE_BETWEEN_BARS = 10, SPACE_ON_TOP_BOTTOM = 25;*/

        g.setColor(BACKGROUND_COLOR);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        g.setColor(BAR_COLOR);
        final int barWidth = 100;
        if(MAX != 0) {
            for (int inputDatum : inputData) {
                final int barHeight = inputDatum * 200 / MAX;
                final int y = 260 - inputDatum * 200 / MAX;
                g.fillRect(x, y, barWidth, barHeight);
                x = x + 129;
            }
            final int barHeight = 0;
            final int y = 0;
            g.fillRect(x, y, barWidth, barHeight);

        }
        Font myFont = new Font("Serif", Font.BOLD, 20);
        g.setColor(Color.BLACK);
        g.drawLine(192, 300, 192, 17);
        g.setColor(Color.BLUE);
        g.setFont(new Font("Monospaced", Font.BOLD, 15));
        g.drawString(name1+"          "+name2, 90, 25);
        String Player1 = String.valueOf(inputData[0]);
        String Player2 = String.valueOf(inputData[1]);
        g.drawString("$"+ Player1 + "             " + "$"+ Player2, 118, 45);
        g.setColor(Color.black);
        g.setFont(myFont);
        g.drawString("Profit",165,15);

    }

}