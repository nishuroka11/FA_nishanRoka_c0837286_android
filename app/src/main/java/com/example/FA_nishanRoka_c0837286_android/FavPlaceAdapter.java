package com.example.FA_nishanRoka_c0837286_android;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class FavPlaceAdapter extends ArrayAdapter {
    Context mContext;
    int layoutRes;
    List<FavouritePlace> fvPlc;
    DatabaseHelper mdatabase;
    public FavPlaceAdapter(@NonNull  Context mContext, int layoutRes, List<FavouritePlace> fvPlc, DatabaseHelper mdatabase) {
        super(mContext, layoutRes, fvPlc);
        this.mContext = mContext;
        this.layoutRes = layoutRes;
        this.fvPlc = fvPlc;
        this.mdatabase = mdatabase;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater =LayoutInflater.from(mContext);
        View view = inflater.inflate(layoutRes,null);
        TextView name = view.findViewById(R.id.tv_placename);
        TextView address = view.findViewById(R.id.tv_desc);
        TextView date = view.findViewById(R.id.tv_date);
        TextView visited = view.findViewById(R.id.tv_visit);
        final FavouritePlace place = fvPlc.get(position);
        if (place.isVisited == 1){
            // name.setBackgroundColor(Color.CYAN);
        }
        name.setText(place.getPlaceName());
        address.setText(place.getAddress());
        date.setText(place.getDate());
        if (place.isVisited == 1){
            //view.setBackgroundColor(R.drawable.buttton_default);
            view.setBackgroundResource(R.drawable.cell_bg);
            visited.setTextColor(Color.BLACK);
            visited.setText("VISITED");
        }
        return view;
    }
    private void loadPlaces() {
        Cursor cursor = mdatabase.getAllPlace();
        if (cursor.moveToFirst()) {

            do {
                fvPlc.add(new FavouritePlace(cursor.getInt(0), cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getDouble(4),
                        cursor.getDouble(5),cursor.getInt(6)));


            } while (cursor.moveToNext());

            cursor.close();
        }
    }
    public void setNotifyOnChange() {
        //favouritePlaces = null;
        loadPlaces();
    }
}
