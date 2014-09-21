package com.example.android_pratice.myapplication;
import android.app.ListActivity;


import android.os.Bundle;


import android.view.View;


import android.widget.ArrayAdapter;


import android.widget.BaseAdapter;
import android.widget.ListView;


import android.widget.Toast;

import java.util.Arrays;


public class DragableListViewActivity extends ListActivity {


  private Object[] sArray  = {"Item 0", "Item 1", "Item 2", 42, false, "Item 5", "Item 6"};
  private TouchInterceptor mList;

  /** Called when the activity is first created. */


  @Override


  public void onCreate(Bundle savedInstanceState) {


    super.onCreate(savedInstanceState);


    setContentView(R.layout.main);


    ArrayAdapter adp = new ArrayAdapter(this, R.layout.listrow, sArray);


    setListAdapter(adp);

    mList = (TouchInterceptor) getListView();
    mList.setDropListener(mDropListener);


    registerForContextMenu(mList);


  }


  @Override


  protected void onListItemClick(ListView l, View v, int position, long id) {


    String selection = sArray[position].toString();


    Toast.makeText(this, selection, Toast.LENGTH_SHORT).show();


  }




  private TouchInterceptor.DropListener mDropListener =


      new TouchInterceptor.DropListener() {


        public void drop(int from, int to) {


          System.out.println("Droplisten from:"+from+" to:"+to);


//Assuming that item is moved up the list


          int direction = -1;


          int loop_start = from;


          int loop_end = to;


//For instance where the item is dragged down the list


          if(from < to) {


            direction = 1;


          }


          Object target = sArray[from];


          for(int i=loop_start;i!=loop_end;i=i+direction){


            sArray[i] = sArray[i+direction];


          }


          sArray[to] = target;


          System.out.println("Changed array is:"+ Arrays.toString(sArray));


          ((BaseAdapter) mList.getAdapter()).notifyDataSetChanged();


        }


      };




}