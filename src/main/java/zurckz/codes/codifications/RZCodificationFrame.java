/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zurckz.codes.codifications;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.jfree.chart.plot.PlotOrientation;

/**
 *
 * @author Zurck'z
 */
public class RZCodificationFrame extends JFrame {

    private JTextField textField;
    private JButton button;
    private JPanel chartPanel;

    public RZCodificationFrame(String title) {
        super(title);

        JPanel inputPanel = new JPanel();
        textField = new JTextField(10);
        textField.setText("");
        button = new JButton("Procesar");
        inputPanel.add(textField);
        inputPanel.add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                procesarCadena(textField.getText());
            }
        });

        chartPanel = new JPanel();
        chartPanel.setPreferredSize(new Dimension(600, 400));

        this.setLayout(new BorderLayout());
        this.add(inputPanel, BorderLayout.NORTH);
        this.add(chartPanel, BorderLayout.CENTER);
    }

    private void procesarCadena(String cadena) {

        XYSeries series = new XYSeries("Signal");

        int x = 0;
        int y = 1;

        for (char c : cadena.toCharArray()) {
            int valor = Character.getNumericValue(c);
            
            if (valor == 0) {
                valor = -1;
            }
            series.add(x++, valor);
            series.add(x, valor);
            series.add(x++, 0);
            series.add(x, 0);
        }

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Codificación RZ",
                "Eje X",
                "Eje Y",
                null,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        chart.getXYPlot().setDataset(0, new XYSeriesCollection(series));

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(600, 400));
        this.chartPanel.removeAll();
        this.chartPanel.add(chartPanel);
        this.chartPanel.revalidate();
    }

    public static void main(String[] args) {
        RZCodificationFrame chart = new RZCodificationFrame("Codificación RZ");
        chart.setSize(600, 550);
        chart.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        chart.setVisible(true);
    }

}
