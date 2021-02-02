package eu.sanjin.sightslocator.list.viewmodel;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import eu.sanjin.handlers.DaoHandler;
import eu.sanjin.model.Sight;
import eu.sanjin.sightslocator.list.adapter.ListViewHolder;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ListViewModel extends ViewModel {

  private MutableLiveData<ArrayList<Sight>> list;

  public void init(Context context) {
    if (list != null) {
      return;
    }
    list = new MutableLiveData<>(new ArrayList<>());
    loadSightListData(context);
  }

  public void loadSightListData(Context context) {
    //noinspection ResultOfMethodCallIgnored
    Observable.just(DaoHandler.getInstance(context).sightDao())
      .subscribeOn(Schedulers.io())
      .subscribe(db -> list.postValue((ArrayList<Sight>) db.getAll()));
  }

  public void bindSight(ListViewHolder view, int position) {
    Sight sight = list.getValue().get(position);
    view.setTitle(sight.getTitle());
    view.setImage(sight.getImageUrl());
  }

  public int getNumberOfItems() {
    return list.getValue().size();
  }

  public void deleteListData(Context context, Sight sight) {
    //noinspection ResultOfMethodCallIgnored
    Observable.just(DaoHandler.getInstance(context).sightDao())
      .subscribeOn(Schedulers.io())
      .subscribe(db -> {
        db.delete(sight);
        list.getValue().remove(sight);
      });
  }
}
