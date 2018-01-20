import android.app.Application;

import com.example.nhem.eventwithlove.event.activities.Preferences;

/**
 * Created by NHEM on 20/01/2018.
 */

public class EventApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Preferences.init(this);
    }
}
