package filipesantoss.rickandmorty.repository;

import filipesantoss.rickandmorty.model.page.CharacterPage;
import filipesantoss.rickandmorty.remote.CharacterService;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import javax.inject.Inject;

public class CharacterRepository implements Repository<CharacterPage> {

  private final CharacterService characterService;

  @Inject
  public CharacterRepository(CharacterService characterService) {
    this.characterService = characterService;
  }

  @Override
  public Observable<CharacterPage> list(int pageNumber) {
    return characterService.list(pageNumber)
        .subscribeOn(Schedulers.io()); // Asynchronously perform blocking IO .
  }

}
