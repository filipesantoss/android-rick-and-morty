package filipesantoss.rickandmorty.repository;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import filipesantoss.rickandmorty.model.Character;
import filipesantoss.rickandmorty.model.Episode;
import filipesantoss.rickandmorty.model.Page;

@Module
@InstallIn(ApplicationComponent.class)
public abstract class RepositoryModule {

  @Binds
  public abstract Repository<Page<Character>> bindCharacterRepository(
      CharacterRepository implementation
  );

  @Binds
  public abstract Repository<Page<Episode>> bindEpisodeRepository(EpisodeRepository implementation);

}
