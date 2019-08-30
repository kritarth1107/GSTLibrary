package network;

import java.util.List;

import in.co.gstsamadhan.gstsamadhan.model.Acts;
import in.co.gstsamadhan.gstsamadhan.model.News;
import in.co.gstsamadhan.gstsamadhan.model.Rules;
import in.co.gstsamadhan.gstsamadhan.model.User;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GstSamadhanApi {

   /* @GET("acts")
    Call<List<Acts>> jsonValues();*/

    @GET("acts")
    Call<List<Acts>> getActs(
            @Query("key") String keyword
    );
    @GET("rules")
    Call<List<Rules>> getRules(
            @Query("key") String keyword
    );
    @GET("news")
    Call<List<News>> getNews(
            @Query("key") String keyword
    );
    @GET("login")
    Call<List<User>> login(
            @Query("username") String username,
            @Query("password") String password
    );

}