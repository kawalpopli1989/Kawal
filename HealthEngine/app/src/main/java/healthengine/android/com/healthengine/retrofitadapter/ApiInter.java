package healthengine.android.com.healthengine.retrofitadapter;

import java.util.List;

import healthengine.android.com.healthengine.Constants;
import healthengine.android.com.healthengine.data.DataG;
import healthengine.android.com.healthengine.data.LoginRequest;
import healthengine.android.com.healthengine.data.LoginResponse;
import healthengine.android.com.healthengine.data.LogoutResponse;
import healthengine.android.com.healthengine.data.MemberDetail;
import healthengine.android.com.healthengine.data.NewProfileResponse;
import healthengine.android.com.healthengine.data.PatientProfileData;
import healthengine.android.com.healthengine.data.SignUpRequest;
import healthengine.android.com.healthengine.data.Token;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by jsn on 31/1/16.
 */

public interface ApiInter {

    @POST(Constants.SIGNUP_EP)
    Call<Token> registerNewMember(@Body SignUpRequest mSignUpRequest);

    @POST(Constants.LOGIN_EP)
    Call<LoginResponse> signInWithMail(@Body LoginRequest mLoginRequest);

    @POST(Constants.LOGIN_EP + "/{name}")
    Call<LoginResponse> enterInWithToken(@Path("name") String fbOrGoogle, @Body Token mToken);

    @POST(Constants.GENERATE_NEW_TOKEN)
    Call<LoginResponse> regenerateNewToken(@Body Token mToken);

    @POST(Constants.LOGOUT)
    Call<LogoutResponse> logout(@Body Token mToken);

    @GET(Constants.BOOKINGS)
    void bookings(@Query("auth") String token);

    @GET(Constants.MEMBER_DETAIL)
    Call<List<MemberDetail>> getMemberDetail(@Query("auth") String token);

    @POST(Constants.NEW_MEMBER_PROFILE)
    Call<NewProfileResponse> newMemberProfile(@Body PatientProfileData mPatientProfileData);

    @POST(Constants.MEMBER_DETAIL + "/{id}/delete")
    Call<DataG> deleteMemberProfile(@Path("id") String id, @Body Token mToken);

}
