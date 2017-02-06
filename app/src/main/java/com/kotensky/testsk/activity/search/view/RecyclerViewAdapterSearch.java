package com.kotensky.testsk.activity.search.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kotensky.testsk.R;
import com.kotensky.testsk.rest.data.search.ItemSearch;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stas on 06.02.2017.
 */

public class RecyclerViewAdapterSearch extends RecyclerView.Adapter <RecyclerViewAdapterSearch.ViewHolderSearch> {

    private List<ItemSearch> itemSearchList = new ArrayList<>();
    private Context mContext;

    public void setItemSearchList (List<ItemSearch> itemSearchList){
        this.itemSearchList = itemSearchList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolderSearch onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search, parent, false);
        mContext = parent.getContext();
        return new ViewHolderSearch (v);
    }

    @Override
    public void onBindViewHolder(ViewHolderSearch holder, int position) {
        ItemSearch itemSearch = itemSearchList.get(position);
        Picasso.with(mContext).load(itemSearch.getOwner().getAvatarUrl()).into(holder.ownerImg);
        holder.name.setText(itemSearch.getName());
        holder.description.setText(itemSearch.getDescription());
    }

    @Override
    public int getItemCount() {
        return itemSearchList.size();
    }

    class ViewHolderSearch extends RecyclerView.ViewHolder{

        private TextView name;
        private TextView description;
        private ImageView ownerImg;

        public ViewHolderSearch(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.textName);
            description = (TextView) itemView.findViewById(R.id.textDesc);
            ownerImg = (ImageView) itemView.findViewById(R.id.owner_img);

        }
    }
}
