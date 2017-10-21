package com.citytogo.jonnyhsia.rxevangelist.page.main;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.citytogo.jonnyhsia.rxevangelist.R;
import com.citytogo.jonnyhsia.rxevangelist.model.entity.Category;

import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by JonnyHsia on 17/10/21.
 * Category Item View Binder
 */
public class CategoryItemBinder extends ItemViewBinder<Category, CategoryItemBinder.VH> {

    private OnCategoryClickListener mOnCategoryClickListener;

    @NonNull
    @Override
    protected VH onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_category, parent, false);
        return new VH(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull final VH holder, @NonNull Category item) {
        holder.tvCategory.setText(item.getTitle());
        holder.imgCategory.setBackgroundResource(item.getBackgroundRes());
        Glide.with(holder.itemView)
                .load(item.getForegroundRes())
                .into(holder.imgCategory);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnCategoryClickListener != null) {
                    mOnCategoryClickListener.onClicked(holder.getAdapterPosition());
                }
            }
        });
    }

    static class VH extends RecyclerView.ViewHolder {
        ImageView imgCategory;
        TextView tvCategory;

        VH(View itemView) {
            super(itemView);

            imgCategory = itemView.findViewById(R.id.img_category);
            tvCategory = itemView.findViewById(R.id.tv_category);
        }
    }

    public OnCategoryClickListener getOnCategoryClickListener() {
        return mOnCategoryClickListener;
    }

    public void setOnCategoryClickListener(OnCategoryClickListener onCategoryClickListener) {
        mOnCategoryClickListener = onCategoryClickListener;
    }

    interface OnCategoryClickListener {

        void onClicked(int pos);
    }
}
