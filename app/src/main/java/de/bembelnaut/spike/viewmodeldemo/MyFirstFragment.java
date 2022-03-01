package de.bembelnaut.spike.viewmodeldemo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import de.bembelnaut.viewmodeldemo.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyFirstFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyFirstFragment extends Fragment {

    private TextView frameTextView;

    public MyFirstFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_first, container, false);
        this.frameTextView = view.findViewById(R.id.frameTextView);

        // arguments are delivered in this example by creating the fragment in the main activity
        // if the fragment is created by the template, no arguments are received
        if(getArguments() != null && getArguments().containsKey("initName")) {
            String initName = getArguments().getString("initName");
            frameTextView.setText(initName);
        }

        // get view model and register this fragment as an lifecycle observer
        UserModel userModel = new ViewModelProvider(requireActivity()).get(UserModel.class);
        userModel.getUser().observe(MyFirstFragment.this.getViewLifecycleOwner(), user -> {
            frameTextView.setText(user.getName());
        });

        return view;
    }
}