package filipesantoss.rickandmorty;

import android.app.Application;
import android.os.StrictMode;
import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class BaseApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();

    if (!BuildConfig.DEBUG) {
      return;
    }

    StrictMode.setThreadPolicy(
        new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().penaltyDeath().build());
  }
}
