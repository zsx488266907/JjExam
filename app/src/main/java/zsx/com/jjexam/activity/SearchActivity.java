package zsx.com.jjexam.activity;

import android.content.Intent;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import zsx.com.jjexam.MainActivity;
import zsx.com.jjexam.R;
import zsx.com.jjexam.adapter.SearchAdapter;
import zsx.com.jjexam.api.Api;
import zsx.com.jjexam.bean.ProBean;
import zsx.com.jjexam.contart.MyContract;
import zsx.com.jjexam.presenter.Mypresenter;

public class SearchActivity extends AppCompatActivity implements MyContract.MyView {


    int page=1;
    @BindView(R.id.edit)
    EditText edit1;
    @BindView(R.id.button1)
    Button button;
    @BindView(R.id.xrv)

    XRecyclerView xRecyclerView;
Mypresenter mypresenter;
    private SearchAdapter searchAdapter;


    private HashMap<String, String> map;
    private String s;
    private String s2,s3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
        initData();
        hh();
    }


        private void hh() {
        xRecyclerView.setPullRefreshEnabled(true);
        xRecyclerView.setLoadingMoreEnabled(true);
    //    xRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page=1;
                Toast.makeText(SearchActivity.this,"刷新...."+page, Toast.LENGTH_SHORT).show();
                xrecy();
                xRecyclerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                Toast.makeText(SearchActivity.this,"加载...."+page, Toast.LENGTH_SHORT).show();
                xrecy();
                xRecyclerView.loadMoreComplete();
            }
        });

        page=1;
        xrecy();
    }


    private void xrecy() {

        map = new HashMap<>();
        s3 = Api.SEARCH_URL+"&&keyword="+ "鞋" +"&&page="+page;
        mypresenter.pro(map, s3,ProBean.class);
       // mypresenter.pro(map, s2,ProBean.class);
       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               s = edit1.getText().toString();
               s2 = Api.SEARCH_URL+"&&keyword="+ s +"&&page="+page;
               mypresenter.pro(map, s2,ProBean.class);
           }
       });

    }

    private void initData() {
        searchAdapter = new SearchAdapter(this);
       xRecyclerView.setAdapter(searchAdapter);
       searchAdapter.setItemListener(new SearchAdapter.ItemListener() {
           @Override
           public void onItemClickListener(int posi, View view) {

               EventBus.getDefault().post(posi);
               Toast.makeText(SearchActivity.this,"传值"+posi,Toast.LENGTH_SHORT).show();
               startActivity(new Intent(SearchActivity.this,PosiActivity.class));

           }

           @Override
           public void onItemLongClickListener(int posi, View view) {
               Toast.makeText(SearchActivity.this,"别急"+posi,Toast.LENGTH_SHORT).show();
               //
           }
       });

    }

    private void initView() {
        getSupportActionBar().hide();
        ButterKnife.bind(this);

        mypresenter= new Mypresenter(this);
      xRecyclerView.setLayoutManager(new GridLayoutManager(this,2));

    }

    @Override
    public void success(Object proBean) {


        if (proBean instanceof ProBean){
            ProBean proBean1 = (ProBean) proBean;
            searchAdapter.setList( proBean1.result);

        }

        //page++;
        xRecyclerView.refreshComplete();
        xRecyclerView.loadMoreComplete();
    }

    @Override
    public void fail(String str) {
        Toast.makeText(SearchActivity.this,"失败了",Toast.LENGTH_SHORT).show();
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        mypresenter.destroy();
    }


}
