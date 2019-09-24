package network;

import java.util.List;

import in.co.gstsamadhan.gstsamadhan.PaymentGateway.checksum;
import in.co.gstsamadhan.gstsamadhan.model.Acts;
import in.co.gstsamadhan.gstsamadhan.model.Coupon;
import in.co.gstsamadhan.gstsamadhan.model.News;
import in.co.gstsamadhan.gstsamadhan.model.Notification;
import in.co.gstsamadhan.gstsamadhan.model.Orders;
import in.co.gstsamadhan.gstsamadhan.model.PressRelease;
import in.co.gstsamadhan.gstsamadhan.model.Purchase;
import in.co.gstsamadhan.gstsamadhan.model.Registration;
import in.co.gstsamadhan.gstsamadhan.model.Rules;
import in.co.gstsamadhan.gstsamadhan.model.User;
import in.co.gstsamadhan.gstsamadhan.model.Video;
import in.co.gstsamadhan.gstsamadhan.model.gstforms;
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
    @GET("notification")
    Call<List<Notification>> getGstNotifications(
            @Query("key") String keyword
    );
    @GET("gstforms")
    Call<List<gstforms>> getGstForms(
            @Query("key") String keyword
    );

    @GET("pressrelease")
    Call<List<PressRelease>> getPressRelease(
            @Query("key") String keyword
    );
    @GET("orders")
    Call<List<Orders>> getOrders(
            @Query("key") String keyword
    );


    @GET("login")
    Call<List<User>> login(
            @Query("username") String username,
            @Query("password") String password
    );


    @FormUrlEncoded
    @POST("paytmapi/generateChecksum.php")
    Call<checksum> getChecksum(
            @Field("MID") String mId,
            @Field("ORDER_ID") String orderId,
            @Field("CUST_ID") String custId,
            @Field("CHANNEL_ID") String channelId,
            @Field("TXN_AMOUNT") String txnAmount,
            @Field("WEBSITE") String website,
            @Field("CALLBACK_URL") String callbackUrl,
            @Field("INDUSTRY_TYPE_ID") String industryTypeId
    );

    @FormUrlEncoded
    @POST("register")
    Call<Registration> register(
            @Field("name") String name,
            @Field("email") String email,
            @Field("mobile") String mobile,
            @Field("password") String password,
            @Field("state") String state,
            @Field("profession") String profession,
            @Field("city") String city,
            @Field("pincode") String pincode
    );
    @FormUrlEncoded
    @POST("purchase_plan")
    Call<Purchase> submitTransaction(
            @Field("client_id") String client_id,
            @Field("plan_id") String plan_id,
            @Field("payment_id") String payment_id,
            @Field("duration_range") String duration_range,
            @Field("total_amount") String total_amount,
            @Field("coupon_code") String coupon_code,
            @Field("discount_amount") String discount_amount,
            @Field("gst_amount") String gst_amount,
            @Field("final_amount") String final_amount,
            @Field("amount_paid") String amount_paid,
            @Field("payment_method") String payment_method,
            @Field("phone") String phone,
            @Field("email") String email,
            @Field("buyer_name") String buyer_name,
            @Field("status") String status,
            @Field("expires_at") String expires_at
    );


    @GET("coupons")
    Call<Coupon> getCoupon(
            @Query("coupon") String Coupon
    );
    @GET("videos")
    Call<List<Video>> getVideos(
    );

}