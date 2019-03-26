package hw.applicaster.vova.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hw.applicaster.vova.R;
import hw.applicaster.vova.adapter.ApplicasterRecyclerAdapter;
import hw.applicaster.vova.databinding.ApplicastListFragmentBinding;
import hw.applicaster.vova.network.model.Entry;
import hw.applicaster.vova.viewmodels.ApplicastDetailsViewModel;
import hw.applicaster.vova.viewmodels.ApplicasterListViewModel;


public class ApplicasterListFragment extends Fragment implements ApplicasterRecyclerAdapter.OnItemClickListener {

    private static final String TAG = "ApplicasterListFragment";
    private ApplicasterListViewModel applicasterListViewModel;
    private ApplicasterRecyclerAdapter adapter;
    private ApplicastListFragmentBinding binding;

    public static ApplicasterListFragment newInstance() {
        ApplicasterListFragment fragment = new ApplicasterListFragment();
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.applicast_list_fragment, container, false);
        binding.rssList.setLayoutManager(new LinearLayoutManager(getContext()));
        applicasterListViewModel = ViewModelProviders.of(this).get(ApplicasterListViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        adapter = new ApplicasterRecyclerAdapter(this);
        binding.rssList.setAdapter(adapter);
        applicasterListViewModel.getEntriesMutableLiveData().observe(this, response -> adapter.setValues(response));
    }

    @Override
    public void itemClicked(Entry item) {

            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            transaction.replace(R.id.fragmentsContainer, ApplicastterDetailsFragment.newInstance(item));
            transaction.addToBackStack(null);
            // Commit the transaction
            transaction.commit();

    }

//    @Override
//    public void itemClicked(RssModel item) {
//        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(item.getLink()));
//        startActivity(browserIntent);
//
//        //I just noticed that need to pass last item name - sharedpref its just a quick fix
//        SharedPreferences.Editor editor = getContext().getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE).edit();
//        editor.putString("lable", item.getTitle());
//        editor.apply();
//    }
}