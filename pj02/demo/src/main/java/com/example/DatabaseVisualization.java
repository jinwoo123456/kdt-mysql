package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.data.category.DefaultCategoryDataset;

public class DatabaseVisualization extends ApplicationFrame {

    public DatabaseVisualization(String title) {
        super(title);

        // 데이터세트 생성
        DefaultCategoryDataset dataset = getDataFromDatabase();

        // 막대 그래프 생성
        JFreeChart barChart = ChartFactory.createBarChart(
                "STU1 and STU2 Data", // 차트 제목
                "STU1", // X축 라벨
                "STU2", // Y축 라벨
                dataset, // 데이터
                PlotOrientation.VERTICAL,
                true, true, false);

        // 차트를 패널에 추가하여 표시
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
        setContentPane(chartPanel);
    }

    // 데이터베이스에서 데이터를 가져오는 메서드
    private DefaultCategoryDataset getDataFromDatabase() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String url = "jdbc:mysql://localhost:3305/YOUR_DATABASE_NAME";
        String user = "YOUR_USERNAME";
        String password = "YOUR_PASSWORD";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT STU1, STU2 FROM TEST";
            try (Statement stmt = connection.createStatement();
                    ResultSet rs = stmt.executeQuery(query)) {

                // 결과에서 데이터 추출
                while (rs.next()) {
                    String stu1 = rs.getString("STU1");
                    String stu2 = rs.getString("STU2");

                    // 데이터셋에 추가
                    dataset.addValue(Double.parseDouble(stu2), "STU2", stu1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataset;
    }

    public static void main(String[] args) {
        DatabaseVisualization chart = new DatabaseVisualization("Database Visualization");
        chart.pack();
        chart.setVisible(true);
    }
}
