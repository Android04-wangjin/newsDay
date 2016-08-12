package com.feicui.news.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.feicui.news.R;
import com.feicui.news.bean.TopBean;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/8/8.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context            mContext;
    private ArrayList<TopBean> mList;
    private MyViewHolder       mMyViewHolder;
    private MyItemClickListener mItemClickListener;

    public MyAdapter(Context context, ArrayList<TopBean> list) {
        mContext = context;
        mList = list;
    }

    /**
     * 设置Item点击监听
     * @param listener
     */
    public void setOnItemClickListener(MyItemClickListener listener){
        this.mItemClickListener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_item, parent,false);
        mMyViewHolder = new MyViewHolder(view,mItemClickListener);

        return mMyViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        //设置要显示的内容
        Picasso.with(mContext).load(mList.get(position).getUrlImage()).into(holder.mImageView);
        holder.mTvTitle.setText(mList.get(position).getTitle());
        holder.mTvDigest.setText(mList.get(position).getSubtitle());
        holder.mTvDigest.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);

    }

    @Override
    public int getItemCount() {
        if (mList != null) {
            return mList.size();
        }
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView           mImageView;
        private TextView            mTvTitle;
        private TextView            mTvDigest;
        private MyItemClickListener mListener;

        public MyViewHolder(View itemView,MyItemClickListener listener) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.iv_item);
            mTvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            mTvDigest = (TextView) itemView.findViewById(R.id.tv_digest);
            this.mListener = listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(mListener != null){
                mListener.onItemClick(view,getPosition());
            }
        }
    }

    public interface MyItemClickListener {
        void onItemClick(View view,int postion);
    }
}
