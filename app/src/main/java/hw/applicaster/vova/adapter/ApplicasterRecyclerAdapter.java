package hw.applicaster.vova.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import hw.applicaster.vova.R;
import hw.applicaster.vova.databinding.LinkItemBinding;
import hw.applicaster.vova.databinding.VideoItemBinding;
import hw.applicaster.vova.network.model.Entry;
import hw.applicaster.vova.network.model.general.Type;

public class ApplicasterRecyclerAdapter extends RecyclerView.Adapter<ApplicasterRecyclerAdapter.BaseViewHolder> {

    private static final int VIDEO_TYPE = 0;
    private static final int LINK_TYPE = 1;

    private static final String TAG = "ApplicasterRecyclerAdapter";
    private final List<Entry> items;
    private final OnItemClickListener listener;

    public ApplicasterRecyclerAdapter(OnItemClickListener listener) {
        items = new ArrayList<>();
        this.listener = listener;
    }

    @Override
    public ApplicasterRecyclerAdapter.BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        switch (viewType){
            case VIDEO_TYPE:
                VideoItemBinding binding = DataBindingUtil
                        .inflate(layoutInflater, R.layout.video_item, parent, false);
                binding.setListener(listener);
                return new VideoViewHolder(binding);

            case LINK_TYPE:
                LinkItemBinding binding2 = DataBindingUtil
                        .inflate(layoutInflater, R.layout.link_item, parent, false);
                binding2.setListener(listener);
                return new LinkViewHolder(binding2);

            default:
                throw new IllegalArgumentException("Invalid view type");
        }


    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.setViewModel(items.get(position));
    }



    @Override
    public int getItemViewType(int position) {
        if (items.get(position).getType().getValue().equals(Type.VIDEO))
            return VIDEO_TYPE;

        if (items.get(position).getType().getValue().equals(Type.LINK))
            return LINK_TYPE;

        throw new IllegalArgumentException("Invalid type " + position);
    }

    @Override
    public void onViewAttachedToWindow(@NonNull BaseViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        holder.bind();
    }



    @Override
    public void onViewDetachedFromWindow(@NonNull BaseViewHolder holder) {
        holder.unbind();
        super.onViewDetachedFromWindow(holder);
    }

    public void setValues(List<Entry> response) {
        items.clear();
        items.addAll(response);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {

        return items.size();
    }


    class VideoViewHolder extends BaseViewHolder {

        VideoItemBinding binding;

        public VideoViewHolder(@NonNull VideoItemBinding binding) {
            super(binding.getRoot());
        }

        @Override
        void bind() {
            if (binding == null) {
                binding = DataBindingUtil.bind(itemView);
            }
        }

        @Override
        void unbind() {
            if (binding != null) {
                binding.unbind();
            }
        }

        @Override
        void setViewModel(Entry model) {
            if (binding != null) {
                binding.setModel(model);
            }
        }
    }

    class LinkViewHolder extends BaseViewHolder {

        LinkItemBinding binding;

        public LinkViewHolder(@NonNull LinkItemBinding itemView) {
            super(itemView.getRoot());
        }

        @Override
        void bind() {
            if (binding == null) {
                binding = DataBindingUtil.bind(itemView);
           }
        }

        @Override
        void unbind() {
            if (binding != null) {
                binding.unbind();
            }
        }

        @Override
        void setViewModel(Entry model) {
            if (binding != null) {
                binding.setModel(model);
            }
        }
    }

    static abstract class BaseViewHolder extends RecyclerView.ViewHolder {

        public BaseViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        abstract void bind();
        abstract void unbind();
        abstract void setViewModel(Entry model);
    }


    public interface OnItemClickListener {
        void itemClicked(Entry item);
    }



}
