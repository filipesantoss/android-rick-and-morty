package filipesantoss.rickandmorty.repository;

import filipesantoss.rickandmorty.model.Episode;
import filipesantoss.rickandmorty.model.Page;
import io.reactivex.rxjava3.core.Observable;

public interface EpisodeRepository {

  Observable<Page<Episode>> list(int pageNumber);

}
