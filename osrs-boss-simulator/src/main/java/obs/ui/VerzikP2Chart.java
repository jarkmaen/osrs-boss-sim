package obs.ui;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import obs.util.Pair;

public class VerzikP2Chart extends Application {

    private static ArrayList<Pair<Integer, Double>> data;

    public static void setData(ArrayList<Pair<Integer, Double>> results) {
        data = results;
    }

    @Override
    public void start(Stage stage) {
        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("Nylo Threshold (%)");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setAutoRanging(false);
        yAxis.setLabel("Average Net Damage");
        yAxis.setLowerBound(73);
        yAxis.setTickUnit(1);
        yAxis.setUpperBound(85);

        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setCreateSymbols(false);
        lineChart.setLegendVisible(false);

        XYChart.Series<Number, Number> series = new XYChart.Series<>();

        double maxValue = 0;
        int maxX = 0;

        for (int i = 0; i < data.size(); i++) {
            Pair<Integer, Double> pair = data.get(i);
            int x = pair.getKey();
            double y = pair.getValue();

            series.getData().add(new XYChart.Data<>(x, y));

            if (y > maxValue) {
                maxValue = y;
                maxX = x;
            }
        }

        lineChart.getData().add(series);

        XYChart.Series<Number, Number> verticalLine = new XYChart.Series<>();
        verticalLine.getData().add(new XYChart.Data<>(maxX, yAxis.getUpperBound()));
        verticalLine.getData().add(new XYChart.Data<>(maxX, yAxis.getLowerBound()));

        lineChart.getData().add(verticalLine);

        series.getNode().setStyle("-fx-stroke: #2379b5;");
        verticalLine.getNode().setStyle(
                "-fx-stroke: red; -fx-stroke-width: 2px; -fx-stroke-dash-array: 10 5;");

        Scene scene = new Scene(lineChart, 1280, 720);
        stage.setScene(scene);
        stage.setTitle("Verzik P2 Chart");
        stage.show();
    }
}
