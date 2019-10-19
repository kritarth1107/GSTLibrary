package network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static final String BASE_URL = "https://gstsamadhan.co.in/gstapi/";
 public static Retrofit retrofit;
 public static Retrofit getApiClient(){
     if (retrofit==null){
         retrofit = new Retrofit.Builder()
                 .baseUrl(BASE_URL)
                 .addConverterFactory(GsonConverterFactory.create())
                 .build();
     }
     return retrofit;
 }
}
