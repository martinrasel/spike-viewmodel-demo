package de.bembelnaut.spike.viewmodeldemo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UserModel extends ViewModel {
    private final MutableLiveData<User> userLiveData = new MutableLiveData<>();

    public LiveData<User> getUser() {
        return userLiveData;
    }

    public UserModel() {
        // trigger user load.
    }

    void doAction(String newName) {
        // depending on the action, do necessary business logic calls and update the userLiveData.

        /*
        ViewModel is a class that is responsible for preparing and managing the data for an Activity
        or a Fragment. It also handles the communication of the Activity / Fragment with the rest of
        the application (e.g. calling the business logic classes).
         */
        User user = userLiveData.getValue();

        if (user == null) {
            user = new User();
        }

        user.setName(newName);
        userLiveData.setValue(user);
    }
}
