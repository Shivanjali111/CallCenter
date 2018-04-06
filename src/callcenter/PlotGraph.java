package callcenter;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

public class PlotGraph {

    int[] score1;
    Date[] call_date1;
    String name1;
    int[] score2;
    Date[] call_date2;
    String name2;
    int flag;
    JFreeChart chart;
    
    public PlotGraph(JPanel p, int[] score, Date[] call_date, String n) {
        score1 = score;
        call_date1 = call_date;
        name1 = n;
        JPanel chartPanel = createChartPanel();
        p.add(chartPanel, BorderLayout.CENTER);
        p.setSize(680, 580);
    }

    public PlotGraph(JPanel p, int[] score1, Date[] call_date1, String n1, int[] score2, Date[] call_date2, String n2) {
        this.score1 = score1;
        this.call_date1 = call_date1;
        name1 = n1;
        this.score2 = score2;
        this.call_date2 = call_date2;
        name2 = n2;
        flag = 1;
        JPanel chartPanel = createChartPanel();
        p.add(chartPanel, BorderLayout.CENTER);
        p.setSize(680, 580);
    }

    private JPanel createChartPanel() {
        String chartTitle = "Employee Performace Graph";
        String xAxisLabel = "Date";
        String yAxisLabel = "Score";

        XYDataset dataset = createDataset();

         chart = ChartFactory.createTimeSeriesChart(chartTitle,
                xAxisLabel, yAxisLabel, dataset);

        chart = customize(chart);
        return new ChartPanel(chart);
    }

    private XYDataset createDataset() {
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        TimeSeries series1 = new TimeSeries(name1);
        

        for (int i = 0; i < score1.length; i++) {
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(call_date1[i]);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            int month = calendar.get(Calendar.MONTH) + 1;
            int year = calendar.get(Calendar.YEAR);
            series1.addOrUpdate(new Day(day, month, year), score1[i]);
        }

        dataset.addSeries(series1);

        if (flag == 1) {
            TimeSeries series2 = new TimeSeries(name2);

            for (int i = 0; i < score2.length; i++) {
                Calendar calendar2 = new GregorianCalendar();
                calendar2.setTime(call_date2[i]);
                int day = calendar2.get(Calendar.DAY_OF_MONTH);
                int month = calendar2.get(Calendar.MONTH) + 1;
                int year = calendar2.get(Calendar.YEAR);
                series2.addOrUpdate(new Day(day, month, year), score2[i]);
            }
            dataset.addSeries(series2);

        }

        return dataset;
    }

    public JFreeChart customize(JFreeChart chart) {
        XYPlot plot = chart.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        plot.setRenderer(renderer);

        // sets paint color for each series
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesPaint(1, Color.GREEN);
        //renderer.setSeriesPaint(2, Color.YELLOW);

        // sets thickness for series (using strokes)
        renderer.setSeriesStroke(0, new BasicStroke(4.0f));
        renderer.setSeriesStroke(1, new BasicStroke(3.0f));
        //renderer.setSeriesStroke(2, new BasicStroke(2.0f));

        plot.setRenderer(renderer);
        plot.setOutlinePaint(Color.BLUE);
        plot.setOutlineStroke(new BasicStroke(2.0f));
        plot.setBackgroundPaint(Color.DARK_GRAY);
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);

        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);
        return chart;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                //new PlotGraph().setVisible(true);
            }
        });
    }
}
