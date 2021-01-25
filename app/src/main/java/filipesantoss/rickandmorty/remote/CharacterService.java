package filipesantoss.rickandmorty.remote;

import filipesantoss.rickandmorty.model.Character;
import io.reactivex.rxjava3.core.Observable;
import java.util.List;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CharacterService {

  @GET("character/{ids}")
  Observable<List<Character>> list(@Path(value = "ids") String ids);

}
