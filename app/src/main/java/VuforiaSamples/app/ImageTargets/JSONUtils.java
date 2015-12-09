package VuforiaSamples.app.ImageTargets;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.ArrayList;

public class JSONUtils {
    public static final String FIXED_DRAWING_ID = "a0c81763-a8b5-4c0e-a065-0b3f95ef9daa";
    private static final String TAG = "JSONUtils";

    public static JSONObject pointListToJson(List<Point> points, int colour, Context activity) throws JSONException {
        TelephonyManager tManager = (TelephonyManager) activity.getSystemService(Context.TELEPHONY_SERVICE);
        String uid = tManager.getDeviceId();
        Log.d(TAG, uid);
        String userId = "";
        if (uid.equals("TODO")) {
            userId = "76c42a03-11f8-4f94-8ef5-3873cbd45c56";
        } else if (uid.equals("TODO")) {
            userId = "2bfc6f9e-30e0-4568-a4f3-ea03c78eead1";
        }

        JSONArray array = new JSONArray();
        for (Point p : points) {
            JSONObject jsonPoint = new JSONObject();
            jsonPoint.put("x", p.getX());
            jsonPoint.put("y", p.getY());
            array.put(jsonPoint);
        }
        JSONObject strokeObject = new JSONObject();
        strokeObject.put("colour", colour+"");
        strokeObject.put("drawingId", FIXED_DRAWING_ID);
        strokeObject.put("userId", userId);
        strokeObject.put("points", array);
        Log.d(TAG, strokeObject.toString());
        return strokeObject;
    }

    public static List<Stroke> jsonToStrokeList(JSONObject object) {
        List<Stroke> strokes = new ArrayList<>();
        try {
            JSONArray jsonStrokes = object.getJSONArray("strokes");
            for (int i = 0; i < jsonStrokes.length(); i++) {
                JSONObject jsonStroke = jsonStrokes.getJSONObject(i);
                strokes.add(jsonToStroke(jsonStroke));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return strokes;
    }

    public static Stroke jsonToStroke(JSONObject object) {
        try {
          //  String id = object.getString("id");
            String colour = object.getString("colour");
            String drawingId = object.getString("drawingId");
            String userId = object.getString("userId");
            Stroke stroke = new Stroke(null, colour, drawingId, userId);

            // Get all points
            JSONArray jsonPoints = object.getJSONArray("points");
            for (int i = 0; i < jsonPoints.length(); i++) {
                JSONObject jsonPoint = jsonPoints.getJSONObject(i);
                //String pointId = jsonPoint.getString("id");
                //String strokeId = jsonPoint.getString("strokeId");
                int x = jsonPoint.getInt("x");
                int y = jsonPoint.getInt("y");
                Point p = new Point(null, null, x, y);
                stroke.addPoint(p);
            }
            return stroke;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
