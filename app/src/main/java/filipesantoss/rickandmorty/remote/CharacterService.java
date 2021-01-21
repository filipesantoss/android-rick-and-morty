package filipesantoss.rickandmorty.remote;

import filipesantoss.rickandmorty.model.CharacterPage;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface CharacterService {

  @GET("character")
  Observable<CharacterPage> list();

}
