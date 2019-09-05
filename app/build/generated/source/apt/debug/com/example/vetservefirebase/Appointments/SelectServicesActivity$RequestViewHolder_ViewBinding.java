// Generated code from Butter Knife. Do not modify!
package com.example.vetservefirebase.Appointments;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.vetservefirebase.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SelectServicesActivity$RequestViewHolder_ViewBinding implements Unbinder {
  private SelectServicesActivity.RequestViewHolder target;

  @UiThread
  public SelectServicesActivity$RequestViewHolder_ViewBinding(
      SelectServicesActivity.RequestViewHolder target, View source) {
    this.target = target;

    target.servicename = Utils.findRequiredViewAsType(source, R.id.servicename, "field 'servicename'", TextView.class);
    target.checkBox = Utils.findRequiredViewAsType(source, R.id.checkBox, "field 'checkBox'", CheckBox.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SelectServicesActivity.RequestViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.servicename = null;
    target.checkBox = null;
  }
}
