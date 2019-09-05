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

public class GeneralFragment_ViewBinding implements Unbinder {
  private GeneralFragment target;

  @UiThread
  public GeneralFragment_ViewBinding(GeneralFragment target, View source) {
    this.target = target;

    target.displaybreed = Utils.findRequiredViewAsType(source, R.id.breedname, "field 'displaybreed'", TextView.class);
    target.displaygender = Utils.findRequiredViewAsType(source, R.id.gender, "field 'displaygender'", TextView.class);
    target.displaypetage = Utils.findRequiredViewAsType(source, R.id.displaypetage, "field 'displaypetage'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    GeneralFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.displaybreed = null;
    target.displaygender = null;
    target.displaypetage = null;
  }
}
