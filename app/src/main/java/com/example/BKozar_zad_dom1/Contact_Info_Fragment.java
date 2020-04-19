package com.example.BKozar_zad_dom1;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.BKozar_zad_dom1.contacts.Contact_List_Content;


/**
 * A simple {@link Fragment} subclass.
 */
public class Contact_Info_Fragment extends Fragment {

    public Contact_Info_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact_info, container, false);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        Intent intent = getActivity().getIntent();
        if(intent !=null){
            Contact_List_Content.Contact receivedContact = intent.getParcelableExtra(Main_Activity.contactExtra);
            if(receivedContact != null){
                displayContact(receivedContact);
            }
        }
    }
    public void displayContact(Contact_List_Content.Contact contact) {
        FragmentActivity activity = getActivity();
        TextView ContactInfoName = activity.findViewById(R.id.ContactName);
        TextView ContactInfoPhone = activity.findViewById(R.id.ContactInfoPhone);
        TextView ContactInfoBirthday = activity.findViewById(R.id.ContactInfoBirthday);
        ImageView ContactInfoImage = activity.findViewById(R.id.ContactImage);

        ContactInfoName.setText(contact.Name+" " +contact.Surname);
        ContactInfoPhone.setText("Phone Number: "+contact.Phone);
        ContactInfoBirthday.setText("Birthday: "+contact.Birthday);
        Drawable contactDrawable;
        switch(contact.picPath){
            case 0:
                contactDrawable = activity.getResources().getDrawable(R.drawable.avatar_1);
                break;
            case 1:
                contactDrawable = activity.getResources().getDrawable(R.drawable.avatar_2);
                break;
            case 2:
                contactDrawable = activity.getResources().getDrawable(R.drawable.avatar_3);
                break;
            case 3:
                contactDrawable =activity.getResources().getDrawable(R.drawable.avatar_4);
                break;
            case 4:
                contactDrawable =activity.getResources().getDrawable(R.drawable.avatar_5);
                break;
            case 5:
                contactDrawable = activity.getResources().getDrawable(R.drawable.avatar_6);
                break;
            case 6:
                contactDrawable = activity.getResources().getDrawable(R.drawable.avatar_7);
                break;
            case 7:
                contactDrawable = activity.getResources().getDrawable(R.drawable.avatar_8);
                break;
            case 8:
                contactDrawable = activity.getResources().getDrawable(R.drawable.avatar_9);
                break;
            default:
                contactDrawable = activity.getResources().getDrawable(R.drawable.avatar_10);
        }
        ContactInfoImage.setImageDrawable(contactDrawable);
    }

}
