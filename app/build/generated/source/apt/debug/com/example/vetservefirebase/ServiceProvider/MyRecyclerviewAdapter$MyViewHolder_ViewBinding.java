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

public class MyRecyclerviewAdapter$MyViewHolder_ViewBinding implements Unbinder {
  private MyRecyclerviewAdapter.MyViewHolder target;

  @UiThread
  public MyRecyclerviewAdapter$MyViewHolder_ViewBinding(MyRecyclerviewAdapter.MyViewHolder target,
      View source) {
    this.target = target;

    target.clinicName = Utils.findRequiredViewAsType(source, R.id.clinicName, "field 'clinicName'", TextView.class);
    target.clinicAddress = Utils.findRequiredViewAsType(source, R.id.clinicAddress, "field 'clinicAddress'", TextView.class);
    target.clinicHours = Utils.findRequiredViewAsType(source, R.id.clinicHours, "field 'clinicHours'", TextView.class);
    target.clinicPhone = Utils.findRequiredViewAsType(source, R.id.clinicPhone, "field 'clinicPhone'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MyRecyclerviewAdapter.MyViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.clinicName = null;
    target.clinicAddress = null;
    target.clinicHours = null;
    target.clinicPhone = null;
  }
}
