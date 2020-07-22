package cl.cocio.androidtestionix.data.api.retrofit;

import java.util.concurrent.TimeUnit;

import cl.cocio.androidtestionix.BuildConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiConnector {

    private static final String IONIX_BASE_URL = BuildConfig.IONIX_API_BASE_URL;
    private static final String TYPICODE_BASE_URL = BuildConfig.TYPICODE_API_BASE_URL;

    public static ApiConnector mApiConnector;

    private static Retrofit ionixRetrofit = null;
    private static Retrofit typicodeRetrofit = null;

    public static ApiConnector getInstance() {
        if (mApiConnector == null) {
            mApiConnector = new ApiConnector();
        }
        return mApiConnector;
    }

    public Retrofit getIonixRetrofit() {
        return getIonixClient();
    }

    public Retrofit getTypicodeRetrofit() {
        return getTypicodeClient();
    }

    private Retrofit getIonixClient() {
        if (ionixRetrofit == null) {

            OkHttpClient.Builder client = new OkHttpClient().newBuilder();
            client.readTimeout(30, TimeUnit.SECONDS);
            client.connectTimeout(30, TimeUnit.SECONDS);

            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                client.addInterceptor(interceptor);
            }

            ionixRetrofit = new Retrofit.Builder()
                    .baseUrl(IONIX_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client.build())
                    .build();
        }
        return ionixRetrofit;
    }

    private Retrofit getTypicodeClient() {
        if (typicodeRetrofit == null) {

            OkHttpClient.Builder client = new OkHttpClient().newBuilder();
            client.readTimeout(30, TimeUnit.SECONDS);
            client.connectTimeout(30, TimeUnit.SECONDS);

            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                client.addInterceptor(interceptor);
            }

            typicodeRetrofit = new Retrofit.Builder()
                    .baseUrl("https://jsonplaceholder.typicode.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client.build())
                    .build();
        }
        return typicodeRetrofit;
    }
}
