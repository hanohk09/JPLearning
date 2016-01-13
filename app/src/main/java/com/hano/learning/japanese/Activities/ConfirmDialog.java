package com.hano.learning.japanese.Activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.View;

import java.util.List;

/**
 * Created by Hano on 12/01/2016.
 */
public abstract class ConfirmDialog extends DialogFragment {
    private String ok = "Confirm";
    private String cancel = "Cancel";
    private String title = "";
    private String message = "";
    private View view;

    public  ConfirmDialog(String title){
        this.title = title;
    }

    public  ConfirmDialog(String title, String message){
        this.title = title;
        this.message = message;
    }

    public ConfirmDialog(String title, String ok, String cancel){
        this.title = title;
        this.ok = ok;
        this.cancel = cancel;
    }

    public ConfirmDialog(String title, String message, String ok, String cancel){
        this.title = title;
        this.message = message;
        this.ok = ok;
        this.cancel = cancel;
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        this.message = tag;
        super.show(manager, tag);
    }

    public void setMessage(String message){
        this.message = message;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onCloseAction(view, true);
                    }
                })
                .setNegativeButton(cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onCloseAction(view, false);
                    }
                }).create();
    }

    protected abstract void onCloseAction(View view, boolean isOK);

    public void setView(View view){
        this.view = view;
    }
}
