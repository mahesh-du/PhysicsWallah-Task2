package com.example.task2;

import static com.example.task2.Constants.FragmentResultBundle_KEY_ACTION;
import static com.example.task2.Constants.FragmentResultBundle_KEY_ACTION_PARAM;
import static com.example.task2.Constants.FragmentResult_REQUEST_KEY;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LabelActions_Fragment extends Fragment {

    TextInputLayout til_label_name;
    TextInputEditText tiet_label_name;
    MaterialButton mbtn_add;
    MaterialButton mbtn_delete;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_label_actions, container, false);
        tiet_label_name = view.findViewById(R.id.tiet_label_name);
        til_label_name = view.findViewById(R.id.til_label_name);
        mbtn_add = view.findViewById(R.id.mbtn_add);
        mbtn_delete = view.findViewById(R.id.mbtn_delete);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mbtn_add.setOnClickListener(view1 -> {
            String name = tiet_label_name.getText().toString();
            Label label = new Label(name);

            Bundle result = new Bundle();
            result.putString(FragmentResultBundle_KEY_ACTION, "ADD");
            result.putParcelable(FragmentResultBundle_KEY_ACTION_PARAM, label);
            getParentFragmentManager().setFragmentResult(FragmentResult_REQUEST_KEY, result);
        });
        mbtn_delete.setOnClickListener(view1 -> {
            Bundle result = new Bundle();
            result.putString(FragmentResultBundle_KEY_ACTION, "DELETE");
            result.putBoolean(FragmentResultBundle_KEY_ACTION_PARAM, true);
            getParentFragmentManager().setFragmentResult(FragmentResult_REQUEST_KEY, result);
        });
    }
}
