package filipesantoss.rickandmorty.repository;

import filipesantoss.rickandmorty.model.CharacterPage;
import filipesantoss.rickandmorty.remote.CharacterService;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import javax.inject.Inject;

public class CharacterRepository {

  private final CharacterService characterService;

  @Inject
  public CharacterRepository(CharacterService characterService) {
    this.characterService = characterService;
  }

  public Observable<CharacterPage> list() {
    return characterService.list()
        .subscribeOn(Schedulers.io()); // Asynchronously perform blocking IO .
  }
}
