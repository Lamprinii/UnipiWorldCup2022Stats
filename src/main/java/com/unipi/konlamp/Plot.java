package com.unipi.konlamp;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

/**
 * This class created shows on screen and print the graph
 */
public class Plot extends JPanel {

    int[] temps;

    /**
     * The constructor
     * @param temps the temperatures that we want print in the plot
     */
    public Plot(int[] temps) {
        this.temps = temps;
    }

    int marg = 60;

    /**
     * This method draws the plot
     * @param grf the <code>Graphics</code> object to protect
     */
    protected void paintComponent(Graphics grf) {
        //create instance of the Graphics to use its methods
        super.paintComponent(grf);
        Graphics2D graph = (Graphics2D) grf;

        //Sets the value of a single preference for the rendering algorithms.
        graph.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // get width and height
        int width = getWidth();
        int height = getHeight();

        // draw graph
        graph.draw(new Line2D.Double(marg, marg, marg, height - marg));
        graph.draw(new Line2D.Double(marg, height - marg, width - marg, height - marg));
        graph.drawString("Temps",5,(int)marg );
        graph.drawString("Time",(float)height - marg, (float)height - marg+30 );

        //find value of x and scale to plot points
        double x = (double) (width - 2 * marg) / (temps.length - 1);
        double scale = (double) (height - 2 * marg) / getMax();

        //set color for points
        graph.setPaint(Color.RED);
        int j=0;//This variable is used to determine the coordintes
        // set points to the graph
        for (int i = temps.length-1; i >= 0; i--) {
            System.out.println(temps[i]);
            double x1 = marg + j * x;
            double y1 = height - marg - scale * temps[i];
            graph.fill(new Ellipse2D.Double(x1 - 2, y1 - 2, 4, 4));
            String str = String.valueOf(temps[i]);
            graph.drawString(str,(float) x1,(float)(y1-5));
            j++;
        }
    }

    /**
     * This method is used to find maximum value
     * @return the max value
     */
    private float getMax() {
        float max = -Integer.MAX_VALUE;
        for (int i = temps.length-1; i >= 0; i--) {
            if (temps[i] > max)
                max = temps[i];

        }
        return max;
    }
}
