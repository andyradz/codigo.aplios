package com.codigo.aplios.sdk.core;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class GraphPanel
        extends JPanel {

    private final int padding = 25;

    private final int labelPadding = 25;

    private final Color lineColor = new Color(44, 102, 230, 180);

    private final Color pointColor = new Color(100, 100, 100, 180);

    private final Color gridColor = new Color(200, 200, 200, 200);

    private static final Stroke GRAPH_STROKE = new BasicStroke(2f);

    private final int pointWidth = 4;

    private final int numberYDivisions = 10;

    private List<Double> scores;

    public GraphPanel(final List<Double> scores) {

        this.scores = scores;
    }

    @Override
    protected void paintComponent(final Graphics g) {

        super.paintComponent(g);
        final Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        final double xScale = ((double)this.getWidth() - (2 * this.padding) - this.labelPadding) /
                (this.scores.size() - 1);
        final double yScale = ((double)this.getHeight() - (2 * this.padding) - this.labelPadding) /
                (this.getMaxScore() - this.getMinScore());

        final List<Point> graphPoints = new ArrayList<>();
        for (int i = 0; i < this.scores.size(); i++) {
            final int x1 = (int)((i * xScale) + this.padding + this.labelPadding);
            final int y1 = (int)(((this.getMaxScore() - this.scores.get(i)) * yScale) + this.padding);
            graphPoints.add(new Point(x1, y1));
        }

        // draw white background
        g2.setColor(Color.WHITE);
        g2.fillRect(this.padding + this.labelPadding, this.padding,
                this.getWidth() - (2 * this.padding) - this.labelPadding,
                this.getHeight() - (2 * this.padding) - this.labelPadding);
        g2.setColor(Color.BLACK);

        // create hatch marks and grid lines for y axis.
        for (int i = 0; i < (this.numberYDivisions + 1); i++) {
            final int x0 = this.padding + this.labelPadding;
            final int x1 = this.pointWidth + this.padding + this.labelPadding;
            final int y0 = this.getHeight() -
                    (((i * (this.getHeight() - (this.padding * 2) - this.labelPadding)) / this.numberYDivisions) +
                    this.padding + this.labelPadding);
            final int y1 = y0;
            if (this.scores.size() > 0) {
                g2.setColor(this.gridColor);
                g2.drawLine(this.padding + this.labelPadding + 1 + this.pointWidth, y0, this.getWidth() - this.padding,
                        y1);
                g2.setColor(Color.BLACK);
                final String yLabel = ((int)((this.getMinScore() +
                        ((this.getMaxScore() - this.getMinScore()) * ((i * 1.0) / this.numberYDivisions))) * 100) /
                        100.0) + "";
                final FontMetrics metrics = g2.getFontMetrics();
                final int labelWidth = metrics.stringWidth(yLabel);
                g2.drawString(yLabel, x0 - labelWidth - 5, (y0 + (metrics.getHeight() / 2)) - 3);
            }
            g2.drawLine(x0, y0, x1, y1);
        }

        // and for x axis
        for (int i = 0; i < this.scores.size(); i++)
            if (this.scores.size() > 1) {
                final int x0 = ((i * (this.getWidth() - (this.padding * 2) - this.labelPadding)) /
                        (this.scores.size() - 1)) + this.padding + this.labelPadding;
                final int x1 = x0;
                final int y0 = this.getHeight() - this.padding - this.labelPadding;
                final int y1 = y0 - this.pointWidth;
                if ((i % ((int)(this.scores.size() / 20.0) + 1)) == 0) {
                    g2.setColor(this.gridColor);
                    g2.drawLine(x0, this.getHeight() - this.padding - this.labelPadding - 1 - this.pointWidth, x1,
                            this.padding);
                    g2.setColor(Color.BLACK);
                    final String xLabel = i + "";
                    final FontMetrics metrics = g2.getFontMetrics();
                    final int labelWidth = metrics.stringWidth(xLabel);
                    g2.drawString(xLabel, x0 - (labelWidth / 2), y0 + metrics.getHeight() + 3);
                }
                g2.drawLine(x0, y0, x1, y1);
            }

        // create x and y axes
        g2.drawLine(this.padding + this.labelPadding, this.getHeight() - this.padding - this.labelPadding,
                this.padding + this.labelPadding, this.padding);
        g2.drawLine(this.padding + this.labelPadding, this.getHeight() - this.padding - this.labelPadding,
                this.getWidth() - this.padding, this.getHeight() - this.padding - this.labelPadding);

        final Stroke oldStroke = g2.getStroke();
        g2.setColor(this.lineColor);
        g2.setStroke(GraphPanel.GRAPH_STROKE);
        for (int i = 0; i < (graphPoints.size() - 1); i++) {
            final int x1 = graphPoints.get(i).x;
            final int y1 = graphPoints.get(i).y;
            final int x2 = graphPoints.get(i + 1).x;
            final int y2 = graphPoints.get(i + 1).y;
            g2.drawLine(x1, y1, x2, y2);
        }

        g2.setStroke(oldStroke);
        g2.setColor(this.pointColor);
        for (int i = 0; i < graphPoints.size(); i++) {
            final int x = graphPoints.get(i).x - (this.pointWidth / 2);
            final int y = graphPoints.get(i).y - (this.pointWidth / 2);
            final int ovalW = this.pointWidth;
            final int ovalH = this.pointWidth;
            g2.fillOval(x, y, ovalW, ovalH);
        }
    }

    // @Override
    // public Dimension getPreferredSize() {
    // return new Dimension(width, heigth);
    // }
    private double getMinScore() {

        double minScore = Double.MAX_VALUE;
        for (final Double score : this.scores)
            minScore = Math.min(minScore, score);
        return minScore;
    }

    private double getMaxScore() {

        double maxScore = Double.MIN_VALUE;
        for (final Double score : this.scores)
            maxScore = Math.max(maxScore, score);
        return maxScore;
    }

    public void setScores(final List<Double> scores) {

        this.scores = scores;
        this.invalidate();
        this.repaint();
    }

    public List<Double> getScores() {

        return this.scores;
    }

    private static void createAndShowGui() {

        final List<Double> scores = new ArrayList<>();
        final Random random = new Random();
        final int maxDataPoints = 40;
        final int maxScore = 10;
        for (int i = 0; i < maxDataPoints; i++)
            scores.add(random.nextDouble() * maxScore);
        // scores.add((double) i);
        final GraphPanel mainPanel = new GraphPanel(scores);
        mainPanel.setPreferredSize(new Dimension(800, 600));
        final JFrame frame = new JFrame("DrawGraph");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane()
                .add(mainPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(final String[] args) {

        SwingUtilities.invokeLater(() -> GraphPanel.createAndShowGui());
    }

}
