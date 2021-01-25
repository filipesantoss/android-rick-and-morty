package filipesantoss.rickandmorty.viewmodel;

import android.util.Log;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import filipesantoss.rickandmorty.model.Character;
import filipesantoss.rickandmorty.repository.CharacterRepository;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import java.util.List;

public class CharacterListViewModel extends LoadableViewModel {

  protected final CharacterRepository repository;
  protected final MutableLiveData<List<Character>> characters = new MutableLiveData<>();

  @ViewModelInject
  public CharacterListViewModel(CharacterRepository repository) {
    this.repository = repository;
  }

  public MutableLiveData<List<Character>> getCharacters() {
    return characters;
  }

  public void list(List<Integer> ids) {
    startLoading();

    repository.get(ids)
        .observeOn(AndroidSchedulers.mainThread()) // Execute on Android main thread.
        .subscribe(
            characters -> {
              this.characters.postValue(characters);
              stopLoading();
            },
            throwable -> Log.e(getClass().getName(), throwable.getMessage())
        );
  }

}
