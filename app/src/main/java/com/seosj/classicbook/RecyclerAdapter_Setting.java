package com.seosj.classicbook;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerAdapter_Setting extends RecyclerView.Adapter<RecyclerAdapter_Setting.ItemViewHolder> {

    // adapter에 들어갈 list 입니다.
    private ArrayList<Data_Recycle_Setting> listData1 = new ArrayList<>();

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // LayoutInflater를 이용하여 전 단계에서 만들었던 item.xml을 inflate 시킵니다.
        // return 인자는 ViewHolder 입니다.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_settings, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        // Item을 하나, 하나 보여주는(bind 되는) 함수입니다.
        holder.onBind(listData1.get(position));
    }

    @Override
    public int getItemCount() {
        // RecyclerView의 총 개수 입니다.
        return listData1.size();
    }

    void addItem(Data_Recycle_Setting data) {
        // 외부에서 item을 추가시킬 함수입니다.
        listData1.add(data);
    }
    // RecyclerView의 핵심인 ViewHolder 입니다.
    // 여기서 subView를 setting 해줍니다.
    class ItemViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView1;
        private TextView textView1;
        private TextView textView2;
        private ImageView imageView2;

        ItemViewHolder(View itemView) {
            super(itemView);

            imageView1 = itemView.findViewById(R.id.image_setting_menu);
            textView1 = itemView.findViewById(R.id.text_setting_menu);
            textView2 = itemView.findViewById(R.id.text_setting_userid);
            imageView2 = itemView.findViewById(R.id.image_setting_arrow);
        }

        void onBind(Data_Recycle_Setting data) {
            imageView1.setImageResource(data.getResId1());
            textView1.setText(data.getTitle());
            textView2.setText(data.getUser_id());
            imageView2.setImageResource(data.getResId2());
        }
    }

}