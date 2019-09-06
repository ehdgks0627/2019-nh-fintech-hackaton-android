package com.example.nhfintechsproutmarket;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.example.nhfintechsproutmarket.dummy.CartItemContent;
import com.example.nhfintechsproutmarket.dummy.HomeItemContent;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements HomeFragment.OnListFragmentInteractionListener, RecommandFragment.OnFragmentInteractionListener,  MyPageFragment.OnFragmentInteractionListener, CartItemFragment.OnListFragmentInteractionListener {

    @BindView(R.id.fab)
    FloatingActionButton fab;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        fragmentTransaction.replace(R.id.fragment, new HomeFragment());
                        break;
                    case R.id.navigation_recommand:
                        fragmentTransaction.replace(R.id.fragment, new RecommandFragment());
                        break;
                    case R.id.navigation_cart:
                        fragmentTransaction.replace(R.id.fragment, new CartItemFragment());
                        return false;
                    case R.id.navigation_mypage:
                        fragmentTransaction.replace(R.id.fragment, new MyPageFragment());
                        break;
                }
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                return true;
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fab.setOnClickListener(view -> new IntentIntegrator(MainActivity.this).initiateScan());
    }

    @Override
    public void onListFragmentInteraction(HomeItemContent.DummyItem item) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
                // todo
            } else {
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                // todo
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onListFragmentInteraction(CartItemContent.DummyItem item) {

    }
}
