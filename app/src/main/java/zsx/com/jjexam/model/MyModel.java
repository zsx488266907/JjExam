package zsx.com.jjexam.model;

import android.os.Handler;
import android.text.TextUtils;

import com.google.gson.Gson;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import zsx.com.jjexam.Retrofitnet.RetrofitServer;
import zsx.com.jjexam.Retrofitnet.RetrofitUtils;
import zsx.com.jjexam.api.Api;
import zsx.com.jjexam.contart.MyContract;
import zsx.com.jjexam.bean.ProBean;
import zsx.com.jjexam.callback.OkhttpCallBack;
import zsx.com.jjexam.callback.RequestCallBack;
import zsx.com.jjexam.net.OkhttpUtils;


public class MyModel implements MyContract.MyModel {
       Handler handler =  new Handler();
       ProBean proBean;


    /*@Override
    public void Pro(HashMap<String, String> map, String str,Class cls, final RequestCallBack requestCallBack) {
      OkhttpUtils.getmInstanse().doGet(str, map,cls, new OkhttpCallBack() {
            @Override
            public void onFair(String msg) {
                if (requestCallBack!=null){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            requestCallBack.onfair("网不行啊,老铁");
                        }
                    });
                }
            }

            @Override
            public void onsuccess(String result) {
                if (!TextUtils.isEmpty(result)){
                    parResult(result,requestCallBack);
                   // proBean = new Gson().fromJson(result, ProsBean.class);
                }
            }
        });

    }

    private void parResult(final String result, final RequestCallBack requestCallBack) {
        proBean = new Gson().fromJson(result, ProBean.class);

        if (requestCallBack!=null){
            handler.post(new Runnable() {
                @Override
                public void run() {
                    requestCallBack.onsuccess(proBean);
                }
            }) ;

        }

    }*/
    @Override
    public void Pro(HashMap<String, String> map, String str, Class cls, final RequestCallBack requestCallBack) {
        RetrofitServer retrofitServer =RetrofitUtils.getInstanct().setRetrofit();
        Call<ProBean> proBeanCall = retrofitServer.GetSearch(str,map);
        proBeanCall.enqueue(new Callback<ProBean>() {
            @Override
            public void onResponse(Call<ProBean> call, Response<ProBean> response) {
                proBean=response.body();
                requestCallBack.onsuccess(proBean);
            }

            @Override
            public void onFailure(Call<ProBean> call, Throwable t) {

            }
        });
    }
 /*
 @Override
    public void getSerchList(HashMap<String, String> params, final RequestCallBack requestCallBack) {
        UserServicer userServicer = RetrofitUtil.getIntanct().setRetrofit();
        Call<SerchBean> serchBeanCall = userServicer.GetSearch(ProduckApi.SearchApi, params);
        serchBeanCall.enqueue(new Callback<SerchBean>() {
            @Override
            public void onResponse(Call<SerchBean> call, Response<SerchBean> response) {
                SerchBean body = response.body();
                requestCallBack.onSearChsuccess(body);
            }

            @Override
            public void onFailure(Call<SerchBean> call, Throwable t) {

            }
        });
    }
 * */


}
