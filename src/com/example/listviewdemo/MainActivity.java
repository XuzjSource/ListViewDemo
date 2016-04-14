package com.example.listviewdemo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private String[] data = {"apple", "banana", "orange", "pear"
            , "grape", "cherry", "Mango", "banana", "orange", "pear"
            , "grape", "cherry", "Mango"};

    private List<Fruit> fruitList = new ArrayList<Fruit>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFruit();
//      ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,data);
        FruitAdapter adapter = new FruitAdapter(MainActivity.this,R.layout.fruit_item,fruitList);
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("dddd");
                Log.i("app","aaaa");
                Fruit fruit = fruitList.get(position);
                Toast.makeText(MainActivity.this, fruit.getName(),
                Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initFruit() {
        Fruit apple = new Fruit("Apple", R.drawable.apple);
        fruitList.add(apple);
        Fruit banana = new Fruit("Banana", R.drawable.apple);
        fruitList.add(banana);
        Fruit orange = new Fruit("Orange", R.drawable.apple);
        fruitList.add(orange);
        Fruit watermelon = new Fruit("Watermelon", R.drawable.apple);
        fruitList.add(watermelon);
        Fruit pear = new Fruit("Pear", R.drawable.apple);
        fruitList.add(pear);
        Fruit grape = new Fruit("Grape", R.drawable.apple);
        fruitList.add(grape);
        Fruit pineapple = new Fruit("Pineapple", R.drawable.apple);
        fruitList.add(pineapple);
        Fruit strawberry = new Fruit("Strawberry", R.drawable.apple);
        fruitList.add(strawberry);
        Fruit cherry = new Fruit("Cherry", R.drawable.apple);
        fruitList.add(cherry);
        Fruit mango = new Fruit("Mango", R.drawable.apple);
        fruitList.add(mango);
        Fruit apple1 = new Fruit("Apple", R.drawable.apple);
        fruitList.add(apple1);
        Fruit banana1 = new Fruit("Banana", R.drawable.apple);
        fruitList.add(banana1);
        Fruit orange1 = new Fruit("Orange", R.drawable.apple);
        fruitList.add(orange1);
        Fruit watermelon1 = new Fruit("Watermelon", R.drawable.apple);
        fruitList.add(watermelon1);
        Fruit pear1 = new Fruit("Pear", R.drawable.apple);
        fruitList.add(pear1);
        Fruit grape1 = new Fruit("Grape", R.drawable.apple);
        fruitList.add(grape1);
        Fruit pineapple1 = new Fruit("Pineapple", R.drawable.apple);
        fruitList.add(pineapple1);
        Fruit strawberry1 = new Fruit("Strawberry", R.drawable.apple);
        fruitList.add(strawberry1);
        Fruit cherry1 = new Fruit("Cherry", R.drawable.apple);
        fruitList.add(cherry1);
        Fruit mango1 = new Fruit("Mango", R.drawable.apple);
        fruitList.add(mango1);
    }
}
