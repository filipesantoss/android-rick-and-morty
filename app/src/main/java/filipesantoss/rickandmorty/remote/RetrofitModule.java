package filipesantoss.rickandmorty.remote;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import javax.inject.Singleton;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(ApplicationComponent.class) // Has the lifetime of the application.
public class RetrofitModule {

  private static final String BASE_URL = "https://rickandmortyapi.com/api/";

  @Provides
  @Singleton
  public static CharacterService provideCharacterService() {
    return new Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()) // Serialization.
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create()) // Call conversion.
        .build()
        .create(CharacterService.class);
  }

  @Provides
  @Singleton
  public static EpisodeService provideEpisodeService() {
    return new Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()
        .create(EpisodeService.class);
  }

}
