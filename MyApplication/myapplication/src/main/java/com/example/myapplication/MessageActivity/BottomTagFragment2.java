//package com.example.myapplication.MessageActivity;
//
//import android.support.v4.app.Fragment;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.RadioButton;
//import android.widget.RadioGroup;
//
//import com.example.myapplication.LinkmanActivity.LinkmanFragment;
//import com.example.myapplication.MainActivity.MainActivity;
//import com.example.myapplication.R;
//
///**
// * Created by 小妖王 on 2017/3/19.
// */
//public class BottomTagFragment2 extends Fragment implements RadioGroup.OnCheckedChangeListener{
//    private RadioGroup radioGroup;
//
//    public RadioButton getSecond_rad() {
//        return second_rad;
//    }
//
//    private RadioButton second_rad;//底部标签第一个按钮
//    //    private String[] mainFragments = {"FirstFragment", "SecondFragment", "ThirdFragment", "FourthFragment"};
//    private String[] mainFragments = {"FirstFragment","FourthFragment"};
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.bottomtag_fragment,container,false);
//        radioGroup = (RadioGroup) view.findViewById(R.id.radiogroup);
//        radioGroup.setOnCheckedChangeListener(this);
//
//        second_rad = (RadioButton) view.findViewById(R.id.second);
//        second_rad.setChecked(true);
//        return view;
//    }
//
////    private void changeFragment(String tag) {
////        FragmentManager manager = getFragmentManager();
////        FragmentTransaction transaction = manager.beginTransaction();
////        Fragment fragment = manager.findFragmentByTag(tag);
////        if (fragment == null) {
////            try {
////                //fragment = (Fragment) Class.forName(tag).newInstance();
////                switch (tag) {
////                    case "FirstFragment":
////                        fragment = new FirstFragment();
////                        break;
//////                    case "SecondFragment":
//////                       fragment = new SecondFragment();
//////                        break;
//////                    case "ThirdFragment":
//////                        fragment = new ThirdFragment();
//////                        break;
////                    case "FourthFragment":
////                        fragment = new FourthFragment();
////                        break;
////                }
////            } catch (Exception e) {
////            }
////            transaction.add(R.id.fragments, fragment, tag);
////        } else {
////            transaction.show(fragment);
////        }
////        for (int i = 0; i < 2; i++) {
////            Fragment tempF = manager.findFragmentByTag(mainFragments[i]);
////            if (tempF != null && !tempF.getTag().equals(tag)) {
////                transaction.hide(tempF);
////            }
////        }
////        transaction.commit();
////    }
//
//    @Override
//    public void onCheckedChanged(RadioGroup group, int checkedId) {
//        // TODO Auto-generated method stub
//
//        switch (checkedId) {
//            case R.id.first:
//                //这里开启查看界面
//                openBottomTag(1,MainActivity.class);
//                break;
//
//            case R.id.second:
//                //do nothing
//                break;
//
//            case R.id.third:
//                openBottomTag(3, LinkmanFragment.class);
//                break;
//
//            case R.id.fourth:
//                //这里开启设置界面
//                openBottomTag(4,MainActivity.class);
//                break;
//
//        }
//    }
//
//    public void openBottomTag(int bottomTagNo, Class<?> activityClass){
//        Intent intent = new Intent(getActivity(),activityClass);
//        Bundle bundle=new Bundle();
//        bundle.putInt("bottomNo", bottomTagNo);
//        intent.putExtras(bundle);
//        startActivity(intent);
//    }
//
//
//}
//
