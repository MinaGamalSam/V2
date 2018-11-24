package hsport.com.example.king.yahooweatherv2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by King on 23/11/2018.
 */

public class ForecastAdapter extends ArrayAdapter<Forecast> {
    List<Forecast> mforecasts;

    public ForecastAdapter(@NonNull Context context, List<Forecast> forecasts) {

        super(context, 0, forecasts);
        mforecasts=forecasts;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listView = convertView;
        if (listView == null) {
            listView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_items, parent, false);
        }
        TextView day = listView.findViewById(R.id.day);
        TextView date = listView.findViewById(R.id.date);
        TextView desc = listView.findViewById(R.id.desc);
        TextView high = listView.findViewById(R.id.high);
        TextView low = listView.findViewById(R.id.low);
        ImageView descImage = listView.findViewById(R.id.desc_imageView);

        Forecast currentForecast = mforecasts.get(position);


        day.setText(currentForecast.getDay());
        date.setText(currentForecast.getDate());
        desc.setText(currentForecast.getDescription());
        /*String hV =""+currentForecast.getHigh();
        high.setText(hV);*/
       // low.setText(String.valueOf(currentForecast.getLow()));
        high.setText(""+currentForecast.getHigh());
        low.setText(""+currentForecast.getLow());

        if (desc.getText().equals("Partly Cloudy")) {
            descImage.setImageResource(R.drawable.partly_cloudy_day);
        } else if (desc.getText().equals("Scattered Thunderstorms")) {
            descImage.setImageResource(R.drawable.scattered_showers_day_night);
        } else if (desc.getText().equals("Sunny")) {
            descImage.setImageResource(R.drawable.clear_day);
        } else {
            descImage.setImageResource(R.drawable.clear_day);
        }
        return listView;
    }
}
