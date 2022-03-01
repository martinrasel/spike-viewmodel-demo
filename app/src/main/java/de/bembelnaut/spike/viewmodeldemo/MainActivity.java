package de.bembelnaut.spike.viewmodeldemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import de.bembelnaut.viewmodeldemo.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
         ViewModel is a class that is responsible for preparing and managing the data for an Activity
         or a Fragment. It also handles the communication of the Activity / Fragment with the rest of
         application (e.g. calling the business logic classes).
         */
        final UserModel userModel = new ViewModelProvider(this).get(UserModel.class);

        // This awareness ensures LiveData only updates app component observers that are in an active lifecycle state.
        // LiveData only notifies active observers about updates. Inactive observers registered to watch LiveData objects aren't notified about changes.
        // LiveData binds a LifeCycleOwner and Observers
        userModel.getUser().observe(this, user -> {
            Log.i("vmdemo", user.getName());
            Toast.makeText(MainActivity.this, "Hello " + user.getName(), Toast.LENGTH_SHORT).show();
        });

        findViewById(R.id.button).setOnClickListener(view -> {
            String newName = ((EditText)MainActivity.this.findViewById(R.id.inputText)).getText().toString();
            userModel.doAction(newName);
        });


        // EXKURS: Fragement
        /*
         A fragment represents a modular portion of the user interface within an activity. A fragment
         has its own lifecycle, receives its own input events, and you can add or remove fragments
         while the containing activity is running.
         */

        if (savedInstanceState == null) {
            // add some data...
            Bundle bundle = new Bundle();
            bundle.putString("initName", "Meister");

            getSupportFragmentManager()
                    .beginTransaction()
                        .setReorderingAllowed(true)
                        .add(R.id.fragment2, MyFirstFragment.class, bundle)
                    .commit();
        }
    }
}