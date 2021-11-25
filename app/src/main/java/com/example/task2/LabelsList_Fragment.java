package com.example.task2;

import static com.example.task2.Constants.FragmentResultBundle_KEY_ACTION;
import static com.example.task2.Constants.FragmentResultBundle_KEY_ACTION_PARAM;
import static com.example.task2.Constants.FragmentResult_REQUEST_KEY;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class LabelsList_Fragment extends Fragment {

    RecyclerView rv_labels;
    TextView txt_empty_view;
    ArrayList<Label> labelArrayList;
    RecyclerViewAdapter_Labels adapter;

    public LabelsList_Fragment() {
        // Required empty public constructor
        labelArrayList = new ArrayList<>();
    }

    public static LabelsList_Fragment newInstance() {
        LabelsList_Fragment fragment = new LabelsList_Fragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getParentFragmentManager().setFragmentResultListener(FragmentResult_REQUEST_KEY, this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {
                String resultAction = bundle.getString(FragmentResultBundle_KEY_ACTION);
                if ( resultAction.equalsIgnoreCase("ADD") ){
                    Label newLabel = bundle.getParcelable(FragmentResultBundle_KEY_ACTION_PARAM);
                    adapter.addLabel(newLabel);
                    if( rv_labels.getVisibility()== View.GONE) {
                        rv_labels.setVisibility(View.VISIBLE);
                        txt_empty_view.setVisibility(View.GONE);
                    }
                }else if (resultAction.equalsIgnoreCase("DELETE")){
                    boolean shouldDeleteSelectedLabels = bundle.getBoolean(FragmentResultBundle_KEY_ACTION_PARAM);
                    if( shouldDeleteSelectedLabels)
                        adapter.deleteSelectedLabels();
                    if(adapter.itemList.size() == 0) {
                        rv_labels.setVisibility(View.GONE);
                        txt_empty_view.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_labels_list, container, false);
        rv_labels = view.findViewById(R.id.rv_labels);
        txt_empty_view = view.findViewById(R.id.txt_empty_view);

        List<Label> labelList = new ArrayList<>();
        adapter = new RecyclerViewAdapter_Labels(labelList);
        rv_labels.setHasFixedSize(true);
        rv_labels.setLayoutManager(new LinearLayoutManager(view.getContext()));
        rv_labels.setAdapter(adapter);

        return view;
    }

}