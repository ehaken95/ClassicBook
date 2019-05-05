package com.seosj.classicbook;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RecycleAdapter_Search extends RecyclerView.Adapter<RecycleAdapter_Search.ItemViewHolder>{

    // adapter에 들어갈 list 입니다.
    private ArrayList<Data_Recycle_Search> listData = new ArrayList<>();

    @NonNull
    @Override
    public RecycleAdapter_Search.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // LayoutInflater를 이용하여 전 단계에서 만들었던 item.xml을 inflate 시킵니다.
        // return 인자는 ViewHolder 입니다.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_search_book, parent, false);
        return new RecycleAdapter_Search.ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleAdapter_Search.ItemViewHolder holder, int position) {
        // Item을 하나, 하나 보여주는(bind 되는) 함수입니다.
        holder.onBind(listData.get(position));
    }

    @Override
    public int getItemCount() {
        // RecyclerView의 총 개수 입니다.
        return listData.size();
    }

    void addItem(Data_Recycle_Search data) {
        // 외부에서 item을 추가시킬 함수입니다.
        listData.add(data);
    }

    // RecyclerView의 핵심인 ViewHolder 입니다.
    // 여기서 subView를 setting 해줍니다.
    class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView textView_loc;
        private TextView textView_num;
        private TextView textView_date;
        private TextView textView_state;

        ItemViewHolder(View itemView) {
            super(itemView);

            textView_loc = itemView.findViewById(R.id.textView_menu3_search2);
            textView_num = itemView.findViewById(R.id.textView_menu3_search4);
            textView_date = itemView.findViewById(R.id.textView_menu3_search5);
            textView_state = itemView.findViewById(R.id.textView_menu3_search6);

        }

        void onBind(Data_Recycle_Search data) {
            textView_loc.setText(data.getLoc());
            textView_num.setText(data.getNum());
            textView_date.setText(data.getUntil_date());
            textView_state.setText(data.getIsrent());
        }
    }
}
