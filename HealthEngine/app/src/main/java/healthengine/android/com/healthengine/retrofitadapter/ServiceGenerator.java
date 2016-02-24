package healthengine.android.com.healthengine.retrofitadapter;


import java.io.IOException;

import healthengine.android.com.healthengine.BuildConfig;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * Created by jsn on 30/1/16.
 */
public final class ServiceGenerator {

    private static final String API_BASE_URL = BuildConfig.API_BASE_URL;

    private static final String HE_API_ACCESS_KEY= BuildConfig.HE_API_ACCESS_KEY;
    private static final String HE_API_SECRET_KEY= BuildConfig.HE_API_SECRET_KEY;

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    private ServiceGenerator(){
    }

    /**Create Adapter without AccessToken
     * @return
     */
    public static ApiInter createService(){
        return createService(null);
    }


    /** Create Adapter with AccessToken
     * @param accessToken
     * @return
     */
    public static ApiInter createService(final String accessToken){

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(loggingInterceptor);

        if(accessToken != null){
            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();

                    // Request customization: add request headers
                    Request.Builder requestBuilder = original.newBuilder()
                            .header("Authorization", accessToken)
                            .header("HE-API-ACCESS-KEY", HE_API_ACCESS_KEY)
                            .header("HE-API-SECRET-KEY", HE_API_SECRET_KEY)
                            .method(original.method(), original.body());

                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });

        }else{

            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();

                    // Request customization: add request headers
                    Request.Builder requestBuilder = original.newBuilder()
                            .header("HE-API-ACCESS-KEY", HE_API_ACCESS_KEY)
                            .header("HE-API-SECRET-KEY", HE_API_SECRET_KEY)
                            .method(original.method(), original.body());

                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });
        }


        OkHttpClient client = httpClient.build();
        Retrofit retrofit = builder.client(client).build();


        return retrofit.create(ApiInter.class);

    }

}
