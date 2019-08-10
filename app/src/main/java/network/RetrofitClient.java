package network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static final String BASE_URL = "https://gstsamadhan.co.in/gstapi/";
 /*   private static RetrofitClient mInstancce;
    private static Retrofit retrofit = null;


  private RetrofitClient(){

      retrofit = new Retrofit.Builder()
              .baseUrl(BASE_URL)
              .addConverterFactory(GsonConverterFactory.create())
              .build();
  }

    public static synchronized RetrofitClient getInstance(){
       if(mInstancce == null){
           mInstancce = new RetrofitClient();
       }
       return mInstancce;
    }

    public GstSamadhanApi getApi(){
      return retrofit.create(GstSamadhanApi.class);
    }
*/
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
