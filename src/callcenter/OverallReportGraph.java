package callcenter;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

public class OverallReportGraph extends JFrame {

    JTable table;

    public OverallReportGraph(JTable t) {
        table = t;
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        DefaultTableModel dtm = (DefaultTableModel) table.getModel();

        for (int i = 0; i < dtm.getRowCount(); i++) {
            String x = dtm.getValueAt(i, 1).toString();
            int y = Integer.parseInt(dtm.getValueAt(i, 3).toString().trim());
            dataset.addValue(y, "Positive Score", dtm.getValueAt(i, 1).toString());
        }
        for (int i = 0; i < dtm.getRowCount(); i++) {
            String x = dtm.getValueAt(i, 1).toString();
            int y = Integer.parseInt(dtm.getValueAt(i, 5).toString().trim());
            dataset.addValue(y, "Negative Score", dtm.getValueAt(i, 1).toString());
        }
        for (int i = 0; i < dtm.getRowCount(); i++) {
            String x = dtm.getValueAt(i, 1).toString();
            int y = Integer.parseInt(dtm.getValueAt(i, 6).toString().trim());
            dataset.addValue(y, "Total Score", dtm.getValueAt(i, 1).toString());
        }

        JFreeChart chart = ChartFactory.createBarChart("Overall Performance", "", "Score", dataset, PlotOrientation.VERTICAL, true, true, false);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);

        CategoryPlot plot = chart.getCategoryPlot();
        plot.getRenderer().setSeriesPaint(0, new Color(0, 102, 255));
        plot.setOutlinePaint(Color.BLUE);
        plot.setOutlineStroke(new BasicStroke(2.0f));
        plot.setBackgroundPaint(Color.DARK_GRAY);
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);

        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setMaximumBarWidth(.1);
        renderer.setDrawBarOutline(false);
        //final GradientPaint gp0 = new GradientPaint(0.0f, 0.0f, Color.blue, 0.0f, 0.0f, Color.lightGray);
        //final GradientPaint gp1 = new GradientPaint(0.0f, 0.0f, Color.green, 0.0f, 0.0f, Color.lightGray);
        final GradientPaint gp2 = new GradientPaint(0.0f, 0.0f, Color.green, 0.0f, 0.0f, Color.lightGray);
        //renderer.setSeriesPaint(0, gp0);
        //renderer.setSeriesPaint(1, gp1);
        renderer.setSeriesPaint(2, gp2);

        add(chartPanel);

        pack();
        setTitle("Bar chart");
        setLocationRelativeTo(null);

    }

}
