package filipesantoss.rickandmorty.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import dagger.hilt.android.AndroidEntryPoint;
import filipesantoss.rickandmorty.R;
import filipesantoss.rickandmorty.databinding.ActivityCharacterListBinding;
import filipesantoss.rickandmorty.model.Character;
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

    CharacterAdapter adapter = new CharacterAdapter(this::onCharacterClick);
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


  private OnClickListener onCharacterClick(Character character) {
    Intent intent = new Intent(this, CharacterDetailsActivity.class)
        .putExtra(CharacterDetailsActivity.CHARACTER, character);

    return view -> startActivity(intent);
  }

}
