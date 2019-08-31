// Generated code from Butter Knife. Do not modify!
package com.example.vetservefirebase.ServiceProvider;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.cardview.widget.CardView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.vetservefirebase.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ProvidersMapView_ViewBinding implements Unbinder {
  private ProvidersMapView target;

  @UiThread
  public ProvidersMapView_ViewBinding(ProvidersMapView target, View source) {
    this.target = target;

    target.clinicName = Utils.findRequiredViewAsType(source, R.id.viewClinicname, "field 'clinicName'", TextView.class);
    target.clinicLocation = Utils.findRequiredViewAsType(source, R.id.viewCliniclocation, "field 'clinicLocation'", TextView.class);
    target.linkToClinicProfile = Utils.findRequiredViewAsType(source, R.id.linkToClinicProfile, "field 'linkToClinicProfile'", TextView.class);
    target.closeview = Utils.findRequiredViewAsType(source, R.id.closeview, "field 'closeview'", TextView.class);
    target.cardviewclinic = Utils.findRequiredViewAsType(source, R.id.cardviewclinic, "field 'cardviewclinic'", CardView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ProvidersMapView target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.clinicName = null;
    target.clinicLocation = null;
    target.linkToClinicProfile = null;
    target.closeview = null;
    target.cardviewclinic = null;
  }
}
