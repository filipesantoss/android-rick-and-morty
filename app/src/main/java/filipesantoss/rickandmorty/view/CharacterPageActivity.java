package filipesantoss.rickandmorty.view;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import dagger.hilt.android.AndroidEntryPoint;
import filipesantoss.rickandmorty.databinding.ActivityCharacterPageBinding;
import filipesantoss.rickandmorty.view.adapter.CharacterAdapter;
import filipesantoss.rickandmorty.viewmodel.CharacterPageViewModel;
import java.util.Objects;

@AndroidEntryPoint
public class CharacterPageActivity extends AppCompatActivity {

  private CharacterPageViewModel viewModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    ActivityCharacterPageBinding binding = ActivityCharacterPageBinding
        .inflate(getLayoutInflater());
    setContentView(binding.getRoot());

    viewModel = new ViewModelProvider(this).get(CharacterPageViewModel.class);

    CharacterAdapter adapter = new CharacterAdapter();
    adapter.onBottomScroll(viewModel::next);

    binding.characterList.setAdapter(adapter);

    viewModel.onLoadStart(() -> binding.characterLoading.setVisibility(View.VISIBLE));
    viewModel.onLoadFinish(() -> binding.characterLoading.setVisibility(View.GONE));
    viewModel.getCharacters().observe(this, adapter::setCharacters);

    if (Objects.isNull(savedInstanceState)) {
      viewModel.list();
    }
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    viewModel.getCharacters().removeObservers(this);
  }

}