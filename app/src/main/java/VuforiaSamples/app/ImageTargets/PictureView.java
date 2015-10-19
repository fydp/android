package VuforiaSamples.app.ImageTargets;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;

public class PictureView extends View {

    public PictureView (Context c) {
        super(c);
    }

    @Override
    public boolean onTouchEvent (MotionEvent event)
    {
        System.out.println("touch");
        return false;
    }

}
