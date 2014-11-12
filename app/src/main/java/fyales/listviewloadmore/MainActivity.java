package fyales.listviewloadmore;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import fyales.listviewloadmore.adapter.CountryAdapter;

/**
 * 实现分页功能
 * @author fyales
 * @date 2014-11-12
 */
public class MainActivity extends Activity implements AbsListView.OnScrollListener{

    private ListView lv1;
    private View loadMore;
    private int visibleLastIndex = 0;
    private CountryAdapter adapter;

    private List<String> asianList;
    private List<String> europeList;

    String[] asian = {"中国","日本","朝鲜","韩国","沙特阿拉伯","哈萨克斯坦","乌兹别克斯坦","塔吉克斯坦","泰国","马来西亚","新加坡","文莱","印度","斯里兰卡"};
    String[] europe = {"挪威","荷兰","英国"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv1 = (ListView) findViewById(R.id.lv1);
        loadMore = getLayoutInflater().inflate(R.layout.loading,null);
        asianList = new ArrayList<String>();
        europeList = new ArrayList<String>();
        for (int i = 0 ;i <asian.length;i++){
            asianList.add(asian[i]);
        }
        for (int j = 0;j <europe.length;j++){
            europeList.add(europe[j]);
        }
        lv1.addFooterView(loadMore);
        adapter = new CountryAdapter(this,asianList);
        lv1.setAdapter(adapter);
        lv1.setOnScrollListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        int itemLastIndex = asianList.size() - 1;
        int lastIndex = itemLastIndex + 1; //包含最后一个loading项
        if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && visibleLastIndex == lastIndex){
            final ProgressBar bar = (ProgressBar) loadMore.findViewById(R.id.progressBar1);
            bar.setVisibility(View.VISIBLE);
            asianList.addAll(europeList);
            adapter.notifyDataSetChanged();;
            bar.setVisibility(View.GONE);



        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        this.visibleLastIndex = firstVisibleItem + visibleItemCount - 1;
    }
}
