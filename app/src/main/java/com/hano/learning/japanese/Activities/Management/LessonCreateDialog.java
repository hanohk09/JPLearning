package com.hano.learning.japanese.Activities.Management;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.hano.learning.japanese.R;

import Models.LessonModel;
import Utilities.SQLiteUtils;

/**
 * Created by Hano on 21/12/2015.
 */
public abstract class LessonCreateDialog extends DialogFragment {
    private SQLiteUtils utils;
    private EditText edtLesson;
    private CheckBox cbxIsPublic;
    private View view;
    private LessonModel les;

    public LessonCreateDialog(SQLiteUtils utils){
        this.utils = utils;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        View view = getActivity().getLayoutInflater().inflate(R.layout.lesson_create_layout, null);
        edtLesson = (EditText) view.findViewById(R.id.edtLesson);
        cbxIsPublic = (CheckBox) view.findViewById(R.id.cbxIsPublic);

        builder.setView(view);
        builder.setPositiveButton(R.string.create, null);
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                LessonCreateDialog.this.getDialog().cancel();
            }
        });

        return builder.create();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);

        if (les != null && view != null){
            onCreateLesson(les, view);
            les = null;
        }

        if (edtLesson != null && cbxIsPublic != null){
            edtLesson.setText("");
            cbxIsPublic.setChecked(false);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        final AlertDialog dialog = (AlertDialog)getDialog();
        if(dialog != null) {
            Button positiveButton = (Button) dialog.getButton(Dialog.BUTTON_POSITIVE);
            positiveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!TextUtils.isEmpty(edtLesson.getText().toString())) {
                        les = new LessonModel();
                        les.setName(edtLesson.getText().toString());
                        les.setIsPublic(cbxIsPublic.isChecked());
                        les.setState(0);
                        dismiss();
                    } else {
                        Toast.makeText(getActivity(), "Lesson is required!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    protected abstract void onCreateLesson(LessonModel les, View view);

    public void setView(View view){
        this.view = view;
    }
}
