package com.t9.assistencia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.ArrayList;

public class WednesdayFragment extends Fragment {

    public WednesdayFragment() {
        // Required empty public constructor
    }
    CardView btnBottomSheet;
    ListView listView;
    ArrayList<String> subjects;

    LinearLayout layoutBottomSheet;

    BottomSheetBehavior sheetBehavior;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_sunday_fragment, container, false);

        TinyDB tinydb = new TinyDB(getContext());
        subjects=tinydb.getListString("Subjects");

        layoutBottomSheet=(LinearLayout)rootView.findViewById(R.id.bottom_sheet);
        listView=(ListView)rootView.findViewById(R.id.subjects_in_timetable);
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(rootView.getContext(), android.R.layout.simple_list_item_1, subjects);
        listView.setAdapter(arrayAdapter);

        sheetBehavior = BottomSheetBehavior.from(layoutBottomSheet);
        sheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_HIDDEN:
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED: {

                    }
                    break;
                    case BottomSheetBehavior.STATE_COLLAPSED: {
                        sheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);

                    }
                    break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
        sheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        btnBottomSheet=(CardView) rootView.findViewById(R.id.btn_bottom_sheet);
        btnBottomSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleBottomSheet();
            }
        });
        return rootView;
    }
    public void toggleBottomSheet() {
        if (sheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
            sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

        } else {
            sheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);

        }
    }
}