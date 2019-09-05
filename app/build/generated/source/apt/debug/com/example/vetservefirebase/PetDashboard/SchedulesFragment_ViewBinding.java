// Generated code from Butter Knife. Do not modify!
package com.example.vetservefirebase.PetDashboard;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.vetservefirebase.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SchedulesFragment_ViewBinding implements Unbinder {
  private SchedulesFragment target;

  @UiThread
  public SchedulesFragment_ViewBinding(SchedulesFragment target, View source) {
    this.target = target;

    target.petAppointmentList = Utils.findRequiredViewAsType(source, R.id.petAppointmentList, "field 'petAppointmentList'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SchedulesFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.petAppointmentList = null;
  }
}
