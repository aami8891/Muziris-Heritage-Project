package com.example.chmarax.logregform;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class CompleteArtifacts extends Fragment {

    public CompleteArtifacts() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_complete_artifacts, container, false);
        Button btinside = v.findViewById(R.id.btinside);
        btinside.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                CompleteArtifacts2 fragment2 = new CompleteArtifacts2();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container, fragment2);
                fragmentTransaction.commit();


            }
        });

        return  v;
    }
}
