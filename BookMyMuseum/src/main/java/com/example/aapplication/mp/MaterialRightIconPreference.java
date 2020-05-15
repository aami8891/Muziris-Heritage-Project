package com.example.aapplication.mp;

import android.content.Context;
import android.util.AttributeSet;

import com.example.aapplication.R;

public class MaterialRightIconPreference extends AbsMaterialPreference<Void> {

    public MaterialRightIconPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MaterialRightIconPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MaterialRightIconPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public Void getValue() {
        return null;
    }

    @Override
    public void setValue(Void value) { }

    @Override
    protected int getLayout() {
        return R.layout.view_right_icon_preference;
    }

}
