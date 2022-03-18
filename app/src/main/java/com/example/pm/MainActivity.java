package com.example.pm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.View;
import com.beardedhen.androidbootstrap.BootstrapButton;
import android.view.Menu;
import com.beardedhen.androidbootstrap.TypefaceProvider;

public class MainActivity extends AppCompatActivity {

    BootstrapButton addButton, dbButton;
    Fragment addFragment = new AddFragment();
    Fragment databaseFragment = new DatabaseFragment();
    Fragment nowFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TypefaceProvider.registerDefaultIconSets();
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().add(R.id.container, addFragment).commit();
        nowFragment = addFragment;

        addButton = findViewById(R.id.addButton);
        dbButton = findViewById(R.id.dbButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nowFragment != addFragment) getSupportFragmentManager().beginTransaction().replace(R.id.container, addFragment).commit();
                nowFragment = addFragment;

            }
        });
        dbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nowFragment != databaseFragment) getSupportFragmentManager().beginTransaction().replace(R.id.container, databaseFragment).commit();
                nowFragment = databaseFragment;
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

}