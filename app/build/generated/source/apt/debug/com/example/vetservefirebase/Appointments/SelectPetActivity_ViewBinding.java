// Generated code from Butter Knife. Do not modify!
package com.example.vetservefirebase.Appointments;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.vetservefirebase.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SelectPetActivity_ViewBinding implements Unbinder {
  private SelectPetActivity target;

  @UiThread
  public SelectPetActivity_ViewBinding(SelectPetActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SelectPetActivity_ViewBinding(SelectPetActivity target, View source) {
    this.target = target;

    target.listofpets = Utils.findRequiredViewAsType(source, R.id.listofpets, "field 'listofpets'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SelectPetActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.listofpets = null;
  }
}
