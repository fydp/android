package VuforiaSamples.app.ImageTargets;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class JSONUtils {

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
        strokeObject.put("colour", colour);
        strokeObject.put("drawingId", "27dfb49c-3d02-41b2-9850-273b6deb6dd1");
        strokeObject.put("userId", userId);
        strokeObject.put("points", array);
        Log.d(TAG, strokeObject.toString());
        return strokeObject;
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