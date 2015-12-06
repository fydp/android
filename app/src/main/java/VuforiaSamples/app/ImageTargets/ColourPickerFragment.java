package VuforiaSamples.app.ImageTargets;

import android.app.Fragment;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.larswerkman.holocolorpicker.ColorPicker;
import com.qualcomm.vuforia.samples.VuforiaSamples.R;

public class ColourPickerFragment extends Fragment implements ColorPicker.OnColorChangedListener {
    ColorPicker mColorPicker;
    CountDownTimer mTimer;
    com.qualcomm.vuforia.samples.VuforiaSamples.app.ImageTargets.ImageTargets mActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mActivity = (com.qualcomm.vuforia.samples.VuforiaSamples.app.ImageTargets.ImageTargets)getActivity();
        final View layout = inflater.inflate(R.layout.colour_picker, container, false);
        mColorPicker = (ColorPicker)layout.findViewById(R.id.picker);

        int existingColour = getArguments().getInt(com.qualcomm.vuforia.samples.VuforiaSamples.app.ImageTargets.ImageTargets.COLOUR_ARG);
        mColorPicker.setColor(existingColour);
        mColorPicker.setOldCenterColor(existingColour);
        mColorPicker.setOnColorChangedListener(this);
        mTimer = new CountDownTimer(1500, 1500) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                mActivity.onColourSelectionTimeout();
            }
        };
        mTimer.start();
        return layout;
    }

    @Override
    public void onColorChanged(int color) {
        mTimer.cancel();
        mColorPicker.setOldCenterColor(color);
        mActivity.onColourChanged(color);
        mTimer.start();
    }
}
