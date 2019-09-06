package com.example.nhfintechsproutmarket;

import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nhfintechsproutmarket.CartItemFragment.OnListFragmentInteractionListener;
import com.example.nhfintechsproutmarket.dummy.CartItemContent;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link CartItemContent} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyCartItemRecyclerViewAdapter extends RecyclerView.Adapter<MyCartItemRecyclerViewAdapter.ViewHolder> {

    private final List<CartItemContent.DummyItem> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyCartItemRecyclerViewAdapter(OnListFragmentInteractionListener listener) {
        mValues = new ArrayList<>();
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_cartitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mTitleView.setText(holder.mItem.title);
        byte[] decodedString = Base64.decode(holder.mItem.image, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        holder.mImageView.setImageBitmap(decodedByte);
        holder.mPriceView.setText(holder.mItem.price);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;

        public final TextView mTitleView;
        public final ImageView mImageView;
        public final TextView mPriceView;
        public CartItemContent.DummyItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;

            mTitleView = view.findViewById(R.id.cartitem_title);
            mImageView = view.findViewById(R.id.cartitem_image);
            mPriceView = view.findViewById(R.id.cartitem_price);
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
}
