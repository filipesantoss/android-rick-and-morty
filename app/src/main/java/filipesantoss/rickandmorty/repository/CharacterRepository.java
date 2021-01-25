package filipesantoss.rickandmorty.repository;

import filipesantoss.rickandmorty.model.Character;
import io.reactivex.rxjava3.core.Observable;
import java.util.List;

public interface CharacterRepository {

  Observable<List<Character>> get(List<Integer> ids);

}
