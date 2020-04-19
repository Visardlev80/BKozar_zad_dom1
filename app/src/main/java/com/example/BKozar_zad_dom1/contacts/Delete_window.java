package com.example.BKozar_zad_dom1.contacts;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.BKozar_zad_dom1.Contact_Fragment;
import com.example.BKozar_zad_dom1.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Delete_window extends DialogFragment {
    OnDeleteDialogInteractionListener mListener;
    public Delete_window() {
        // Required empty public constructor
    }


    public static Delete_window newInstance(){
        return new Delete_window();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Contact_Fragment.OnListFragmentInteractionListener) {
            mListener = (Delete_window.OnDeleteDialogInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(getString(R.string.delete_question));
        builder.setPositiveButton(getString(R.string.dialog_confirm), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mListener.onDeleteDialogPositiveClick(Delete_window.this);
            }
        });
        builder.setNegativeButton(getString(R.string.dialog_cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mListener.onDeleteDialogNegativeClick(Delete_window.this);
            }
        });
        return builder.create();

    }



    public interface OnDeleteDialogInteractionListener {

        void onDeleteDialogNegativeClick(DialogFragment dialog);

        void onDeleteDialogPositiveClick(DialogFragment dialog);

    }


}


