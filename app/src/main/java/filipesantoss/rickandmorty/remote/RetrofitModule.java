package filipesantoss.rickandmorty.remote;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import filipesantoss.rickandmorty.remote.CharacterService;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import javax.inject.Singleton;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(ApplicationComponent.class) // Has the lifetime of the application.
public class RetrofitModule {

  @Provides
  @Singleton
  public static CharacterService provideCharacterService() {
    return new Retrofit.Builder()
        .baseUrl("https://rickandmortyapi.com/api/")
        .addConverterFactory(GsonConverterFactory.create()) // Serialization.
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create()) // Call conversion.
        .build()
        .create(CharacterService.class);
  }

}
