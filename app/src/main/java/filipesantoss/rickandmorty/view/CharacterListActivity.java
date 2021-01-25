package filipesantoss.rickandmorty.view;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import dagger.hilt.android.AndroidEntryPoint;
import filipesantoss.rickandmorty.R;
import filipesantoss.rickandmorty.databinding.ActivityCharacterListBinding;
import filipesantoss.rickandmorty.view.adapter.CharacterAdapter;
import filipesantoss.rickandmorty.viewmodel.CharacterListViewModel;
import java.util.Objects;

@AndroidEntryPoint
public class CharacterListActivity extends AppCompatActivity {

  private ActivityCharacterListBinding binding;
  private CharacterListViewModel viewModel;

  public static final String CHARACTER_ID_LIST = "filipesantoss.rickandmorty.CHARACTER_ID_LIST";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    binding = DataBindingUtil.setContentView(this, R.layout.activity_character_list);
    viewModel = new ViewModelProvider(this).get(CharacterListViewModel.class);

    CharacterAdapter adapter = new CharacterAdapter();
    binding.characterList.setAdapter(adapter);

    viewModel.onLoadStart(this::onLoadStart);
    viewModel.onLoadFinish(this::onLoadFinished);
    viewModel.getCharacters().observe(this, adapter::setCharacters);

    if (Objects.isNull(savedInstanceState)) {
      viewModel.list(getIntent().getIntegerArrayListExtra(CHARACTER_ID_LIST));
    }
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    viewModel.getCharacters().removeObservers(this);
  }

  private void onLoadStart() {
    binding.characterLoading.setVisibility(View.VISIBLE);
    binding.characterList.setVisibility(View.GONE);
  }

  private void onLoadFinished() {
    binding.characterLoading.setVisibility(View.GONE);
    binding.characterList.setVisibility(View.VISIBLE);
  }

}
