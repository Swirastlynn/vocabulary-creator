package com.przemyslawlusnia.vocabularycreator.wordlist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.przemyslawlusnia.vocabularycreator.BaseFragment;
import com.przemyslawlusnia.vocabularycreator.R;
import com.przemyslawlusnia.vocabularycreator.VocabularyCreatorApplication;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class WordListFragment extends BaseFragment implements WordListView {

  @BindView(R.id.wordsRecyclerView)
  RecyclerView wordsRecyclerView;

  @Inject
  WordListPresenter presenter;
  @Inject
  WordListAdapter wordListAdapter;
  @Inject
  LinearLayoutManager linearLayoutManager;

  public static WordListFragment newInstance() {
    return new WordListFragment();
  }

  public WordListFragment() {
    // Required empty public constructor
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setupActivityComponent();
    presenter.onAttachView(this);
  }

  private void setupActivityComponent() {
    VocabularyCreatorApplication.get(getActivity())
        .getAppComponent()
        .plus(new WordListFragmentModule(this, (WordListActivity) getContext()))
        .inject(this);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.word_list_fragment, container, false);
    butterknifeUnbinder = ButterKnife.bind(this, view);
    return view;
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    wordsRecyclerView.setLayoutManager(linearLayoutManager);
    wordListAdapter.setWords(getTempWordList());
    wordsRecyclerView.setAdapter(wordListAdapter);
  }

  private List<Word> getTempWordList() {
    List<Word> list = new ArrayList<>();
    list.add(Word.create("new", "nowy", Word.TYPE_TRAINING));
    list.add(Word.create("old", "stary", Word.TYPE_TRAINING));
    list.add(Word.create("old Brohacz", "stary snowboardzista", Word.TYPE_TRAINING));
    list.add(Word.create("menacingly", "groźnie", Word.TYPE_TRAINING));
    list.add(Word.create("hush", "cicho-sza", Word.TYPE_TRAINING));
    list.add(Word.create("hush!", "cyt!", Word.TYPE_TRAINING));
    list.add(Word.create("exert", "wyciąg", Word.TYPE_TRAINING));
    list.add(Word.create("assure", "zapewniać, ubezpieczać", Word.TYPE_TRAINING));
    list.add(Word.create("assert", "zapewniać", Word.TYPE_TRAINING));
    list.add(Word.create("assess", "oszacować", Word.TYPE_TRAINING));
    list.add(Word.create("assessment", "oszacowanie", Word.TYPE_TRAINING));
    list.add(Word.create("swivel", "obracać się", Word.TYPE_LEARNED));
    return list;
  }

  @Override
  public void showProgress() {
    // not used
  }

  @Override
  public void hideProgress() {
    // not used
  }

  @Override
  public void showFailure(Throwable t) {
    // not used
  }

  @Override
  public void onPause() {
    presenter.onUnsubscribe();
    super.onPause();
  }

  @Override
  public void onDestroy() {
    presenter.onDestroy();
    super.onDestroy();
  }

}