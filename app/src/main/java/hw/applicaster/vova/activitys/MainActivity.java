package hw.applicaster.vova.activitys;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import hw.applicaster.vova.R;
import hw.applicaster.vova.databinding.ActivityMainBinding;
import hw.applicaster.vova.fragments.ApplicasterListFragment;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (binding.fragmentsContainer != null){
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
}
