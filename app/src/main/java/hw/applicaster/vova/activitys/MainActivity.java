package hw.applicaster.vova.activitys;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import hw.applicaster.vova.R;
import hw.applicaster.vova.databinding.ActivityMainBinding;
import hw.applicaster.vova.fragments.ApplicasterListFragment;
import hw.applicaster.vova.fragments.ApplicastterDetailsFragment;
import hw.applicaster.vova.fragments.NavigationListener;

public class MainActivity extends AppCompatActivity implements NavigationListener {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        if (!getResources().getBoolean(R.bool.isTablet)){
            //if we're being restored from a previous state,
           //  then we don't need to do anything and should return
            if (savedInstanceState != null) {
                return;
            }

            // Add the fragment to the 'fragmentsContainer' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentsContainer, ApplicasterListFragment.newInstance()).commit();
        }


    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void navigateTo(Fragment fragment) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        transaction.replace(binding.fragmentsContainer.getId(), fragment);
        transaction.addToBackStack(null);
        // Commit the transaction
        transaction.commit();

    }
}
