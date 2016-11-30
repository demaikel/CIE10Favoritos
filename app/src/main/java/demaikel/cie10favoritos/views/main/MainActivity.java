package demaikel.cie10favoritos.views.main;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;

import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.firebase.ui.auth.AuthUI;
import com.github.ybq.android.spinkit.SpinKitView;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import demaikel.cie10favoritos.R;
import demaikel.cie10favoritos.adapters.TabAdapter;
import demaikel.cie10favoritos.views.login.LoginActivity;

public class MainActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private PagerAdapter TabAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SpinKitView progressBar = (SpinKitView) findViewById(R.id.spin_kit);
        DoubleBounce doubleBounce = new DoubleBounce();
        progressBar.setIndeterminateDrawable(doubleBounce);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabAdapter = new TabAdapter(getSupportFragmentManager(),this);


        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(TabAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

                //FirebaseDatabase.getInstance().getReference().child("cie10");
                //FirebaseDatabase.getInstance().getReference().child("cie10").child("Ciertas afecciones originadas en el período perinatal (P00–P96)");
                //FirebaseRecyclerAdapter adapter =new FirebaseRecyclerAdapter<>()

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mViewPager.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }
        }, 3000);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.logOut) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    private void logout(){
        AuthUI.getInstance()
                .signOut(MainActivity.this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        MainActivity.this.finish();
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                });


    }

}



