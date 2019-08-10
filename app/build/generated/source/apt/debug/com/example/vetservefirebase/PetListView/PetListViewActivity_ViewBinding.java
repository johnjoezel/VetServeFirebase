// Generated code from Butter Knife. Do not modify!
package com.example.vetservefirebase.PetListView;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.vetservefirebase.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PetListViewActivity_ViewBinding implements Unbinder {
  private PetListViewActivity target;

  @UiThread
  public PetListViewActivity_ViewBinding(PetListViewActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public PetListViewActivity_ViewBinding(PetListViewActivity target, View source) {
    this.target = target;

    target.listofpets = Utils.findRequiredViewAsType(source, R.id.listofpet, "field 'listofpets'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    PetListViewActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.listofpets = null;
  }
}
