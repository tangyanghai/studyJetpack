package com.example.jetpackstudy.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jetpackstudy.R;
import com.example.jetpackstudy.repository.Word;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * <p>@author : tangyanghai</p>
 * <p>@time : 2020/9/17</p>
 * <p>@for : </p>
 * <p></p>
 */
public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {
    class WordViewHolder extends RecyclerView.ViewHolder{
        TextView wordItemView;

        public WordViewHolder(@NonNull View itemView) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.textView);
        }
    }

    private final LayoutInflater mInflater;
    private List<Word> mWords; // Cached copy of words

    public WordListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new WordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {
        if (mWords != null) {
            Word current = mWords.get(position);
            holder.wordItemView.setText(current.getWord());
        } else {
            // Covers the case of data not being ready yet.
            holder.wordItemView.setText("No Word");
        }
    }

    void setWords(List<Word> words){
        mWords = words;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mWords != null)
            return mWords.size();
        else return 0;
    }

}
