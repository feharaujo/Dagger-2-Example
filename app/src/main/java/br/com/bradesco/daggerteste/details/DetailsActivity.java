package br.com.bradesco.daggerteste.details;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import br.com.bradesco.daggerteste.AppApplication;
import br.com.bradesco.daggerteste.R;
import br.com.bradesco.daggerteste.main.DaggerMainActivityComponent;
import br.com.bradesco.daggerteste.main.MainActivityComponent;
import br.com.bradesco.daggerteste.main.MainActivityModule;
import retrofit2.Retrofit;

public class DetailsActivity extends AppCompatActivity {

    @Inject
    Retrofit retrofit;
    @Inject
    Picasso picasso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        MainActivityComponent mainActivityComponent = DaggerMainActivityComponent.builder()
                .mainActivityModule(new MainActivityModule("https://www.google.com.br/"))
                .applicationComponent(((AppApplication) getApplication()).getAppComponent())
                .build();

        mainActivityComponent.inject(this);

        TextView textView = (TextView) findViewById(R.id.textView);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Retrofit: " + retrofit);
        stringBuilder.append("\n");
        stringBuilder.append("Picasso: " + picasso);

        textView.setText(stringBuilder.toString());
    }
}
