package hw.applicaster.vova.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import hw.applicaster.vova.R;
import hw.applicaster.vova.databinding.LinkItemBinding;
import hw.applicaster.vova.databinding.VideoItemBinding;
import hw.applicaster.vova.network.model.Entry;
import hw.applicaster.vova.network.model.general.Type;

public class ApplicasterRecyclerAdapter extends RecyclerView.Adapter<ApplicasterRecyclerAdapter.BaseViewHolder> implements Filterable {

    private static final int VIDEO_TYPE = 0;
    private static final int LINK_TYPE = 1;

    private static final String TAG = "ApplicasterRecyclerAdapter";
    private  List<Entry> itemsFilterd;
    private final List<Entry> items;
    private final OnItemClickListener listener;

    public ApplicasterRecyclerAdapter(OnItemClickListener listener) {
        itemsFilterd = new ArrayList<>();
        items = new ArrayList<>();
        this.listener = listener;
    }

    @NonNull
    @Override
    public ApplicasterRecyclerAdapter.BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
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
        holder.setViewModel(itemsFilterd.get(position));
    }


    @Override
    public int getItemViewType(int position) {
        if (itemsFilterd.get(position).getType().getValue().equals(Type.VIDEO))
            return VIDEO_TYPE;

        if (itemsFilterd.get(position).getType().getValue().equals(Type.LINK))
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
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new MyDiffCallback(response, itemsFilterd));
        itemsFilterd.clear();
        itemsFilterd.addAll(response);
        items.clear();
        items.addAll(response);
        diffResult.dispatchUpdatesTo(this);
    }

    @Override
    public int getItemCount() {

        return itemsFilterd.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                List<Entry> filteredList = new ArrayList<>();

                if (charString.isEmpty()) {
                    filteredList = items;
                } else {
                    for (Entry entry : items) {

                        // name match condition -here we are looking for name or phone number match
                        if (entry.getTitle().toLowerCase().contains(charString.toLowerCase()) ) {
                            filteredList.add(entry);
                        }
                    }

                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                itemsFilterd = (ArrayList<Entry>) filterResults.values;
                notifyDataSetChanged();



            }
        };
    }


    class VideoViewHolder extends BaseViewHolder {

        VideoItemBinding binding;

        private VideoViewHolder(@NonNull VideoItemBinding binding) {
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
                String src = model.getMediaGroup().get(0).getMediaItem().get(0).getSrc();
                Glide.with(binding.thumbnail.getContext()).load(src).apply(new RequestOptions().error(R.drawable.error_image).placeholder(R.drawable.loding_image).fitCenter()).into(binding.thumbnail);
            }
        }
    }

    class LinkViewHolder extends BaseViewHolder {

        LinkItemBinding binding;

        private LinkViewHolder(@NonNull LinkItemBinding itemView) {
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
        void setViewModel(@NonNull Entry model) {
            if (binding != null) {
                binding.setModel(model);
                String src = model.getMediaGroup().get(0).getMediaItem().get(0).getSrc();
                Glide.with(binding.thumbnail.getContext()).load(src).apply(new RequestOptions().error(R.drawable.error_image).placeholder(R.drawable.loding_image).fitCenter()).into(binding.thumbnail);
            }
        }
    }

    abstract class BaseViewHolder extends RecyclerView.ViewHolder {

        private BaseViewHolder(@NonNull View itemView) {
            super(itemView);
            bind();
        }

        abstract void bind();

        abstract void unbind();

        abstract void setViewModel(Entry model);
    }


    public interface OnItemClickListener {
        void itemClicked(Entry item);
    }

    class MyDiffCallback extends DiffUtil.Callback {

        List<Entry> oldEntries;
        List<Entry> newEntries;

        private MyDiffCallback(List<Entry> newEntries, List<Entry> oldEntries) {
            this.newEntries = newEntries;
            this.oldEntries = oldEntries;
        }

        @Override
        public int getOldListSize() {
            return oldEntries.size();
        }

        @Override
        public int getNewListSize() {
            return newEntries.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return oldEntries.get(oldItemPosition).getType().getValue().equals(newEntries.get(oldItemPosition).getType().getValue());
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return oldEntries.get(oldItemPosition).getId().equals(newEntries.get(oldItemPosition).getId());
        }

        @Nullable
        @Override
        public Object getChangePayload(int oldItemPosition, int newItemPosition) {
            //you can return particular field for changed item.
            return super.getChangePayload(oldItemPosition, newItemPosition);
        }


    }


}
