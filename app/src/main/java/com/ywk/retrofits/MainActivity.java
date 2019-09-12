package com.ywk.retrofits;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE
            }, 1);
        }
        context = this;
    }

    public void btnSendRequset(View view) {
        upLoadImage();
    }

//    private void upLoadImage() {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://style.bwdz.cn:8086/index.php/api/style/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(HttpLoggingInterceptorUtil.getClient())
//                .build();
//        GetRequest_Interface request = retrofit.create(GetRequest_Interface.class);
//
//        HashMap<String, RequestBody> map = new HashMap<>();
//        RequestBody requestFile1 = RequestBody.create(MediaType.parse("image/*"), new File(Environment.getExternalStorageDirectory(), "07.png"));
//
//        if (requestFile1 != null) {
//            map.put("07.png", requestFile1);
//        }
//        if (!map.isEmpty()) {
//            Call<ResponseBody> call = request.saveReport(new File(Environment.getExternalStorageDirectory(), "07.png"));
//            call.enqueue(new Callback<ResponseBody>() {
//                @Override
//                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                    Toast.makeText(context, "成功" + response.toString(), Toast.LENGTH_SHORT).show();
//                    Log.d("TAG", response.toString());
//                }
//
//                @Override
//                public void onFailure(Call<ResponseBody> call, Throwable t) {
//                    Toast.makeText(context, "失败" + t.getMessage(), Toast.LENGTH_SHORT).show();
//                    Log.d("TAG", t.getMessage());
//                }
//            });
//        }
//    }

    private void upLoadImage() {
        String path = Environment.getExternalStorageDirectory() + "07.png";
        File file = new File(path);

        RequestBody fileRQ = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part part= MultipartBody.Part.createFormData("picture", file.getName(), fileRQ);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://style.bwdz.cn:8086/index.php/api/style/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(HttpLoggingInterceptorUtil.getClient())
                .build();
        GetRequest_Interface request = retrofit.create(GetRequest_Interface.class);

        Call<ResponseBody> call = request.saveReport(part);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(context, "成功" + response.toString(), Toast.LENGTH_SHORT).show();
                Log.d("TAG", response.toString());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(context, "失败" + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("TAG", t.getMessage());
            }
        });
    }
}
