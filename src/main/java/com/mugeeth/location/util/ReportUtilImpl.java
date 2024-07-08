package com.mugeeth.location.util;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ReportUtilImpl implements ReportUtil {
    @Override
    public void generatePieChart(String path, List<Object[]> data) { //got the data
        DefaultPieDataset dataset = new DefaultPieDataset();

        for(Object[] objects : data){
            dataset.setValue(objects[0].toString(),Double.valueOf(objects[1].toString())); //copy the data into dataset
        }
        JFreeChart chart = ChartFactory.createPieChart3D("Location Type Report",dataset,false,false,false); //crated the Jfreechart using chartfactory from Jfreechart api
        //Object representation converted into jpeg file below
        try {
            ChartUtilities.saveChartAsJPEG(new File(path+"/pieChart.jpeg"), chart, 300, 300);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
