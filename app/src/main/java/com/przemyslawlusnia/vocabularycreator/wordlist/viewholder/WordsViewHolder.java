package com.przemyslawlusnia.vocabularycreator.wordlist.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.przemyslawlusnia.vocabularycreator.wordlist.ModifiableWord;

public abstract class WordsViewHolder extends RecyclerView.ViewHolder {

  public WordsViewHolder(View itemView) {
    super(itemView);
  }

  public abstract void bind(ModifiableWord word);

}