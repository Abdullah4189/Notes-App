package com.example.notes_app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddNote#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddNote extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    EditText editText_title;
    EditText editText_desc;
    Button btn_save;
    NotesDatabaseHelper dbHelper;
    ImageView imageView2;

    public AddNote() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddNote.
     */
    // TODO: Rename and change types and number of parameters
    public static AddNote newInstance(String param1, String param2) {
        AddNote fragment = new AddNote();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_note, container, false);
        editText_title = view.findViewById(R.id.edittext_title);
        editText_desc = view.findViewById(R.id.edittext_desc);
        btn_save = view.findViewById(R.id.btn_save);
        imageView2 = view.findViewById(R.id.imageView2);


        imageView2.setOnClickListener(v -> {
            FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_layout, new MainFragment());
            transaction.addToBackStack(null);
            transaction.commit();
        });


        dbHelper = new NotesDatabaseHelper(getContext());

        btn_save.setOnClickListener(v -> {
            String title =  editText_title.getText().toString().trim();
            String desc = editText_desc.getText().toString().trim();

            if (!title.isEmpty() && !desc.isEmpty()) {
                boolean inserted = dbHelper.insertNote(title, desc);
                if (inserted) {
                    Toast.makeText(getContext(), "Note saved", Toast.LENGTH_SHORT).show();
                    editText_title.setText("");
                    editText_desc.setText("");
                } else {
                    Toast.makeText(getContext(), "Error saving note", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getContext(), "Both fields required", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}