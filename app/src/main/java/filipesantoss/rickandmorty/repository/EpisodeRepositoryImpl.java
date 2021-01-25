package filipesantoss.rickandmorty.repository;

import filipesantoss.rickandmorty.model.Episode;
import filipesantoss.rickandmorty.model.Page;
import filipesantoss.rickandmorty.remote.EpisodeService;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import javax.inject.Inject;

public class EpisodeRepositoryImpl implements EpisodeRepository {

  private final EpisodeService episodeService;

  @Inject
  public EpisodeRepositoryImpl(EpisodeService episodeService) {
    this.episodeService = episodeService;
  }

  @Override
  public Observable<Page<Episode>> list(int pageNumber) {
    return episodeService.list(pageNumber)
        .subscribeOn(Schedulers.io());
  }

}
