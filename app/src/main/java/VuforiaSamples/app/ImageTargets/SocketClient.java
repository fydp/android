package VuforiaSamples.app.ImageTargets;


import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class SocketClient {
    private static final String TAG = "SocketClient";
    private static final String INIT = "INIT";
    private static final String RECEIVE_ALL_DRAWINGS = "RECEIVE_ALL_DRAWINGS";
    private static final String RECEIVE_POINTS = "RECEIVE_POINTS";
    private static final String SEND_POINTS = "SEND_POINTS";
    private static final String URL = "http://104.196.40.180:3000/";
    private static SocketClient instance = null;
    private final Context activity;
    private Socket socket;
    private Handler mHandler;

    private SocketClient(final Context activity) {
        this.activity = activity;
        try {
            socket = IO.socket(URL);
        } catch(Exception e) { // TODO change
            // Log all exceptions
            Log.d(TAG, "Client init error: " + e.getMessage());
        }
        mHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message message) {
                Toast.makeText(activity, "receive!", Toast.LENGTH_SHORT).show();

            }
        };
        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {

            @Override
            public void call(Object... args) {
            }

        }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {

            @Override
            public void call(Object... args) {}

        });

        initializeSocketEvents();

        socket.connect();
    }

    public static SocketClient getInstance(Context activity) {
        if (instance == null) {
            instance = new SocketClient(activity);
        }
        return instance;
    }

    public void connect() {
        if (!socket.connected()) {
            socket.connect();
            initialize();
        }
    }

    public void disconnect() {
        socket.disconnect();
    }

    private void initializeSocketEvents() {
        socket.on(RECEIVE_POINTS, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Message message = mHandler.obtainMessage();
                message.sendToTarget();
                JSONObject obj = (JSONObject) args[0];
                // Parse list of points
                Log.d(TAG, obj.toString());
                Stroke stroke = JSONUtils.jsonToStroke(obj);
                ((PointsCallback)activity).onPointsAvailable(stroke.getPoints(), stroke.getColour());
            }
        });

        socket.on(RECEIVE_ALL_DRAWINGS, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Message message = mHandler.obtainMessage();
                message.sendToTarget();
                JSONObject obj = (JSONObject) args[0];
                // Only get first drawing
                Log.d(TAG, obj.toString());
                List<Stroke> strokes = JSONUtils.jsonToStrokeList(obj);
                ((PointsCallback)activity).onStrokesAvailable(strokes);
            }
        });
    }

    private void initialize() {
        socket.emit(INIT); // Get drawing data
    }

    public void sendPoints(List<Point> points, int colour) {
        try {
            JSONObject obj = JSONUtils.pointListToJson(points, colour,activity);
            socket.emit(SEND_POINTS, obj); // Send points through socket
        } catch (JSONException e) {
            Log.d(TAG, "Could not serialize points");
        }
    }
}
