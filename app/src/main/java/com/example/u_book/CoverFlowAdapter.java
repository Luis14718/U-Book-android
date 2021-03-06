package com.example.u_book;

import android.app.Dialog;
import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CoverFlowAdapter extends BaseAdapter {

    private ArrayList<Game> data;
    private AppCompatActivity activity;

    public CoverFlowAdapter(AppCompatActivity context, ArrayList<Game> objects) {
        this.activity = context;
        this.data = objects;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Game getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_flow_view, null, false);

            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.gameImage.setImageResource(data.get(position).getImageSource());
        viewHolder.gameName.setText(data.get(position).getName());

        convertView.setOnClickListener(onClickListener(position));

        return convertView;
    }

    private View.OnClickListener onClickListener(final int position) {
        return new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(activity);
                dialog.setContentView(R.layout.dialog_game_info);
                dialog.setCancelable(true); // dimiss when touching outside
                dialog.setTitle("Game Details");

                TextView text = (TextView) dialog.findViewById(R.id.name);
                text.setText(getItem(position).getName());
                ImageView image = (ImageView) dialog.findViewById(R.id.image);
                image.setImageResource(getItem(position).getImageSource());
                TextView description= (TextView) dialog.findViewById(R.id.description);
                description.setText(getItem(position).getDescription());
                TextView availability = (TextView) dialog.findViewById(R.id.availability);
                Button button = (Button)  dialog.findViewById(R.id.btncaller);

                if (getItem(position).getAvailability()<=0){
                    availability.setText("Is available");
                    button.setVisibility(View.VISIBLE);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            dialog.setContentView(R.layout.activity_order);
                            dialog.setCancelable(true);

                            TextView text = (TextView) dialog.findViewById(R.id.name2);
                            text.setText(getItem(position).getName());
                            ImageView image = (ImageView) dialog.findViewById(R.id.image2);
                            image.setImageResource(getItem(position).getImageSource());
                            TextView description =(TextView)dialog.findViewById(R.id.description2);
                            description.setText(getItem(position).getDescription());
                            // data.set(position,new  Game(getItem(position).getImageSource(),getItem(position).getName(),getItem(position).getDescription(),1));
                            Button button2= (Button) dialog.findViewById(R.id.done);
                            button2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    data.set(position,new  Game(getItem(position).getImageSource(),getItem(position).getName(),getItem(position).getDescription(),1,getItem(position).getCategories()));
                                    dialog.cancel();




                                }
                            });

                            dialog.show();






                        }

                    });




                }else {
                    availability.setText("It is not available");
                    button.setVisibility(View.INVISIBLE);
                }






                dialog.show();
            }




        };
    }






    private static class ViewHolder {
        private TextView gameName;
        private ImageView gameImage;

        public ViewHolder(View v) {
            gameImage = (ImageView) v.findViewById(R.id.image);
            gameName = (TextView) v.findViewById(R.id.name);
        }
    }
}
