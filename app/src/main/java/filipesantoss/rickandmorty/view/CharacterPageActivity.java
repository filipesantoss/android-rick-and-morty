package filipesantoss.rickandmorty.view;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import dagger.hilt.android.AndroidEntryPoint;
import filipesantoss.rickandmorty.databinding.ActivityMainBinding;
import filipesantoss.rickandmorty.view.adapter.CharacterAdapter;
import filipesantoss.rickandmorty.viewmodel.CharacterPageViewModel;

@AndroidEntryPoint
public class CharacterPageActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());

    CharacterPageViewModel viewModel = new ViewModelProvider(this)
        .get(CharacterPageViewModel.class);

    CharacterAdapter adapter = new CharacterAdapter(this);
    adapter.onBottomScroll(viewModel::next);

    binding.characterList.setAdapter(adapter);

    viewModel.getCharacters().observe(this, adapter::setCharacters);

    viewModel.list();
  }

}