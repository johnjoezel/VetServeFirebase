// Generated code from Butter Knife. Do not modify!
package com.example.vetservefirebase.ServiceProvider;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.vetservefirebase.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ProvidersListView$RequestViewHolder_ViewBinding implements Unbinder {
  private ProvidersListView.RequestViewHolder target;

  @UiThread
  public ProvidersListView$RequestViewHolder_ViewBinding(ProvidersListView.RequestViewHolder target,
      View source) {
    this.target = target;

    target.viewClinicname = Utils.findRequiredViewAsType(source, R.id.viewClinicname, "field 'viewClinicname'", TextView.class);
    target.viewCliniclocation = Utils.findRequiredViewAsType(source, R.id.viewCliniclocation, "field 'viewCliniclocation'", TextView.class);
    target.linkToClinicProfile = Utils.findRequiredViewAsType(source, R.id.linkToClinicProfile, "field 'linkToClinicProfile'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ProvidersListView.RequestViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.viewClinicname = null;
    target.viewCliniclocation = null;
    target.linkToClinicProfile = null;
  }
}