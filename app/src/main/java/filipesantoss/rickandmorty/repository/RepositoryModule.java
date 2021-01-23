package filipesantoss.rickandmorty.repository;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import filipesantoss.rickandmorty.model.page.CharacterPage;

@Module
@InstallIn(ApplicationComponent.class)
public abstract class RepositoryModule {

  @Binds
  public abstract Repository<CharacterPage> bindCharacterRepository(
      CharacterRepository implementation
  );

}
