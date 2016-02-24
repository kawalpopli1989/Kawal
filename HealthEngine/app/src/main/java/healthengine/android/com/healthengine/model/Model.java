package healthengine.android.com.healthengine.model;

/**
 * Created by jsn on 21/1/16.
 */
public class Model<T> {

    private static Model mModel = null;

    MyInter myInter = null;

    private Model() {
    }

    public static Model getInstance() {
        if(mModel==null){
            mModel = new Model();
        }
        return mModel;
    }

    public void registerModel(MyInter myInter) {
        this.myInter = myInter;
    }

    public void onSuccess(T t, String className){



        myInter.success(t);
//        for(MyInter myInter1: observerList){
//            MyInter mMyInter = getInstanceOf(className);
//           if(myInter1.getClass().getName().equals(mMyInter.getClass().getName())){
//               myInter1.success(t);
//               break;
//           }
//        }
    }

    public void onFailed(T t, String className){
//        for(MyInter myInter1: observerList){
//            MyInter mMyInter = getInstanceOf(className);
//
//            if(myInter1.getClass().getName().equals(mMyInter.getClass().getName())){
//                myInter1.failure(t);
//                break;
//            }
//        }

        myInter.failure(t);
    }

//    public void changeToolbarSettings(String title){
////        for(MyInter myInter1: observerList){
////            MyInter mMyInter = getInstanceOf(title);
////
////            if(myInter1.getClass().getName().equals(mMyInter.getClass().getName())){
////                myInter1.changeT(title);
////                break;
////            }
////        }
//
//        myInter.changeToolbarSettings(title);
//
//    }

//    public MyInter<T> getInstanceOf(String name){
//
//        name = name.substring(name.lastIndexOf(".")+1, name.length());
//
//        switch (name){
//            case "LoginActivity":
//                return new LoginActivity<T>();
//
//            case "SignupActivity":
//                return new SignupActivity<T>();
//
//            case "HomeActivity":
//                return new HomeActivity<T>();
//        }
//
//        return null;
//    }


    public  interface MyInter<T> {
        void success(T t);
        void failure(T t);
//        void changeToolbarSettings(String fragmentName);
    }


}
