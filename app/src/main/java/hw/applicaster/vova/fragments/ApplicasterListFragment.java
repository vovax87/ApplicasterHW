package hw.applicaster.vova.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DividerItemDecoration;
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
    private NavigationListener navigator;

    public static ApplicasterListFragment newInstance() {
        ApplicasterListFragment fragment = new ApplicasterListFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            navigator = (NavigationListener ) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement NavigationListener ");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.applicast_list_fragment, container, false);
        binding.itemsList.setLayoutManager(new LinearLayoutManager(getContext()));
        applicasterListViewModel = ViewModelProviders.of(this).get(ApplicasterListViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setListView();
        applicasterListViewModel.getEntriesMutableLiveData().observe(this, response -> adapter.setValues(response));
    }

    private void setListView() {
        adapter = new ApplicasterRecyclerAdapter(this);
        binding.itemsList.setAdapter(adapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL );

        binding.itemsList.addItemDecoration(dividerItemDecoration);
    }

    @Override
    public void itemClicked(Entry item) {
        navigator.navigateTo(ApplicastterDetailsFragment.newInstance(item));
    }


}