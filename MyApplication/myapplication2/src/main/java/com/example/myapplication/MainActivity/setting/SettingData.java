package com.example.myapplication.MainActivity.setting;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.MainActivity.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.LinkmanActivity.ParentInfo;

import java.io.File;

import static android.content.ContentValues.TAG;

/**
 * Created by 小妖王 on 2017/2/20.
 */

public class SettingData extends Fragment implements View.OnClickListener{
    private View personfileImage;
    private View nickname;
    private View gender;
    private View signature;
    private View introduction;
    private View region;

    private FragmentManager mFragmentManager;
    private FragmentTransaction mTransaction;

    private Fragment genderFragment;
    private Fragment introFragment;
    private Fragment signaFragment;
    private Fragment regionFragment;
    private Fragment nickNameFragment;

    private TextView personalGender;
    private TextView personalRegion;
    private TextView personalNickName;
    @Nullable
    protected static final int CHOOSE_PICTURE = 0;
    protected static final int TAKE_PICTURE = 1;
    private static final int CROP_SMALL_PICTURE = 2;
    private static final int TAKE_VIDEO = 3;
    protected static Uri tempUri;

    private View view;
    private MainActivity mainActivity;
    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.setting_data,container,false);

        mainActivity=(MainActivity)getActivity();
        personfileImage=view.findViewById(R.id.id_setting_personfile_image);
        nickname=view.findViewById(R.id.setting_nickname);
        gender=view.findViewById(R.id.setting_gender);
        signature=view.findViewById(R.id.id_setting_personalfile_signature);
        introduction=view.findViewById(R.id.id_setting_personalfile_introduction);
        region=view.findViewById(R.id.setting_region);

        personalGender =(TextView)view.findViewById(R.id.id_setting_textview_gender);
        personalRegion=(TextView)view.findViewById(R.id.id_setting_textview_region);
        personalNickName=(TextView)view.findViewById(R.id.id_setting_textview_nickname);

        personfileImage.setOnClickListener(this);
        nickname.setOnClickListener(this);
        gender.setOnClickListener(this);
        signature.setOnClickListener(this);
        introduction.setOnClickListener(this);
        region.setOnClickListener(this);

//        personalGender.post(new Runnable(){
//
//            @Override
//            public void run() {
//                personalGender.se(bitmap);
//            }
//
//        });
        personalGender.setText(ParentInfo.gender);
        personalRegion.setText(ParentInfo.region);
        personalNickName.setText(ParentInfo.nickname);
        return view;
    }


    @Override
    public void onClick(View v) {
        mFragmentManager = getFragmentManager();
        mTransaction = mFragmentManager.beginTransaction();


        switch (v.getId()) {
            //用户头像
            case R.id.id_setting_personfile_image:
                showChoosePicDialog();
                break;

            //昵称
            case R.id.setting_nickname:
                if (nickNameFragment==null){
                    nickNameFragment=new SettingDataNickName();
                }
                mTransaction.replace(R.id.fourth_fragment_content,nickNameFragment);
                mTransaction.addToBackStack(null);
                mTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                Log.i(TAG, "onClick: clickNick!");
                break;

            //性别
            case R.id.setting_gender:
                if (genderFragment==null){
                    genderFragment=new SettingDataGender();
                }
                mTransaction.replace(R.id.fourth_fragment_content,genderFragment);
                mTransaction.addToBackStack(null);
                mTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                Log.i(TAG, "onClick: clickgE!");
                break;

            //个性签名
            case R.id.id_setting_personalfile_signature:
                if (signaFragment==null){
                    signaFragment=new SettingDataSignature();
                }
                mTransaction.replace(R.id.fourth_fragment_content,signaFragment);
                mTransaction.addToBackStack(null);
                mTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                break;

            //个人简介
            case R.id.id_setting_personalfile_introduction:
                if (introFragment==null){
                    introFragment=new SettingDataIntroduction();
                }
                mTransaction.replace(R.id.fourth_fragment_content,introFragment);
                mTransaction.addToBackStack(null);
                mTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                break;

            //所在地区
            case R.id.setting_region:
                if (regionFragment==null){
                    regionFragment=new SettingDataRegion();
                }
                mTransaction.replace(R.id.fourth_fragment_content,regionFragment);
                mTransaction.addToBackStack(null);
                mTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                break;

//            case R.id.:
//                break;
//            case R.id.:
//                break;
//            case R.id.:
//                break;
//            case R.id.:
//                break;
//            case R.id.:
//                break;
//            case R.id.:
//                break;
//            case R.id.:
//                break;
//            case R.id.:
//                break;
        }
        mTransaction.commit();
    }
    /**
     * 显示修改头像的对话框
     */
    protected void showChoosePicDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("设置头像");
        String[] items = { "选择本地照片", "拍照" };
        builder.setNegativeButton("取消", null);
        builder.setItems(items, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case CHOOSE_PICTURE: // 选择本地照片
//                        Intent openAlbumIntent = new Intent(
//                                Intent.ACTION_GET_CONTENT);
                        Intent openAlbumIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        openAlbumIntent.setType("image/*");
//                        startActivityForResult(openAlbumIntent, CHOOSE_PICTURE);
                        startActivityForResult(openAlbumIntent, CHOOSE_PICTURE);

                        break;
                    case TAKE_PICTURE: // 拍照
                        Intent openCameraIntent = new Intent(
                                MediaStore.ACTION_IMAGE_CAPTURE);
                        tempUri = Uri.fromFile(new File(Environment
                                .getExternalStorageDirectory(), "image.jpg"));
                        // 指定照片保存路径（SD卡），image.jpg为一个临时文件，每次拍照后这个图片都会被替换
                        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
                        startActivityForResult(openCameraIntent, TAKE_PICTURE);
                        break;
                }
            }
        });
        builder.create().show();
    }
}
