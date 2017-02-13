package com.hackro.movies.central.movies;

import com.hackro.movies.central.BuildConfig;
import dagger.Module;
import dagger.Provides;
import java.io.File;
import java.util.Locale;
import javax.inject.Singleton;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

@Module public class MoviesModule {

  private File cacheFile;

  public MoviesModule(File cacheFile) {
    this.cacheFile = cacheFile;
  }

  @Provides @Singleton Retrofit retrofit() {
    Cache cache = new Cache(cacheFile, 10 * 1024 * 1024);

    OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(chain -> {
      Request original = chain.request();

      Request request = original.newBuilder()
          .header("Content-Type", "application/json")
          .removeHeader("Pragma")
          .header("Cache-Control",
              String.format(Locale.getDefault(), "max-age=%d", BuildConfig.CACHE_TIME))
          .build();

      Response response = chain.proceed(request);
      response.cacheResponse();
      return response;
    }).cache(cache).build();

    return new Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addConverterFactory(ScalarsConverterFactory.create())
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .build();
  }

  @Provides @Singleton GetMoviesAction getMoviesAction(Retrofit retrofit) {
    return new GetMoviesAction(retrofit);
  }
}
