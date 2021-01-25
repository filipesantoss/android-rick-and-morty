package filipesantoss.rickandmorty.viewmodel;

import android.util.Log;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import filipesantoss.rickandmorty.model.Episode;
import filipesantoss.rickandmorty.model.Page;
import filipesantoss.rickandmorty.repository.EpisodeRepository;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EpisodeListViewModel extends LoadableViewModel {

  protected final EpisodeRepository repository;
  protected final MutableLiveData<List<Episode>> episodes = new MutableLiveData<>();
  protected Page.Data data;

  private static final int INITIAL_PAGE_NUMBER = 1;

  @ViewModelInject
  public EpisodeListViewModel(EpisodeRepository repository) {
    this.repository = repository;
  }

  public MutableLiveData<List<Episode>> getEpisodes() {
    return episodes;
  }

  public void list() {
    list(INITIAL_PAGE_NUMBER);
  }

  public void next() {
    if (Objects.isNull(data)) {
      return;
    }

    data.getNextPageNumber().ifPresent(this::list);
  }

  private void list(Integer pageNumber) {
    startLoading();

    repository.list(pageNumber)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            this::update,
            throwable -> Log.e(getClass().getName(), throwable.getMessage())
        );
  }

  private void update(Page<Episode> page) {
    data = page.getData();

    Stream<Episode> episodes = Objects.isNull(this.episodes.getValue())
        ? page.getItems().stream()
        : Stream.concat(this.episodes.getValue().stream(), page.getItems().stream());

    this.episodes.postValue(episodes.collect(Collectors.toList()));
    stopLoading();
  }

}
