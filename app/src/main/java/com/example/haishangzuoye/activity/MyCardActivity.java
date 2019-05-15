package com.example.haishangzuoye.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.haishangzuoye.R;
import com.example.haishangzuoye.Utils.Api;
import com.example.haishangzuoye.Utils.SharedPreferencesUtils;

import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 我的名片
 */
public class MyCardActivity extends BaseActivity {

    @BindView(R.id.head_img)
    ImageView headImg;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.nickname)
    TextView nickname;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.sex)
    TextView sex;
    @BindView(R.id.age)
    TextView age;
    @BindView(R.id.org)
    TextView org;
    private static int REQUEST_CAMERA = 1;
    private static int IMAGE_REQUEST_CODE = 2;
    private File file;
    private String paths;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_card);
        ButterKnife.bind(this);
        setToolBar();
        getData();

        if (SharedPreferencesUtils.getLoggedStatus(this)) {
            if (!TextUtils.isEmpty(SharedPreferencesUtils.getImage(this))) {
                Bitmap bitmap = BitmapFactory.decodeFile(SharedPreferencesUtils.getImage(this));
                headImg.setImageBitmap(bitmap);
            }
        }

        headImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SharedPreferencesUtils.getLoggedStatus(MyCardActivity.this)) {
                    String[] items = {"拍照", "相册选取"};
                    new AlertDialog.Builder(MyCardActivity.this)
                        .setIcon(R.mipmap.ic_launcher)
                        .setTitle("请选择")
                        .setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (i == 0) {
                                    applyWritePermission();
                                } else {
                                    getPhoto();
                                }
                            }
                        }).show();
                } else {
                    startActivity(new Intent(MyCardActivity.this, LoginActivity.class));
                }
            }
        });
    }

    private void getData() {
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://wxooxw.com:8180/sea/seaController/") // 设置 网络请求 Url
            .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
            .build();
        Api api = retrofit.create(Api.class);
        Map<String, String> params = new HashMap<>();
        params.put("userId", SharedPreferencesUtils.getUserId(this));
        String vars = new JSONObject(params).toString();
        Call<ResponseBody> call = api.editUserData(vars);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String str = new String(response.body().bytes());

                    JSONObject jsonObject = new JSONObject(str);
                    int status = jsonObject.getInt("status");
                    if (status == 1) {
                        jsonObject = jsonObject.optJSONObject("result");
                        org.setText("工作单位：" + jsonObject.getString("workAddress"));
                        phone.setText("联系方式：" + jsonObject.getString("realPhone"));
                        age.setText("年龄：" + jsonObject.getString("age"));
                        sex.setText("性别：" + jsonObject.getString("sex"));
                        name.setText("真实姓名：" + jsonObject.getString("realName"));
                        nickname.setText("昵称：" + jsonObject.getString("nickName"));
                    } else {
                        Toast.makeText(MyCardActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    private void getPhoto() {
        //在这里跳转到手机系统相册里面
        Intent intent = new Intent(Intent.ACTION_PICK,
            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, IMAGE_REQUEST_CODE);
    }

    private void useCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()
            + "/test/" + System.currentTimeMillis() + ".jpg");
        file.getParentFile().mkdirs();
        //改变Uri  com.xykj.customview.fileprovider注意和xml中的一致
        Uri uri = FileProvider.getUriForFile(this, "com.example.haishangzuoye.fileprovider", file);
        //添加权限
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    private void setToolBar() {
        ActionBar actionBar = getSupportActionBar();
        if (null != actionBar) {
            actionBar.setTitle("我的名片");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public void applyWritePermission() {
        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (Build.VERSION.SDK_INT >= 23) {
            int check = ContextCompat.checkSelfPermission(this, permissions[0]);
            // 权限是否已经 授权 GRANTED---授权 DINIED---拒绝
            if (check == PackageManager.PERMISSION_GRANTED) {
                useCamera();
            } else {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        } else {
            useCamera();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            useCamera();
        } else {
            // 没有获取 到权限，从新请求，或者关闭app
            Toast.makeText(this, "需要存储权限", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CAMERA && resultCode == RESULT_OK) {
            Log.e("TAG", "---------" + FileProvider.getUriForFile(this, "com.example.haishangzuoye.fileprovider", file));
            headImg.setImageBitmap(BitmapFactory.decodeFile(file.getAbsolutePath()));
            //在手机相册中显示刚拍摄的图片
            SharedPreferencesUtils.setImage(this, file.getAbsolutePath());
            Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            Uri contentUri = Uri.fromFile(file);
            mediaScanIntent.setData(contentUri);
            sendBroadcast(mediaScanIntent);
        }
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK) {
            try {
                Uri selectedImage = data.getData(); //获取系统返回的照片的Uri
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);//从系统表中查询指定Uri对应的照片
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                paths = cursor.getString(columnIndex);  //获取照片路径
                SharedPreferencesUtils.setImage(this, paths);
                cursor.close();
                Bitmap bitmap = BitmapFactory.decodeFile(paths);
                headImg.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
