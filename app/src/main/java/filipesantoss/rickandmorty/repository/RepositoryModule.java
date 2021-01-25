package filipesantoss.rickandmorty.repository;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityRetainedComponent;

@Module
@InstallIn(ActivityRetainedComponent.class)
public abstract class RepositoryModule {

  @Binds
  public abstract CharacterRepository bindCharacterRepository(CharacterRepositoryImpl repository);

  @Binds
  public abstract EpisodeRepository bindEpisodeRepository(EpisodeRepositoryImpl repository);

}
