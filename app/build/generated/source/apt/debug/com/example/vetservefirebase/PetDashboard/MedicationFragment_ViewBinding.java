// Generated code from Butter Knife. Do not modify!
package com.example.vetservefirebase.PetDashboard;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.vetservefirebase.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MedicationFragment_ViewBinding implements Unbinder {
  private MedicationFragment target;

  @UiThread
  public MedicationFragment_ViewBinding(MedicationFragment target, View source) {
    this.target = target;

    target.medication = Utils.findRequiredViewAsType(source, R.id.medication, "field 'medication'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MedicationFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.medication = null;
  }
}
