// Generated code from Butter Knife. Do not modify!
package com.example.vetservefirebase.ServiceProvider;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;

import com.example.vetservefirebase.PetDashboard.PetAppointmentAdapter;
import com.example.vetservefirebase.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PetAppointmentAdapter$MyViewHolder_ViewBinding implements Unbinder {
  private PetAppointmentAdapter.MyViewHolder target;

  @UiThread
  public PetAppointmentAdapter$MyViewHolder_ViewBinding(PetAppointmentAdapter.MyViewHolder target,
      View source) {
    this.target = target;

    target.providerName = Utils.findRequiredViewAsType(source, R.id.providerName, "field 'providerName'", TextView.class);
    target.appointmentDateTime = Utils.findRequiredViewAsType(source, R.id.appointmentDateTime, "field 'appointmentDateTime'", TextView.class);
    target.appointmentServices = Utils.findRequiredViewAsType(source, R.id.appointmentServices, "field 'appointmentServices'", TextView.class);
    target.appointmentStatus = Utils.findRequiredViewAsType(source, R.id.appointmentStatus, "field 'appointmentStatus'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    PetAppointmentAdapter.MyViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.providerName = null;
    target.appointmentDateTime = null;
    target.appointmentServices = null;
    target.appointmentStatus = null;
  }
}
