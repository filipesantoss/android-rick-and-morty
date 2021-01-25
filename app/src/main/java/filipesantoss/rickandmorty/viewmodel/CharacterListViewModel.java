package filipesantoss.rickandmorty.viewmodel;

import android.util.Log;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import filipesantoss.rickandmorty.model.Character;
import filipesantoss.rickandmorty.model.Page;
import filipesantoss.rickandmorty.repository.Repository;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CharacterListViewModel extends ViewModel implements Loadable {

  private final Repository<Page<Character>> repository;
  private final MutableLiveData<List<Character>> characters = new MutableLiveData<>();
  private Page.Data data;
  private Runnable loadStartAction;
  private Runnable loadFinishAction;

  private static final int INITIAL_PAGE_NUMBER = 1;

  @ViewModelInject
  public CharacterListViewModel(Repository<Page<Character>> repository) {
    this.repository = repository;
  }

  public MutableLiveData<List<Character>> getCharacters() {
    return characters;
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

  @Override
  public void onLoadStart(Runnable action) {
    loadStartAction = action;
  }

  @Override
  public void onLoadFinish(Runnable action) {
    loadFinishAction = action;
  }

  private void list(Integer pageNumber) {
    if (!Objects.isNull(loadStartAction)) {
      loadStartAction.run();
    }

    repository.list(pageNumber)
        .observeOn(AndroidSchedulers.mainThread()) // Execute on Android main thread.
        .subscribe(
            this::update,
            throwable -> Log.e(getClass().getName(), throwable.getMessage())
        );
  }

  private void update(Page<Character> characterPage) {
    Stream<Character> characters = characterPage.getItems().stream();
    this.data = characterPage.getData();

    if (!Objects.isNull(this.characters.getValue())) {
      characters = Stream.concat(this.characters.getValue().stream(), characters);
    }

    this.characters.postValue(characters.collect(Collectors.toList()));

    if (!Objects.isNull(loadFinishAction)) {
      loadFinishAction.run();
    }
  }

}