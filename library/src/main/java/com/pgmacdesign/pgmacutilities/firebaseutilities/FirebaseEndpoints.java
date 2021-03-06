package com.pgmacdesign.pgmacutilities.firebaseutilities;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by pmacdowell on 2017-09-29.
 */

public interface FirebaseEndpoints {

    public static final String API_ENDPOINT = "/fcm";
    //No version with this. Implement in the future if they add
    public static final String VERSION = "";

    /**
     * Send a push notification
     * @param key Authorization key (should be 'key={key}'). If receiving 401 unauthorized
     *            errors, please see this link: https://stackoverflow.com/questions/37633188/firebase-401-unauthorized-error-fcm
     * @param pojo {@link PushNotificationsPojo}
     * @return Map<String, Object>> response data
     */
    @POST(API_ENDPOINT + VERSION + "/send")
    Call<ResponseBody> sendPushNotification(@Header("Authorization") String key,
                                            @Body PushNotificationsPojo pojo);
}
