// Generated code from Butter Knife. Do not modify!
package com.example.vetservefirebase.Others;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.vetservefirebase.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PetListAdapter$PetListViewHolder_ViewBinding implements Unbinder {
  private PetListAdapter.PetListViewHolder target;

  @UiThread
  public PetListAdapter$PetListViewHolder_ViewBinding(PetListAdapter.PetListViewHolder target,
      View source) {
    this.target = target;

    target.petname = Utils.findRequiredViewAsType(source, R.id.viewpetname, "field 'petname'", TextView.class);
    target.petbreed = Utils.findRequiredViewAsType(source, R.id.viewpetbreed, "field 'petbreed'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    PetListAdapter.PetListViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.petname = null;
    target.petbreed = null;
  }
}
