package com.example.aapplication.mp.io;

import android.content.Context;

import java.util.Set;


public interface UserInputModule {

    void showEditTextInput(
            String key,
            CharSequence title,
            CharSequence defaultValue,
            Listener<String> listener);

    void showSingleChoiceInput(
            String key,
            CharSequence title,
            CharSequence[] displayItems,
            CharSequence[] values,
            int selected,
            Listener<String> listener);

    void showMultiChoiceInput(
            String key,
            CharSequence title,
            CharSequence[] displayItems,
            CharSequence[] values,
            boolean[] defaultSelection,
            Listener<Set<String>> listener);

    void showColorSelectionInput(
            String key,
            CharSequence title,
            int defaultColor,
            Listener<Integer> color);

    interface Factory {
        UserInputModule create(Context context);
    }

    interface Listener<T> {
        void onInput(T value);
    }
}
