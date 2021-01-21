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

public class CharacterPageViewModel extends ViewModel {

  private final CharacterRepository repository;
  private final MutableLiveData<List<Character>> characters = new MutableLiveData<>();

  @ViewModelInject
  public CharacterPageViewModel(CharacterRepository repository) {
    this.repository = repository;
  }

  public MutableLiveData<List<Character>> getCharacters() {
    return characters;
  }

  public void list() {
    repository.list()
        .observeOn(AndroidSchedulers.mainThread()) // Execute on Android main thread.
        .map(CharacterPage::getCharacters)
        .subscribe(characters::postValue,
            throwable -> {
              Log.e(getClass().getName(), throwable.getMessage());
              throwable.printStackTrace();
            });
  }
}
