package filipesantoss.rickandmorty.repository;

import filipesantoss.rickandmorty.model.Character;
import filipesantoss.rickandmorty.remote.CharacterService;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;

public class CharacterRepositoryImpl implements CharacterRepository {

  private final CharacterService characterService;

  @Inject
  public CharacterRepositoryImpl(CharacterService characterService) {
    this.characterService = characterService;
  }

  @Override
  public Observable<List<Character>> get(List<Integer> ids) {
    return characterService
        .list(ids.stream().map(String::valueOf).collect(Collectors.joining(",")))
        .subscribeOn(Schedulers.io()); // Asynchronously perform blocking IO .
  }

}
