package eu.sanjin.sightslocator.list.viewmodel;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Objects;

import eu.sanjin.contracts.ViewHolderContract;
import eu.sanjin.handlers.DaoHandler;
import eu.sanjin.handlers.PreferenceHandler;
import eu.sanjin.handlers.PreferenceKey;
import eu.sanjin.model.Sight;
import eu.sanjin.sightslocator.list.helper.ListTouchListener;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import lombok.Getter;

public class ListViewModel extends ViewModel {

  @Getter
  private MutableLiveData<ArrayList<Sight>> list;
  private ListTouchListener listener;

  public void init(ListTouchListener listener) {
    if (list != null) {
      return;
    }
    this.listener = listener;

    list = new MutableLiveData<>(new ArrayList<>());
  }

  public void loadSightListData(Context context) {
    //noinspection ResultOfMethodCallIgnored
    Observable.just(DaoHandler.getInstance(context).sightDao())
      .subscribeOn(Schedulers.io())
      .subscribe(db -> list.postValue((ArrayList<Sight>) db.getAll()));
  }

  public void bindSight(ViewHolderContract view, int position) {
    Sight sight = Objects.requireNonNull(list.getValue()).get(position);
    view.setTitle(sight.getTitle());
    view.setImage(sight.getImageUrl());
    view.setDescription(sight.getDescription());
  }

  public int getNumberOfItems() {
    return Objects.requireNonNull(list.getValue()).size();
  }

  public void deleteListData(Context context, int position) {
    Sight sight = Objects.requireNonNull(list.getValue()).get(position);
    //noinspection ResultOfMethodCallIgnored
    Observable.just(DaoHandler.getInstance(context).sightDao())
      .subscribeOn(Schedulers.io())
      .subscribe(db -> {
        db.delete(sight);

        ArrayList<Sight> sights = list.getValue();
        sights.remove(sight);
        list.postValue(sights);
      });
  }

  public void view(Context context, int position) {
    if (!Objects.isNull(listener)) {
      PreferenceHandler.setIntPreference(context, PreferenceKey.SIGHT_ITEM_POSITION, position);
      listener.onItemClick();
    }
  }
}
