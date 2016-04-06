package com.lyc.schedulebox.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lyc.schedulebox.R;
import com.lyc.schedulebox.logic.model.MindListModel;

import java.util.List;

/**
 * Created by lianyuchen on 16/4/6.
 */
public class ShowMindListAdapter extends BaseAdapter {

    private Context context;
    private List<MindListModel.ObjEntity.ListEntity> data;

    public ShowMindListAdapter(Context context, List<MindListModel.ObjEntity.ListEntity> data) {
        super();
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 声明一个holder对象
        ViewHolder holder;
        // 判断第二个参数View对象是否为空
        if (null == convertView) {
            // 若为空，将listview中的每一项内容的布局转化为View类型
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.listview_mind_item, null);
            holder = new ViewHolder();
            // 从convertView中找出listview中每一项内容里的组件，这里包括ImageView，两个TextView
//            holder.iv = (ImageView) convertView
//                    .findViewById(R.id.act_show_hotel_msg_iv_pic);
            holder.tvUsername = (TextView) convertView
                    .findViewById(R.id.tv_username);
            holder.tvContent = (TextView) convertView
                    .findViewById(R.id.tv_user_mind);
            holder.tvPubTime = (TextView) convertView
                    .findViewById(R.id.tv_pub_time);
            // 将ViewHolder对象作为convertView的标识，目的不在于作为标识，在于后面从convertView中取出ViewHolder
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        // 为holder的控件绑定数据/////////////////////
        // 获取当前位置的数据
        MindListModel.ObjEntity.ListEntity currentData = data.get(position);
//        holder.iv.setImageResource(R.drawable.img_bg);
        holder.tvUsername.setText(currentData.getUserBean().getUserId() + "");
        holder.tvContent.setText(currentData.getMindContent());
        holder.tvPubTime.setText(currentData.getMindPubTime());
        // //////////////
        // 加载远程图片
//        System.out.println(">>>>----" + currentData.getHotelPic());
//        String imageUrl = imageBaseUrl + "/" + currentData.getHotelPic();
//        System.out.println(imageUrl);
//        Bitmap bitmap = this.asynBitmapTask.loadBitmap(holder.iv, imageUrl,
//                new AsynBitmapTask.ImageCallback() {
//
//                    @Override
//                    public void imageload(ImageView imageView, Bitmap bitmap) {
//                        imageView.setImageBitmap(bitmap);
//                    }
//                });
//
//        if (bitmap != null) {
//            holder.iv.setImageBitmap(bitmap);
//        }
        // 返回值是第二个参数View对象
        return convertView;
    }

    static class ViewHolder {
        // 仅仅需要声明ListView的一个item里面所需要使用的控件
//        ImageView iv;
        TextView tvUsername;
        TextView tvContent;
        TextView tvPubTime;
    }
}
