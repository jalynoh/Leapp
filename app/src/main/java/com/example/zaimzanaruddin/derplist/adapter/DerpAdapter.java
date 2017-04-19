package com.example.zaimzanaruddin.derplist.adapter;



import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import android.content.Context;
import android.view.ViewGroup;
import java.util.ArrayList;


import com.example.zaimzanaruddin.derplist.model.ListItem;
import com.example.zaimzanaruddin.derplist.R;


/**
 * Created by zaimzanaruddin on 4/16/17.
 */

public class DerpAdapter extends RecyclerView.Adapter<DerpAdapter.DerpHolder> {

    private List<ListItem> listData;
    private LayoutInflater inflater;

    private ItemClickCallback itemClickCallback;


    public interface ItemClickCallback {
        void onSecondaryIconClick(int p);
        void onItemClick(int p);
    }


    public void setItemClickCallback(final ItemClickCallback itemClickCallback) {
        this.itemClickCallback = itemClickCallback;

    }


    public DerpAdapter(List<ListItem> listData, Context c) {
        inflater = LayoutInflater.from(c);
        this.listData = listData;

    }


    @Override
    public DerpHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.card_item, parent, false);
        return new DerpHolder(view);
    }

    @Override
    public void onBindViewHolder(DerpHolder holder, int position) {
        ListItem item = listData.get(position);
        holder.title.setText(item.getTitle());
        holder.location.setText(item.getlocation());
        holder.time.setText(item.getTime());
        holder.thumbnail.setImageURI(Uri.parse(item.getImageResId())); //NEW ADDITION !!!!!!
    }

    public void setListData(ArrayList<ListItem> exerciseList) {
        this.listData.clear();
        this.listData.addAll(exerciseList);
    }


    @Override
    public int getItemCount() {
        return listData.size();
    }


    class DerpHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView thumbnail;
        private ImageView secondaryIcon;
        private ImageView thirdIcon;

        private TextView title;
        private TextView location;
       // private TextView date;
        private TextView time;
                View container;


        public DerpHolder(View itemView) {
            super(itemView);

            thumbnail = (ImageView) itemView.findViewById(R.id.im_event_icon);
            secondaryIcon = (ImageView) itemView.findViewById(R.id.im_uparrow);
            secondaryIcon.setOnClickListener(this);
            thirdIcon = (ImageView) itemView.findViewById(R.id.im_item_icon_downarrow);
            thirdIcon.setOnClickListener(this);
            title = (TextView) itemView.findViewById(R.id.lbl_event_title);
           // date = (TextView) itemView.findViewById(R.id.im_date);
            time = (TextView) itemView.findViewById(R.id.im_time);
            location = (TextView) itemView.findViewById(R.id.lbl_location_title);
            container = itemView.findViewById(R.id.cont_item_root);
            container.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
                if (v.getId() == R.id.cont_item_root) {
                    itemClickCallback.onItemClick(getAdapterPosition());
                } else {
                 //   itemClickCallback.onSecondaryIconClick(getAdapterPosition());
                }
            }
        }
    }

