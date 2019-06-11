package hr.tvz.android.listavladimir;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;

public class MyFirebaseInstanceIDService extends FirebaseMessagingService {
    private static final String TAG = "FirebaseIIDServiceDemo";
    public String[] name;

   // @Override
    public void onTokenRefresh() {
        String token = FirebaseInstanceId.getInstance().getToken();
        System.out.println("my firebase token " + token );
    }
    private void sendRegistrationToServer(String token) {

    }
}
