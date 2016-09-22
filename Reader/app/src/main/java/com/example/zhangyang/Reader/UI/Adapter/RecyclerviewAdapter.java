package com.example.zhangyang.Reader.UI.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zhangyang.Reader.Bean.News;
import com.example.zhangyang.Reader.Interface.OnRecyclerViewItemClickListener;
import com.example.zhangyang.Reader.R;

import java.util.List;

/**
 * Created by zhangyang on 16/8/30.
 */
public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.RecyclerHolder> implements View.OnClickListener{
    private List<News.DataBean.ArticlesBean> beanList;
    private Context context;
    private static final int VIEW_BODY = 1;
    private static final int VIEW_FOOT = 2;
    private View recyclerheader;
    private View footView;
    private int app_id;
    private OnRecyclerViewItemClickListener onItemClickListener;

    public RecyclerviewAdapter(Context context) {
        this.context = context;
    }

    public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (footView != null && viewType == VIEW_FOOT) {
            return new RecyclerHolder(footView);
        }
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        view.setOnClickListener(this);
        return new RecyclerHolder(view);
    }

    public void update(){
        beanList.clear();
    }
    @Override
    public void onClick(View v) {
        if(onItemClickListener!=null){
            onItemClickListener.onItemClick(v, (String) v.getTag());
        }
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }

    public void setFootView(View footView) {
        this.footView = footView;
        notifyItemChanged(beanList.size());//+1
    }

    public void deleteFootView() {
        if (this.footView != null) {
            notifyItemRemoved(beanList.size());//+1
            this.footView=null;
        }
    }

    public void setBeanList(List<News.DataBean.ArticlesBean> beanList) {
//        this.beanList.addAll(beanList);
        this.beanList = beanList;
        notifyDataSetChanged();
    }

    public void addBeanList(List<News.DataBean.ArticlesBean> beanList) {
        this.beanList.addAll(beanList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == beanList.size()) return VIEW_FOOT;//+1
        return VIEW_BODY;
    }

    @Override
    public void onBindViewHolder(RecyclerHolder holder, int position) {
        if (getItemViewType(position) == VIEW_FOOT) return;
//        if (recyclerheader != null) {
//            holder.itemView.setTag(beanList.get(position-1).getPk());
//            Glide.with(context)
//                    .load(beanList.get(position - 1).getThumbnail_pic())
//                    .crossFade().centerCrop()
//                    .into(holder.iv);
//            holder.tv.setText(beanList.get(position - 1).getTitle());
//        }else {
            holder.itemView.setTag(beanList.get(position).getPk());
            Glide.with(context)
                    .load(beanList.get(position).getThumbnail_pic())
                    .crossFade().centerCrop()
                    .into(holder.iv);
            holder.tv.setText(beanList.get(position).getTitle());
//        }
    }

    @Override
    public int getItemCount() {
        if (beanList != null&&footView!=null) {
            return beanList.size()+1;
        }
        if(beanList==null)return 0;
        return beanList.size() ;
    }


    class RecyclerHolder extends RecyclerView.ViewHolder {
        private TextView tv;
        private ImageView iv;

        public RecyclerHolder(View itemView) {
            super(itemView);
            if (footView == itemView) return;
            iv = (ImageView) itemView.findViewById(R.id.lv_iv);
            tv = (TextView) itemView.findViewById(R.id.lv_tv);
        }
    }
}
