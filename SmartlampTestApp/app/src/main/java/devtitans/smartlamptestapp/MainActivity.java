package devtitans.smartlamptestapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import android.os.ServiceManager;
import android.os.IBinder;
import android.os.RemoteException;

import devtitans.smartlamp.ISmartlamp;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "DevTITANS.SmartlampApp";
    private SmartlampManager manager;

    private ConstraintLayout constraintLayout = findViewById(R.id.contraint);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = SmartlampManager.getInstance();

        updateAll(null);
    }

    public void updateAll(View view) {
        Log.d(TAG, "Atualizando dados do dispositivo ...");

        try {
            int luminosity = manager.getLuminosity();
            if (luminosity > 50) {
                constraintLayout.setBackgroundColor(getResources().getColor(R.color.green));
            } else {
                constraintLayout.setBackgroundColor(getResources().getColor(R.color.red));
            }

        } catch (android.os.RemoteException e) {
            Toast.makeText(this, "Erro ao acessar o Binder!", Toast.LENGTH_LONG).show();
            Log.e(TAG, "Erro atualizando dados:", e);
        }

    }

}