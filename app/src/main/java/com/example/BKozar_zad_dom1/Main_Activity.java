package com.example.BKozar_zad_dom1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import com.example.BKozar_zad_dom1.contacts.Contact_List_Content;
import com.example.BKozar_zad_dom1.contacts.Delete_window;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class Main_Activity extends AppCompatActivity  implements Contact_Fragment.OnListFragmentInteractionListener, Calling_window.OnCallDialogInteractionListener, Delete_window.OnDeleteDialogInteractionListener {

    public static final String contactExtra = "contactExtra";
    private int currentItemPosition = -1;
    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

        FloatingActionButton fab = findViewById(R.id.adder);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.fragment_contact_add);
            }
        });
        }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void startSecondActivity(Contact_List_Content.Contact contact, int position){
        Intent intent = new Intent(this, Contact_Info_Activity.class);
        intent.putExtra(contactExtra,contact);
        startActivity(intent);
    }

    private void displayContactInFragment(Contact_List_Content.Contact contact){
        Contact_Info_Fragment contactInfoFragment = ((Contact_Info_Fragment) getSupportFragmentManager().findFragmentById(R.id.displayFragment));
        if(contactInfoFragment != null){
            contactInfoFragment.displayContact(contact);
        }
    }

    @Override
    public void onListFragmentClickInteraction(Contact_List_Content.Contact contact, int position) {
        Toast.makeText(this,getString(R.string.item_selected_msg) + position,Toast.LENGTH_SHORT).show();

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            displayContactInFragment(contact);
        }else{
            startSecondActivity(contact,position);
        }
    }

    @Override
    public void onListFragmentLongClickInteraction(int position) {
        showCallDialog();
    }

    @SuppressLint("ResourceType")
    public void onDeleteClickInteraction(int position) {

       Toast.makeText(this,getString(R.string.long_click_msg) + position, Toast.LENGTH_SHORT).show();
        showDeleteDialog();
       currentItemPosition = position ;
    }
   @SuppressLint("ResourceType")
    public void addClick(View view) {
        EditText contactNameEditText = findViewById(R.id.ContactName);
        EditText ContactSurnameEditText = findViewById(R.id.ContactSurname);
        EditText ContactBirthdayEditText = findViewById(R.id.ContactBirthday);
        EditText ContactPhoneEditText = findViewById(R.id.ContactPhone);

        String ConName = contactNameEditText.getText().toString();
        String ConSurname = ContactSurnameEditText.getText().toString();
        String ContactBirthday = ContactBirthdayEditText.getText().toString();
        String ContactPhone= ContactPhoneEditText.getText().toString();


        int min = 0;
        int max = 9;
        int selectedImage = (int)(Math.random()*(max - min +1)+min);

            if(ConName.isEmpty()){
                ConName = getString(R.string.default_name);
                return;}
            if(ConSurname.isEmpty()){
                ConSurname = getString(R.string.default_surname);
                return;
            }
            if(!validationDate(ContactBirthday)){
                ContactBirthdayEditText.setError("Use correct date format! (dd.MM.yyyy)");
                return;
            }
            if(!validationNameNumber(ContactPhone)){
                ContactPhoneEditText.setError("Invalid format of number! (9 digits)");
                return;
            }

            Contact_List_Content.addItem(new Contact_List_Content.Contact("Contact." + Contact_List_Content.ITEMS.size() +1,
                    ConName,
                    ConSurname,
                    ContactBirthday,
                    ContactPhone,
                    selectedImage));

        contactNameEditText.setText("");
        ContactSurnameEditText.setText("");
        ContactBirthdayEditText.setText("");
        ContactPhoneEditText.setText("");

        InputMethodManager imm= (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(),0);
        Intent intent = new Intent(this, Main_Activity.class);
       startActivity(intent);
    }

    @Override
    public void onDeleteDialogNegativeClick(DialogFragment dialog){
        View v = findViewById(R.id.Delete_Button);
        if(v != null){
            Snackbar.make(v,getString(R.string.delete_cancel_msg), Snackbar.LENGTH_LONG)
                    .setAction(getString(R.string.retry_msg), new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            showDeleteDialog();
                        }
                    }).show();
        }
    }


    @Override
    public void onDeleteDialogPositiveClick(DialogFragment dialog) {
        if(currentItemPosition != -1 && currentItemPosition < Contact_List_Content.ITEMS.size()){
            Contact_List_Content.removeItem(currentItemPosition);
        }
        Intent intent = new Intent(this, Main_Activity.class);
        startActivity(intent);
    }

    private boolean validationNameNumber(String phone) {
          boolean check=false;
        if(!Pattern.matches("[a-zA-Z]+", phone))
        {
            if(phone.length()==9)
            {
                check = true;
            }
        }
        else
        {
            check=false;
        }
        return check;

    }
    public boolean validationDate(String date)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

        Date testDate = null;


        try
        {
            testDate = sdf.parse(date);
        }

        catch (ParseException e)
        {
            return false;
        }

        if (!sdf.format(testDate).equals(date))
        {
            return false;
        }
        return true;
    }

    private void showCallDialog(){
        Calling_window.newInstance().show(getSupportFragmentManager(),getString(R.string.call_dialog_tag));
    }

    private void showDeleteDialog(){
        Delete_window.newInstance().show(getSupportFragmentManager(),getString(R.string.delete_dialog_tag));
    }
    @Override
    public void onDialogPositiveClick(DialogFragment dialog){

    }
    @Override
    public void onDialogNegativeClick(DialogFragment dialog){

    }
}
