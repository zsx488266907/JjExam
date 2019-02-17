package zsx.com.jjexam.presenter;

import java.util.HashMap;

import zsx.com.jjexam.contart.MyContract;
import zsx.com.jjexam.callback.RequestCallBack;
import zsx.com.jjexam.model.MyModel;


public class Mypresenter extends MyContract.Mypresenter {
  private MyModel myModel;
  private MyContract.MyView myView;



    public Mypresenter(MyContract.MyView myView) {
       myModel=  new MyModel();
        this.myView = myView;
    }

    @Override
    public void pro(HashMap<String, String> map,String str,Class cls) {
           myModel.Pro(map, str,cls, new RequestCallBack() {
               @Override
               public void onfair(String msg) {
                    myView.fail(msg);
               }

               @Override
               public void onsuccess(Object proBean) {
                  myView.success(proBean);
               }
           });
    }
    public void destroy(){
        if (myView!=null){
            myView = null;
        }

    }
}
