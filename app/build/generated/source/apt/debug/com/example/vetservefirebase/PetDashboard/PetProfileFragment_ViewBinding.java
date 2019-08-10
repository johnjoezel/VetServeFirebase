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

public class PetProfileFragment_ViewBinding implements Unbinder {
  private PetProfileFragment target;

  @UiThread
  public PetProfileFragment_ViewBinding(PetProfileFragment target, View source) {
    this.target = target;

    target.displaypetname = Utils.findRequiredViewAsType(source, R.id.displaypetname, "field 'displaypetname'", TextView.class);
    target.displaybreed = Utils.findRequiredViewAsType(source, R.id.breedname, "field 'displaybreed'", TextView.class);
    target.displaygender = Utils.findRequiredViewAsType(source, R.id.gender, "field 'displaygender'", TextView.class);
    target.displayweight = Utils.findRequiredViewAsType(source, R.id.weight, "field 'displayweight'", TextView.class);
    target.displayheight = Utils.findRequiredViewAsType(source, R.id.height, "field 'displayheight'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    PetProfileFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.displaypetname = null;
    target.displaybreed = null;
    target.displaygender = null;
    target.displayweight = null;
    target.displayheight = null;
  }
}
