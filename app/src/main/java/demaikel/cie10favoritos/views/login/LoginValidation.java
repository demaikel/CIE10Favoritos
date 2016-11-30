package demaikel.cie10favoritos.views.login;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Michael on 27-11-2016.
 */

public class LoginValidation {

    private LoginCallback callback;

    public LoginValidation(LoginCallback callback) {
        this.callback = callback;
    }

    public void init(){

        FirebaseAuth auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            callback.logged();
        } else {
            callback.singUp();
        }
    }

}
