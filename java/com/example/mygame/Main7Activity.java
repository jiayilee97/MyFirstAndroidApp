package com.example.mygame;

import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

public class Main7Activity extends AppCompatActivity {

    RecyclerView recyclerView3;
    AppUtility appUtilityRecycle3;
    ListAdapterWithRecycleView listAdapterWithRecycleView3;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);

        // Toolbar and FAB
/*        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        // ListView1
        // ArrayAdapter to create an array of views (one view for each string)
        String[] stringlist
                = getApplicationContext().getResources().getStringArray(R.array.stringlist);
        ListView listView = findViewById(R.id.listview);
        ArrayAdapter namesAdapter
                = new ArrayAdapter<String>(this,R.layout.simple_list_item_1,stringlist);
        listView.setAdapter(namesAdapter);

        // ListView2
        // Modified BaseAdapter usage
        ListView listView2=(ListView)findViewById(R.id.listview2);
        AppUtility appUtility=AppUtility.getAppUtility(getApplicationContext());
        ListAdapterPeople listAdapterPeople
                =new ListAdapterPeople(this,appUtility.getPeople());
        listView2.setAdapter(listAdapterPeople);

        // RecyclerView
        RecyclerView recyclerView =(RecyclerView) findViewById(R.id.recycleListView);
        AppUtility appUtilityRecycle=AppUtility.getAppUtility(getApplicationContext());
        ListAdapterWithRecycleView listAdapterWithRecycleView
                =new ListAdapterWithRecycleView(this,appUtility.getPeople());
        GridLayoutManager layoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(listAdapterWithRecycleView);


        // RecyclerView2
        RecyclerView recyclerView2 =(RecyclerView) findViewById(R.id.recycleListView2);
        AppUtility appUtilityRecycle2=AppUtility.getAppUtility(getApplicationContext());
        ListAdapterWithRecycleView listAdapterWithRecycleView2
                =new ListAdapterWithRecycleView(this,appUtility.getPeople());
        LinearLayoutManager layoutManager2
                =new LinearLayoutManager(this, LinearLayout.HORIZONTAL,false);
        recyclerView2.setLayoutManager(layoutManager2);
        recyclerView2.setAdapter(listAdapterWithRecycleView2);

        // RecyclerView3
        recyclerView3 =(RecyclerView) findViewById(R.id.recycleListView3);
        appUtilityRecycle3=AppUtility.getAppUtility(getApplicationContext());
        listAdapterWithRecycleView3
                =new ListAdapterWithRecycleView(this,appUtilityRecycle3.getPeople());
        GridLayoutManager layoutManager3
                =new GridLayoutManager(this,2, RecyclerView.VERTICAL,false);
        layoutManager3.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return (position%3 == 0 ? 2 : 1);
            }
        });
        recyclerView3.setLayoutManager(layoutManager3);
        recyclerView3.setAdapter(listAdapterWithRecycleView3);



    }

    public void onClick(View v){
        switch(v.getId()){
            case R.id.staggerbtn:
                createStaggerView();
                break;
        }
    }
    public void createStaggerView(){
        // StaggeredRecyclerView
        StaggeredGridLayoutManager staggeredGridLayoutManager
                = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);

        recyclerView3.setLayoutManager(staggeredGridLayoutManager);
        recyclerView3.setAdapter(listAdapterWithRecycleView3);

    }

}
