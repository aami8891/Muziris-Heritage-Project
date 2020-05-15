package com.example.aapplication.mp;

import android.content.Context;
import android.util.AttributeSet;

import com.example.aapplication.R;


public class MaterialSwitchPreference extends AbsMaterialCheckablePreference {

    public MaterialSwitchPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MaterialSwitchPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MaterialSwitchPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected int getLayout() {
        return R.layout.view_switch_preference;
    }
}
