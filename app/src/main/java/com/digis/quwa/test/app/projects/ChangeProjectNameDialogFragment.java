package com.digis.quwa.test.app.projects;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.digis.quwa.test.R;

public class ChangeProjectNameDialogFragment extends DialogFragment {

    private ChangeProjectNameDialogFragment() { }

    private ProjectNameListener projectNameListener;

    public static ChangeProjectNameDialogFragment create(ProjectNameListener projectNameListener) {
        ChangeProjectNameDialogFragment dialogFragment = new ChangeProjectNameDialogFragment();
        dialogFragment.setProjectNameListener(projectNameListener);
        return dialogFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_TITLE, R.style.DialogStyle);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_change_project_name, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.tvBtnCancel).setOnClickListener(v -> dismiss());

        EditText etProjectName = view.findViewById(R.id.etProjectName);
        view.findViewById(R.id.tvBtnSave).setOnClickListener(v -> {
            projectNameListener.onNewProjectNameSubmitted(etProjectName.getText().toString());
            dismiss();
        });
    }

    public void setProjectNameListener(ProjectNameListener projectNameListener) {
        this.projectNameListener = projectNameListener;
    }

    public interface ProjectNameListener {
        void onNewProjectNameSubmitted(String newName);
    }
}