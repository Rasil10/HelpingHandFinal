package helpinghand.helpinghand2.forUser;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

import helpinghand.helpinghand2.Booking;
import helpinghand.helpinghand2.R;

/**
 * Created by Rasil10 on 2017-08-07.
 */

public class NotificationAdapter extends ArrayAdapter<Booking> {
    Context context;
    private UserBookingAdapter adapter;
    private DatabaseReference mDatabase;
    final ArrayList<Booking> bookings = new ArrayList<Booking>();


    public NotificationAdapter(Activity context, ArrayList<Booking> words) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, words);
        this.context = context;
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.notification_list_view, parent, false);
        }

        TextView dateTextView = (TextView) listItemView.findViewById(R.id.date);
        TextView timeTextView = (TextView) listItemView.findViewById(R.id.time);
        TextView hourTextView = (TextView) listItemView.findViewById(R.id.hour);



        // Get the {@link AndroidFlavor} object located at this position in the list
        final Booking currentuserBooking = getItem(position);

        TextView notification_message = (TextView) listItemView.findViewById(R.id.notification);
        notification_message.setText("Your Booking has been Accepted by "+currentuserBooking.getProfessionalname());

        dateTextView.setText(currentuserBooking.getDate());
        timeTextView.setText(currentuserBooking.getTime());
        hourTextView.setText(currentuserBooking.getHour());

        final String bookingid = currentuserBooking.getId();



        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }


}


