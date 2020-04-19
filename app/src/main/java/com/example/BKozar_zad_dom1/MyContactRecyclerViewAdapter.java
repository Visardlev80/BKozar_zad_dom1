package com.example.BKozar_zad_dom1;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.BKozar_zad_dom1.contacts.Contact_List_Content;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Contact_List_Content.Contact} and makes a call to the
 * specified {@link Contact_Fragment.OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyContactRecyclerViewAdapter extends RecyclerView.Adapter<MyContactRecyclerViewAdapter.ViewHolder> {

    private final List<Contact_List_Content.Contact> mValues;
    private final Contact_Fragment.OnListFragmentInteractionListener mListener;

    public MyContactRecyclerViewAdapter(List<Contact_List_Content.Contact> items, Contact_Fragment.OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_contact, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Contact_List_Content.Contact contact = mValues.get(position);
        holder.mItem = contact;
        holder.mContentView.setText(contact.Name);
        final int picPath = contact.picPath;
        Context context = holder.mView.getContext();

        Drawable contactDrawable;
        switch(picPath){
                    case 0:
                        contactDrawable = context.getResources().getDrawable(R.drawable.avatar_1);
                        break;
                    case 1:
                        contactDrawable = context.getResources().getDrawable(R.drawable.avatar_2);
                        break;
                    case 2:
                        contactDrawable = context.getResources().getDrawable(R.drawable.avatar_3);
                        break;
                    case 3:
                        contactDrawable = context.getResources().getDrawable(R.drawable.avatar_4);
                        break;
                    case 4:
                        contactDrawable = context.getResources().getDrawable(R.drawable.avatar_5);
                        break;
                    case 5:
                        contactDrawable = context.getResources().getDrawable(R.drawable.avatar_6);
                        break;
                    case 6:
                        contactDrawable = context.getResources().getDrawable(R.drawable.avatar_7);
                        break;
                    case 7:
                        contactDrawable = context.getResources().getDrawable(R.drawable.avatar_8);
                        break;
                    case 8:
                        contactDrawable = context.getResources().getDrawable(R.drawable.avatar_9);
                        break;
                    default:
                        contactDrawable = context.getResources().getDrawable(R.drawable.avatar_10);
        }
        holder.mItemImageView.setImageDrawable(contactDrawable);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentClickInteraction(holder.mItem, position);

                }
            }
        });
        holder.mView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                // Notify the active callbacks interface (the activity, if the
                // fragment is attached to one) that an item has been selected.
                mListener.onListFragmentLongClickInteraction(position);
                return false;
            }
        });

        holder.mItemImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Notify the active callbacks interface (the activity, if the
                // fragment is attached to one) that an item has been selected.
                mListener.onDeleteClickInteraction(position);

            }
        });




    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mContentView;
        public final ImageView mItemImageView;
        public final ImageButton mItemImageButton;
        public final FloatingActionButton mItemFloatingActionButton;
        public final Button mItemButton;
        public Contact_List_Content.Contact mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mItemImageView = view.findViewById(R.id.item_image);
            mItemImageButton = view.findViewById(R.id.Delete_Button);
            mContentView = view.findViewById(R.id.content);
            mItemFloatingActionButton= view.findViewById(R.id.adder);
            mItemButton = view.findViewById(R.id.addButton);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
