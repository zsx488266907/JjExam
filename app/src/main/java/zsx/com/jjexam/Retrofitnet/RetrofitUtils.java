package zsx.com.jjexam.Retrofitnet;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import zsx.com.jjexam.api.Api;

public class RetrofitUtils {
    private static RetrofitUtils instanct;
    private final Retrofit retrofit;

    public RetrofitUtils() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }
    public RetrofitServer setRetrofit(){
        return retrofit.create(RetrofitServer.class);
    }
    public static RetrofitUtils getInstanct() {
        if (instanct == null){
            synchronized (RetrofitUtils.class){
                if (instanct == null){
                    instanct = new RetrofitUtils();
                }
            }
        }
        return instanct;
    }
}
