package br.com.bradesco.daggerteste.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import br.com.bradesco.daggerteste.AppApplication;
import br.com.bradesco.daggerteste.R;
import br.com.bradesco.daggerteste.details.DetailsActivity;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    @Inject
    Retrofit retrofit;
    @Inject
    Picasso picasso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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


    public void nextScreen(View view) {
        Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
        startActivity(intent);
    }
}
