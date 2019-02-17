package zsx.com.jjexam.Retrofitnet;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import zsx.com.jjexam.bean.ProBean;

public interface RetrofitServer {
    //搜索
    @GET
    Call<ProBean> GetSearch(@Url String url, @QueryMap HashMap<String,String> map);


}
