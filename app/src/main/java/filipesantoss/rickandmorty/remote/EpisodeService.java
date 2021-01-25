package filipesantoss.rickandmorty.remote;

import filipesantoss.rickandmorty.model.Episode;
import filipesantoss.rickandmorty.model.Page;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface EpisodeService {

  @GET("episode")
  Observable<Page<Episode>> list(@Query("page") int pageNumber);

}
