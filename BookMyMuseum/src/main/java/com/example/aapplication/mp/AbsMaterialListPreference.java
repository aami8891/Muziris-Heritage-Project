package com.example.aapplication.mp;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.example.aapplication.R;

import static com.example.aapplication.R.styleable;

abstract class AbsMaterialListPreference<T> extends AbsMaterialTextValuePreference<T> {

    protected CharSequence[] entries;
    protected CharSequence[] entryValues;

    public AbsMaterialListPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AbsMaterialListPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public AbsMaterialListPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onCollectAttributes(AttributeSet attrs) {
        super.onCollectAttributes(attrs);

        TypedArray ta = getContext().obtainStyledAttributes(R.styleable.AbsMaterialListPreference);
        try {
            if (ta.hasValue(R.styleable.AbsMaterialListPreference_mp_entry_descriptions)) {
                entries = ta.getTextArray(R.styleable.AbsMaterialListPreference_mp_entry_descriptions);
            }

            if (ta.hasValue(R.styleable.AbsMaterialListPreference_mp_entry_values)) {
             entryValues = ta.getTextArray(R.styleable.AbsMaterialListPreference_mp_entry_values);

            }
        } finally {
            ta.recycle();
        }

        if (entries == null || entryValues == null) {
            if (entries != null) {
                entryValues = entries;
            } else if (entryValues != null) {
                entries = entryValues;
            } else {
                throw new AssertionError(getContext().getString(R.string.exc_no_entries_to_list_provided));
            }
        }
    }

}
