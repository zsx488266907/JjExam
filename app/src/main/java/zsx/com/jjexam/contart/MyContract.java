package zsx.com.jjexam.contart;

import java.util.HashMap;

import zsx.com.jjexam.callback.RequestCallBack;

public interface MyContract {
    public  abstract class Mypresenter{
        public abstract void pro(HashMap<String,String> map,String str,Class cls);

    }

    public interface MyModel{
        void Pro(HashMap<String, String> map, String str, Class cls, RequestCallBack requestCallBack);
    }
    public  interface  MyView{
        public void success(Object proBean);
        public void fail(String str);
    }
}
