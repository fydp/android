package VuforiaSamples.app.ImageTargets;

import android.graphics.Color;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

public class GeneratePointsAsyncTask extends AsyncTask<Void, Void, List<Point>>{

    PointsCallback mCallback;
    public GeneratePointsAsyncTask(PointsCallback callback) {
        mCallback = callback;
    }

    @Override
    protected List<Point> doInBackground(Void... params) {

        List<Point> points = new ArrayList<>();
        for (int i = 0; i < random(10, 25); i++) {
            points.add(new Point(null, null, random(0, 247), random(0, 2*173)));
        }
        return points;
    }

    private int random(int from, int to) {
        return (int)(Math.random()*to) + from;
    }

    @Override
    protected void onPostExecute(List<Point> points) {
        mCallback.onPointsAvailable(points, Color.BLACK+"");
    }
}