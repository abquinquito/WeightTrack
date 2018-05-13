package com.example.raymundrafael.weighttrak;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

/**
 * Created by raymundrafael on 30/04/2018.
 */

public class Timelinefragment extends Fragment {
    LineGraphSeries<DataPoint> series;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.timeline,container,false);
        // insert graphing data here
        double y,x;
        x = -5.0;

        GraphView graph = (GraphView) v.findViewById(R.id.graph);
        series = new LineGraphSeries<DataPoint>();
        for(int i =0; i<100; i++) {
            x = x + 0.1;
            y = Math.sin(x);
            series.appendData(new DataPoint(x, y), true, 100);
        }
        graph.addSeries(series);
        return v;
    }
}
