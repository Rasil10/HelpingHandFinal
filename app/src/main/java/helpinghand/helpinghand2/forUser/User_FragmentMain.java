package helpinghand.helpinghand2.forUser;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import helpinghand.helpinghand2.R;

/**
 * Created by Rasil10 on 2017-07-04.
 */

public class User_FragmentMain extends Fragment {
    String[] locations = {"lazimpat","baluwatar", "naxal", "kanti", "bhatbhateni", "gongabhu","dhumbarai","balaju",
            "bagmati", "kathmandu", "Khursanitar","gaididhara","maharajgunj","samakhusi","lainchaur","putalisadak","Krishna Shrestha",
            "Shyam Chandra","Yoman Chaudhary","Hari Eam","Ram Eam","Bam Lama","Tiplin Lama","Gita Pandey","Ram Pandit","Kathmandu",
            "Cleaners","Electricians","Laptop Repairer","Plumber","chabahil","boudha","sukedhara","gausala","kalanki","balkhu","kalimati","sitapaila","samakhusi","balaju","thamel","jawalakhel","kumaripati","pulchowk","lagankhel","koteshwor","baneshwor","tinkune","sankhamul","chobar","kirtipur","dakshinkali","dillibazar","singhadurbar","baitha maharjan","prakash poudel","gyanendra thakuri","hari bahadur","harry pandit","rishi dhamala"

    };
    ListView list;
    View viewid,view1;
    LinearLayout list2, list3, list4,nearProfesional;
    AutoCompleteTextView actv;
    SharedPreferences preferences;
    private ListView mListView, mListView2;
    private DatabaseReference mDatabase;
    private ImageView imageView;
    private EditText editText;
    ArrayList<Professional> professionals = new ArrayList<Professional>();
    ArrayList<Professional> professionals2 = new ArrayList<Professional>();

    LinearLayout linearLayout_top, linearLayout_bottom;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.content_user__main, container, false);
        actv = (AutoCompleteTextView) rootView.findViewById(R.id.search_box);
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.select_dialog_item, locations);
        actv.setThreshold(1);
        actv.setAdapter(adapter);
        list = (ListView) rootView.findViewById(R.id.list);
        list2 = (LinearLayout) rootView.findViewById(R.id.list2);
        list3 = (LinearLayout) rootView.findViewById(R.id.list3);
        nearProfesional= (LinearLayout) rootView.findViewById(R.id.nearProfesional);
        // list4 = (LinearLayout) rootView.findViewById(R.id.list4);

        preferences = this.getActivity().getSharedPreferences("loginInfo", 0);


        final String registeredUser_address = preferences.getString("UserAddress", "");

        // linearLayout_top = (LinearLayout) rootView.findViewById(R.id.linearlayout_top);
        //linearLayout_bottom = (LinearLayout) rootView.findViewById(R.id.linearlayout_bottom);

        mListView = (ListView) rootView.findViewById(R.id.list);
        imageView = (ImageView) rootView.findViewById(R.id.search_image_view);


        imageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                rootView.findViewById(R.id.viewid).setVisibility(View.GONE);
                //      rootView.findViewById(R.id.view1).setVisibility(View.GONE);
                rootView.findViewById(R.id.topProfesional).setVisibility(View.GONE);



                list.setVisibility(View.VISIBLE);
                list2.setVisibility(View.GONE);
                list3.setVisibility(View.GONE);
                nearProfesional.setVisibility(View.GONE);
                //     list4.setVisibility(View.GONE);
//                view1.setVisibility(View.GONE);
//                viewid.setVisibility(View.GONE);

                list.setScrollContainer(false);




                professionals.clear();
                //rootView.findViewById(R.id.loadingPanel).setVisibility(View.VISIBLE);
                editText = (EditText) rootView.findViewById(R.id.search_box);
                final String inputAddress = editText.getText().toString();
                if (inputAddress.equals(null)) {
                    Toast.makeText(getActivity(), "Search Field is Empty..", Toast.LENGTH_LONG).show();
                } else {

                    String tag[] = {"Cleaners", "Electricians", "Laptop Repairer", "Plumber"};
                    for (int i = 0; i <= 3; i++) {
                        mDatabase = FirebaseDatabase.getInstance().getReference().child(tag[i]);


                        final ChildEventListener childEventListener = mDatabase.addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                                if (dataSnapshot.exists()) {
                                    Professional professional = new Professional(dataSnapshot.child("id").getValue().toString(),
                                            dataSnapshot.child("name").getValue().toString(),
                                            dataSnapshot.child("phone").getValue().toString(),
                                            dataSnapshot.child("address").getValue().toString(),
                                            dataSnapshot.child("address2").getValue().toString(),
                                            dataSnapshot.child("address3").getValue().toString(),
                                            dataSnapshot.child("address4").getValue().toString(),
                                            dataSnapshot.child("longitude").getValue().toString(),
                                            dataSnapshot.child("latitude").getValue().toString(),
                                            dataSnapshot.child("rate").getValue().toString(),
                                            dataSnapshot.child("rating").getValue().toString(),
                                            dataSnapshot.child("category").getValue().toString(),
                                            dataSnapshot.child("district").getValue().toString(),
                                            dataSnapshot.child("zone").getValue().toString(),
                                            dataSnapshot.child("fburl").getValue().toString(),
                                            dataSnapshot.child("voter").getValue().toString()
                                    );
                                    if ((inputAddress.equalsIgnoreCase(dataSnapshot.child("address").getValue().toString()))||
                                            (inputAddress.equalsIgnoreCase(dataSnapshot.child("address2").getValue().toString()))||
                                            (inputAddress.equalsIgnoreCase(dataSnapshot.child("address3").getValue().toString()))||
                                            (inputAddress.equalsIgnoreCase(dataSnapshot.child("address4").getValue().toString()))||
                                            (inputAddress.equalsIgnoreCase(dataSnapshot.child("name").getValue().toString()))||
                                            (inputAddress.equalsIgnoreCase(dataSnapshot.child("zone").getValue().toString()))||
                                            (inputAddress.equalsIgnoreCase(dataSnapshot.child("district").getValue().toString()))||
                                            (inputAddress.equalsIgnoreCase(dataSnapshot.child("category").getValue().toString())))
                                        professionals.add(professional);
                                    //rootView.findViewById(R.id.loadingPanel).setVisibility(View.GONE);

                                }

                                if (professionals.size() == 0) {

                                } else {
                                    ProfessionalAdapter adapter = new ProfessionalAdapter(getActivity(), professionals);
                                    // rootView.findViewById(R.id.loadingPanel).setVisibility(View.GONE);

                                    list.setAdapter(adapter);
                                    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                            Intent i = new Intent(getActivity(), Detail_Activity.class);
                                            Professional pp = new Professional();
                                            pp = professionals.get(position);
                                            i.putExtra("ProfessionalName", pp.getName());
                                            i.putExtra("ProfessionalAddress", pp.getAddress());
                                            i.putExtra("ProfessionalNumber", pp.getNumber());
                                            i.putExtra("ProfessionalLongitude", pp.getLongitude());
                                            i.putExtra("ProfessionalLatitude", pp.getLatitude());
                                            i.putExtra("ProfessionalRate", pp.getRate());
                                            i.putExtra("ProfessionalRating", pp.getRating());



                                            startActivity(i);
                                        }
                                    });
                                }

                            }


                            @Override
                            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                            }

                            @Override
                            public void onChildRemoved(DataSnapshot dataSnapshot) {

                            }

                            @Override
                            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });

                    }
                }

            }
        });



        LinearLayout searchByCategory = (LinearLayout) rootView.findViewById(R.id.search_by_category_LL);
        searchByCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.app.FragmentManager fm = getFragmentManager();
                fm.beginTransaction().replace(R.id.main_frame, new SearchByCategory()).commit();

            }
        });

        LinearLayout searchByRating = (LinearLayout) rootView.findViewById(R.id.search_by_rating_LL);
        searchByRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.app.FragmentManager fm = getFragmentManager();
                fm.beginTransaction().replace(R.id.main_frame, new SearchByRating()).commit();

            }
        });
        LinearLayout searchByLocationy_IV = (LinearLayout) rootView.findViewById(R.id.search_by_location_LL);
        searchByLocationy_IV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.app.FragmentManager fm = getFragmentManager();
                fm.beginTransaction().replace(R.id.main_frame, new SearchByLocation()).commit();
            }
        });
        LinearLayout searchByNearestLocation_IV = (LinearLayout) rootView.findViewById(R.id.search_by_nearestlocation_LL);
        searchByNearestLocation_IV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), SearchByNearest.class);
                startActivity(i);
            }
        });


        mListView = (ListView) rootView.findViewById(R.id.topProfesionallist);
        mListView2 = (ListView) rootView.findViewById(R.id.nearProfesionallist);
        String tag[] = {"Cleaners", "Electricians", "Laptop Repairer", "Plumber"};
        for (int i = 0; i <= 3; i++) {
            mDatabase = FirebaseDatabase.getInstance().getReference().child(tag[i]);
            final ChildEventListener childEventListener = mDatabase.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                    if (dataSnapshot.exists()) {
                        ratingloop:
                        if ((Integer.parseInt(dataSnapshot.child("rating").getValue().toString())) == 5) {
                            Professional professional = new Professional(
                                    dataSnapshot.child("id").getValue().toString(),
                                    dataSnapshot.child("name").getValue().toString(),
                                    dataSnapshot.child("phone").getValue().toString(),
                                    dataSnapshot.child("address").getValue().toString(),
                                    dataSnapshot.child("address2").getValue().toString(),
                                    dataSnapshot.child("address3").getValue().toString(),
                                    dataSnapshot.child("address4").getValue().toString(),
                                    dataSnapshot.child("longitude").getValue().toString(),
                                    dataSnapshot.child("latitude").getValue().toString(),
                                    dataSnapshot.child("rate").getValue().toString(),
                                    dataSnapshot.child("rating").getValue().toString(),
                                    dataSnapshot.child("category").getValue().toString(),
                                    dataSnapshot.child("district").getValue().toString(),
                                    dataSnapshot.child("zone").getValue().toString(),
                                    dataSnapshot.child("fburl").getValue().toString(),
                                    dataSnapshot.child("voter").getValue().toString()
                            );

                            professionals.add(professional);
                            break ratingloop;


                        }
                        nearloop:
                        if ((registeredUser_address.equalsIgnoreCase(dataSnapshot.child("address").getValue().toString())) ||
                                (registeredUser_address.equalsIgnoreCase(dataSnapshot.child("address2").getValue().toString())) ||
                                (registeredUser_address.equalsIgnoreCase(dataSnapshot.child("address3").getValue().toString())) ||
                                (registeredUser_address.equalsIgnoreCase(dataSnapshot.child("address4").getValue().toString()))
                                ) {

                            Professional professional = new Professional(
                                    dataSnapshot.child("id").getValue().toString(),
                                    dataSnapshot.child("name").getValue().toString(),
                                    dataSnapshot.child("phone").getValue().toString(),
                                    dataSnapshot.child("address").getValue().toString(),
                                    dataSnapshot.child("address2").getValue().toString(),
                                    dataSnapshot.child("address3").getValue().toString(),
                                    dataSnapshot.child("address4").getValue().toString(),
                                    dataSnapshot.child("longitude").getValue().toString(),
                                    dataSnapshot.child("latitude").getValue().toString(),
                                    dataSnapshot.child("rate").getValue().toString(),
                                    dataSnapshot.child("rating").getValue().toString(),
                                    dataSnapshot.child("category").getValue().toString(),
                                    dataSnapshot.child("district").getValue().toString(),
                                    dataSnapshot.child("zone").getValue().toString(),
                                    dataSnapshot.child("fburl").getValue().toString(),
                                    dataSnapshot.child("voter").getValue().toString()
                            );
                            professionals2.add(professional);
                            break nearloop;


                        }

                        if (getActivity()!=null){
                            ProfessionalAdapter adapter = new ProfessionalAdapter(getActivity(), professionals);
                            mListView.setAdapter(adapter);
                            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                    Intent i = new Intent(getActivity(), Detail_Activity.class);
                                    Professional pp = new Professional();
                                    pp = professionals.get(position);
                                    i.putExtra("ProfessionalId", pp.getId());
                                    i.putExtra("ProfessionalName", pp.getName());
                                    i.putExtra("ProfessionalAddress", pp.getAddress());
                                    i.putExtra("ProfessionalNumber", pp.getNumber());
                                    i.putExtra("ProfessionalLongitude", pp.getLongitude());
                                    i.putExtra("ProfessionalLatitude", pp.getLatitude());
                                    i.putExtra("ProfessionalRate", pp.getRate());
                                    i.putExtra("ProfessionalRating", pp.getRating());
                                    i.putExtra("ProfessionalCategory", pp.getCategory());
                                    i.putExtra("ProfessionalFburl", pp.getFburl());
                                    i.putExtra("ProfessionalVoter", pp.getVoter());

                                    startActivity(i);
                                }
                            });
                        }


                        if (getActivity()!=null){
                            ProfessionalAdapter adapter2 = new ProfessionalAdapter(getActivity(), professionals2);
                            mListView2.setAdapter(adapter2);
                            mListView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                    Intent i = new Intent(getActivity(), Detail_Activity.class);
                                    Professional pp = new Professional();
                                    pp = professionals.get(position);
                                    i.putExtra("ProfessionalId", pp.getId());
                                    i.putExtra("ProfessionalName", pp.getName());
                                    i.putExtra("ProfessionalAddress", pp.getAddress());
                                    i.putExtra("ProfessionalNumber", pp.getNumber());
                                    i.putExtra("ProfessionalLongitude", pp.getLongitude());
                                    i.putExtra("ProfessionalLatitude", pp.getLatitude());
                                    i.putExtra("ProfessionalRate", pp.getRate());
                                    i.putExtra("ProfessionalRating", pp.getRating());
                                    i.putExtra("ProfessionalCategory", pp.getCategory());
                                    i.putExtra("ProfessionalFburl", pp.getFburl());
                                    i.putExtra("ProfessionalVoter", pp.getVoter());

                                    startActivity(i);
                                }
                            });
                        }



                    }


                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }


        return rootView;
    }


}
