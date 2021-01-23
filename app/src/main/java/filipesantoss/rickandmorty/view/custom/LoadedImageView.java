package filipesantoss.rickandmorty.view.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import filipesantoss.rickandmorty.R;
import java.util.Objects;

public class LoadedImageView extends AppCompatImageView {

  private Context context;
  private String url;
  private Drawable placeholder;
  private Drawable error;

  public LoadedImageView(Context context) {
    super(context);
    init(null, 0);
  }

  public LoadedImageView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(attrs, 0);
  }

  public LoadedImageView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    init(attrs, defStyle);
  }

  public void setContext(Context context) {
    this.context = context;
  }

  public void setUrl(String url) {
    this.url = url;
    load();
  }

  private void init(AttributeSet attrs, int defStyle) {
    TypedArray styledAttributes = getContext()
        .obtainStyledAttributes(attrs, R.styleable.LoadedImageView, defStyle, 0);

    url = styledAttributes.getString(R.styleable.LoadedImageView_url);

    placeholder = getDrawable(styledAttributes, R.styleable.LoadedImageView_placeholder);
    error = getDrawable(styledAttributes, R.styleable.LoadedImageView_error);

    styledAttributes.recycle();
  }

  private Drawable getDrawable(TypedArray styledAttributes, int styleable) {
    if (!styledAttributes.hasValue(styleable)) {
      return null;
    }

    Drawable drawable = styledAttributes.getDrawable(styleable);
    drawable.setCallback(this);
    return drawable;
  }

  private void load() {
    if (Objects.isNull(context)) {
      throw new IllegalStateException("Missing call to LoadedImageView#setContext(Context)");
    }

    Glide.with(context).load(url)
        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
        .placeholder(placeholder)
        .error(error)
        .into(this);
  }

}
