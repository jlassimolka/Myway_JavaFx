import java.awt.Color;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieToolTipGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import tn.esprit.entity.Moyentp;
import tn.esprit.services.MoyentpService;

public class PieChart extends JFrame {

    private static final long serialVersionUID = 1L;

    public PieChart() {

        // Fetch data from the database
        MoyentpService moyentpService = new MoyentpService();
        List<Moyentp> moyentps = moyentpService.getAll();

        // Group the data by type
        Map<String, Long> typeCounts = moyentps.stream()
                .collect(Collectors.groupingBy(Moyentp::getType, Collectors.counting()));

       // Create a pie chart dataset
DefaultPieDataset dataset = new DefaultPieDataset();
typeCounts.forEach((type, count) -> {
    long totalCount = moyentps.stream().filter(m -> m.getType().equals(type)).count();
    String label = String.format("%s (%d)", type, totalCount);
    dataset.setValue(label, count);
});


        // Create a chart
        JFreeChart chart = ChartFactory.createPieChart("Transport Types", dataset, true, true, false);
        PiePlot plot = (PiePlot) chart.getPlot();

        // Customize the chart
        plot.setSectionPaint("Bus", Color.BLUE);
        plot.setSectionPaint("Metro", Color.GREEN);
        plot.setSectionPaint("Train", Color.RED);
        
        // Set the tooltip generator to display both section name and value as percentage
        DecimalFormat df = new DecimalFormat("#.##%");
        StandardPieToolTipGenerator tooltipGenerator = new StandardPieToolTipGenerator(
                "{0}: {1} ({2})", df, df);
        plot.setToolTipGenerator(tooltipGenerator);

        // Add the chart to a panel and set the panel to this frame
        ChartPanel chartPanel = new ChartPanel(chart);
        setContentPane(chartPanel);
    }

    public static void main(String[] args) {
        PieChart chart = new PieChart();
        chart.setSize(600, 400);
        chart.setVisible(true);
    }
}
