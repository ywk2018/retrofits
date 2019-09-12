package com.ywk.retrofits;


import java.io.File;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

public interface GetRequest_Interface {
    public static final String BASE_URL = "http://118.89.50.100:8080/Firm_Capital_Management_System0.4/";

    /**
     * 上传图片
     */
    public static final String UP_LOAD = "uploadAPI.action";

    /**
     * 上传图片
     *
     * * @param map  图片集合
     * @return Call<ResponseBody>
     */
//    @Multipart
//    @POST("http://style.bwdz.cn:8086/index.php/api/style/upload.html")
//    Call<ResponseBody> saveReport(File file);

    @Multipart
    @POST("http://style.bwdz.cn:8086/index.php/api/style/upload.html")
    Call<ResponseBody> saveReport(@Part MultipartBody.Part body);
}
