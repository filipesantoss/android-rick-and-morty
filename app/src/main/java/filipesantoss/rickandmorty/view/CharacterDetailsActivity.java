package filipesantoss.rickandmorty.view;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import filipesantoss.rickandmorty.R;
import filipesantoss.rickandmorty.databinding.ActivityCharacterDetailsBinding;

public class CharacterDetailsActivity extends AppCompatActivity {

  public static final String CHARACTER = "filipesantoss.rickandmorty.CHARACTER";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    ActivityCharacterDetailsBinding binding = DataBindingUtil
        .setContentView(this, R.layout.activity_character_details);

    binding.characterImage.setContext(this);
    binding.setCharacter(getIntent().getParcelableExtra(CHARACTER));
  }

}
