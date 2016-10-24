package com.liquorice.app.android.helpers;

import android.graphics.Color;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.liquorice.app.android.core.Constants;

/**
 * Created by eyablonskaya on 7/21/2016.
 */
public class ChartHelper {

    public static void setupPieChart(PieChart reportChart) {
        reportChart.setUsePercentValues(true);
        reportChart.setDescription("");
        reportChart.setExtraOffsets(5, 10, 5, 5);
        reportChart.setDragDecelerationFrictionCoef(0.95f);
        //reportChart.setCenterTextTypeface(Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf"));  //TODO eyablonskaya add typeface

        reportChart.setDrawHoleEnabled(false);
        //reportChart.setHoleColorTransparent(true);

        reportChart.setTransparentCircleColor(Color.WHITE);
        reportChart.setTransparentCircleAlpha(110);

        reportChart.setHoleRadius(58f);
        reportChart.setTransparentCircleRadius(61f);

        //do not show legend text inside the party of chart
        reportChart.setDrawSliceText(false);

        reportChart.setRotationAngle(0);
        // enable rotation of the chart by touch
        reportChart.setRotationEnabled(true);
        reportChart.setHighlightPerTapEnabled(true);


        reportChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);

        reportChart.setCenterTextRadiusPercent(20);

        Legend l = reportChart.getLegend();
        l.setPosition(Legend.LegendPosition.LEFT_OF_CHART);
        l.setTextSize(Constants.REPORT_LEGEND_TEXT_SIZE);
        //l.setTypeface();  //TODO eyablonskaya add typeface
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);
    }

    public static PieData setupPieData(PieDataSet dataSet) {
        PieData pData = new PieData(dataSet);
        pData.setValueFormatter(new PercentFormatter());
        pData.setValueTextSize(Constants.REPORT_DIAGRAM_TEXT_SIZE);
        pData.setValueTextColor(Color.WHITE);
        //pData.setValueTypeface(tf);  //TODO eyablonskaya add typeface
        return pData;
    }
}
