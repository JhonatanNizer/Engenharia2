package com.example.nizer01.goplay.utility;

import mobi.upod.timedurationpicker.TimeDurationPickerDialogFragment;
import mobi.upod.timedurationpicker.TimeDurationPicker;

public class PickerDialogFragment extends TimeDurationPickerDialogFragment {

    private long duration;

    @Override
    protected long getInitialDuration() {
        return 15 * 60 * 1000;
    }

    @Override
    protected int setTimeUnits() {
        return TimeDurationPicker.HH_MM;
    }

    @Override
    public void onDurationSet(TimeDurationPicker view, long duration) {
        this.duration = duration;
    }

    public long getDuration(){
        return this.duration;
    }

}
