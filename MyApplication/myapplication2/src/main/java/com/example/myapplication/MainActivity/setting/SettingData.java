package com.example.myapplication.MainActivity.setting;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.MainActivity.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.Utils.ImageUtils;
import com.example.myapplication.Utils.ParentInfo;
import com.example.myapplication.lookOver.NormalImageLoader;

import java.io.ByteArrayOutputStream;
import java.io.File;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static android.app.Activity.RESULT_OK;
import static android.content.ContentValues.TAG;

/**
 * Created by 小妖王 on 2017/2/20.
 */

public class SettingData extends Fragment implements View.OnClickListener{
    private ImageView personfileImage;
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
        personfileImage=(ImageView)view.findViewById(R.id.id_setting_personfile_image);
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
        if (ParentInfo.getImageURL() != null) {
            new NormalImageLoader().getPicture(getResources().getString(R.string.testBaseURL) + ParentInfo.getImageURL(), personfileImage);
        }
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) { // 如果返回码是可以用的
            switch (requestCode) {
                case TAKE_PICTURE:
                    startPhotoZoom(tempUri); // 开始对图片进行裁剪处理
                    break;
                case CHOOSE_PICTURE:
                    startPhotoZoom(data.getData()); // 开始对图片进行裁剪处理
                    break;
                case CROP_SMALL_PICTURE:
                    if (data != null) {
                        setImageToView(data); // 让刚才选择裁剪得到的图片显示在界面上
                    }
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
    protected void startPhotoZoom(Uri uri) {
        if (uri == null) {
            Log.i("tag", "The uri is not exist.");
        }
        tempUri = uri;
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CROP_SMALL_PICTURE);
    }

    /**
     * 保存裁剪之后的图片数据
     *
     */
    protected void setImageToView(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            photo = ImageUtils.toRoundBitmap(photo, tempUri); // 这个时候的图片已经被处理成圆形的了
            personfileImage.setImageBitmap(photo);
            uploadPic(photo);
        }
    }


    private void uploadPic(Bitmap bitmap) {
        // 上传至服务器
        // ... 可以在这里把Bitmap转换成file，然后得到file的url，做文件上传操作
        // 注意这里得到的图片已经是圆形图片了
        // bitmap是没有做个圆形处理的，但已经被裁剪了

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        //iv_photo.setDrawingCacheEnabled(true);
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);

        RequestBody body = RequestBody.create(MediaType.parse("*.png"), outputStream.toByteArray());
        // 拿着imagePath上传了
        // ...
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.testBaseURL))
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        ImageUploadServes imageUploadServes = retrofit.create(ImageUploadServes.class);

        Call<String> call = imageUploadServes.uploadImage(ParentInfo.getId()+"",body);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.e("uploader image", "success");
                ParentInfo.setImageURL(response.body().toString());
//                UserUtils.setParam(getApplicationContext(),user);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.i("uploader iamge:=","fail");
            }
        });

    }

}
