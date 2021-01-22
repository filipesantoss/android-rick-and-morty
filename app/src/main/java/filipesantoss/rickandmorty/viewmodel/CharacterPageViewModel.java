package filipesantoss.rickandmorty.viewmodel;

import android.util.Log;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import filipesantoss.rickandmorty.model.Character;
import filipesantoss.rickandmorty.model.CharacterPage;
import filipesantoss.rickandmorty.repository.CharacterRepository;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CharacterPageViewModel extends ViewModel {

  private static final int INITIAL_PAGE_NUMBER = 1;

  private final CharacterRepository repository;
  private final MutableLiveData<List<Character>> characters = new MutableLiveData<>();
  private CharacterPage.Pagination pagination;

  @ViewModelInject
  public CharacterPageViewModel(CharacterRepository repository) {
    this.repository = repository;
  }

  public MutableLiveData<List<Character>> getCharacters() {
    return characters;
  }

  public void list() {
    list(INITIAL_PAGE_NUMBER);
  }

  public void next() {
    if (Objects.isNull(pagination)) {
      return;
    }

    pagination.getNextPageNumber().ifPresent(this::list);
  }

  private void list(Integer pageNumber) {
    repository.list(pageNumber)
        .observeOn(AndroidSchedulers.mainThread()) // Execute on Android main thread.
        .subscribe(
            this::update,
            throwable -> Log.e(getClass().getName(), throwable.getMessage())
        );
  }

  private void update(CharacterPage characterPage) {
    Stream<Character> characters = characterPage.getCharacters().stream();
    this.pagination = characterPage.getPagination();

    if (!Objects.isNull(this.characters.getValue())) {
      characters = Stream.concat(this.characters.getValue().stream(), characters);
    }

    this.characters.postValue(characters.collect(Collectors.toList()));
  }

}
