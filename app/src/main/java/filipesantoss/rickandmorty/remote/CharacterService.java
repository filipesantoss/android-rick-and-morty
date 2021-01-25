package filipesantoss.rickandmorty.remote;

import filipesantoss.rickandmorty.model.Character;
import filipesantoss.rickandmorty.model.Page;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CharacterService {

  @GET("character")
  Observable<Page<Character>> list(@Query("page") int pageNumber);

}
