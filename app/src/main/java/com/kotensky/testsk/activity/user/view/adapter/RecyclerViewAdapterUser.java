package com.kotensky.testsk.activity.user.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kotensky.testsk.R;
import com.kotensky.testsk.activity.user.model.data.RepoUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stas on 06.02.2017.
 */

public class RecyclerViewAdapterUser extends RecyclerView.Adapter<RecyclerViewAdapterUser.ViewHolderUser> {


    private List<RepoUser> itemUserList = new ArrayList<>();

    public void setItemUserList(List<RepoUser> itemSearchList) {
        this.itemUserList = itemSearchList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolderUser onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_repo, parent, false);

        return new ViewHolderUser(v);
    }

    @Override
    public void onBindViewHolder(ViewHolderUser holder, int position) {
        RepoUser repoUser = itemUserList.get(position);
        holder.name.setText(repoUser.getName());
        holder.description.setText(repoUser.getDescription());
    }

    @Override
    public int getItemCount() {
        return itemUserList.size();
    }

    class ViewHolderUser extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView description;

        public ViewHolderUser(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.textViewItemUserName);
            description = (TextView) itemView.findViewById(R.id.textViewItemUserDesc);

        }
    }
}