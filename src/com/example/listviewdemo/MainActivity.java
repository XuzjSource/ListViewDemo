package com.example.listviewdemo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnScrollListener{
    private String[] data = {"apple", "banana", "orange", "pear"
            , "grape", "cherry", "Mango", "banana", "orange", "pear"
            , "grape", "cherry", "Mango"};

    private List<Fruit> fruitList = new ArrayList<Fruit>();
    private View loadMoreView;  
    private Button loadMoreButton;  
    private int visibleLastIndex = 0;   //最后的可视项索引  
    private int visibleItemCount;       // 当前窗口可见项总数  
    private int datasize = 100;          //模拟数据集的条
    private Handler handler = new Handler(); 
    FruitAdapter adapter;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFruit();
       
        loadMoreView = getLayoutInflater().inflate(R.layout.loadmore, null);
        loadMoreButton = (Button)loadMoreView.findViewById(R.id.loadMoreButton);
        loadMoreButton.setOnClickListener(new View.OnClickListener() {
             
            @Override
            public void onClick(View v) {
                loadMoreButton.setText("正在加载中...");   //设置按钮文字
                handler.postDelayed(new Runnable() {
                     
                    @Override
                    public void run() {
                        loadMoreData();
                        adapter.notifyDataSetChanged();
                        loadMoreButton.setText("查看更多...");  //恢复按钮文字
                    }
                },2000);
                 
            }
        }); 
        
        
        
//      ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,data);
        adapter = new FruitAdapter(MainActivity.this,R.layout.fruit_item,fruitList);
        listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        
        listView.addFooterView(loadMoreView);    //设置列表底部视图
        listView.setAdapter(adapter);
        listView.setOnScrollListener(this);
        
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fruit fruit = fruitList.get(position);
                Toast.makeText(MainActivity.this, fruit.getName(),
                Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 加载更多数据
     */
    private void loadMoreData(){
        int count = adapter.getCount();
         
        if(count+10 <= datasize){
            for(int i=count+1; i<=count+10; i++){
            	Fruit item = new Fruit();
                item.setName("Title"+i);
                item.setImageId(R.drawable.apple);
                fruitList.add(item);
            }
        }else{
            for(int i=count+1; i<=datasize; i++){
            	Fruit item = new Fruit();
                item.setName("Title"+i);
                item.setImageId(R.drawable.apple);
                fruitList.add(item);
            }
        }
         
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
     /*   Fruit grape = new Fruit("Grape", R.drawable.apple);
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
        fruitList.add(mango1);*/
    }

	@Override
	public void onScroll(AbsListView arg0, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
		 this.visibleItemCount = visibleItemCount;
	        visibleLastIndex = firstVisibleItem + visibleItemCount - 1;
	         
	        Log.e("========================= ","========================");
	        Log.e("firstVisibleItem = ",firstVisibleItem+"");
	        Log.e("visibleItemCount = ",visibleItemCount+"");
	        Log.e("totalItemCount = ",totalItemCount+"");
	        Log.e("========================= ","========================");
	         
	        //如果所有的记录选项等于数据集的条数，则移除列表底部视图
	        if(totalItemCount == datasize+1){
	            listView.removeFooterView(loadMoreView);
	            Toast.makeText(this, "数据全部加载完!", Toast.LENGTH_LONG).show();
	        }
		
	}

	@Override
	public void onScrollStateChanged(AbsListView arg0, int scrollState) {
		// TODO Auto-generated method stub
		int itemsLastIndex = adapter.getCount()-1;  //数据集最后一项的索引  
        int lastIndex = itemsLastIndex + 1;
        if (scrollState == OnScrollListener.SCROLL_STATE_IDLE
                && visibleLastIndex == lastIndex) {
            // 如果是自动加载,可以在这里放置异步加载数据的代码
        }
	}
}
