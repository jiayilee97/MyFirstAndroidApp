package com.example.mygame;

import android.content.Context;
import android.os.Build;
import android.util.ArraySet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import androidx.annotation.RequiresApi;

import java.util.List;

public class ListAdapterPeople extends BaseAdapter {

    String TAG = "ListAdapterPeople";
    List<Person> people;
    Context context;
    LayoutInflater layoutInflater;
    ArraySet<View> viewSet;

    @RequiresApi(api = Build.VERSION_CODES.M)
    public ListAdapterPeople(Context context, List<Person> people){
        this.context=context;
        this.people=people;
        viewSet=new ArraySet<View>();
    }

    @Override
    public int getCount() {
        return people.size();
    }

    @Override
    public Object getItem(int position) {
        return people.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        PersonViewHolder personViewHolder;
        if (view == null ) {
            layoutInflater = LayoutInflater.from(this.context);
            view=layoutInflater.inflate(R.layout.layout_person_row_item,null);
            personViewHolder = new PersonViewHolder();
            personViewHolder.textViewName = (TextView)view.findViewById(R.id.textViewName);
            personViewHolder.textViewLastName=(TextView)view.findViewById(R.id.textViewLastName);
            personViewHolder.textViewGender=(TextView)view.findViewById(R.id.textViewGender);
            personViewHolder.textViewNationality=(TextView)view.findViewById(R.id.textViewNationality);

            view.setTag(personViewHolder);
        } else {
            personViewHolder = (PersonViewHolder) view.getTag();
        }
        final Person person = people.get(position);

//        ((TextView)view.findViewById(R.id.textViewName)).setText(person.getName());
//        ((TextView)view.findViewById(R.id.textViewLastName)).setText(person.getLastName());
//        ((TextView)view.findViewById(R.id.textViewGender)).setText((person.getGender()== Person.GENDER.MALE? "Male":"Female"));
//        ((TextView)view.findViewById(R.id.textViewNationality)).setText(person.getNationality());

        personViewHolder.textViewName.setText(person.getName());
        personViewHolder.textViewLastName.setText(person.getLastName());
        personViewHolder.textViewGender.setText((person.getGender()== Person.GENDER.MALE? "Male":"Female"));
        personViewHolder.textViewNationality.setText(person.getNationality());


        viewSet.add(view);
        Log.i(TAG,"Index: "+position+" : "+view +" viewSize: "+viewSet.size());
        return view;
    }

    private static class PersonViewHolder{
        public TextView textViewName;
        public TextView textViewLastName;
        public TextView textViewGender;
        public TextView textViewNationality;
    }
}
