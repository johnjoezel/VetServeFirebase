// Generated code from Butter Knife. Do not modify!
package com.example.vetservefirebase.Dashboard;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.vetservefirebase.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PetOwnerDashboardFragment_ViewBinding implements Unbinder {
  private PetOwnerDashboardFragment target;

  private View view7f0a0190;

  @UiThread
  public PetOwnerDashboardFragment_ViewBinding(final PetOwnerDashboardFragment target,
      View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.userProfpic, "field 'userProfpic' and method 'changePP'");
    target.userProfpic = Utils.castView(view, R.id.userProfpic, "field 'userProfpic'", ImageView.class);
    view7f0a0190 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.changePP();
      }
    });
    target.txtUsername = Utils.findRequiredViewAsType(source, R.id.displayusername, "field 'txtUsername'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    PetOwnerDashboardFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.userProfpic = null;
    target.txtUsername = null;

    view7f0a0190.setOnClickListener(null);
    view7f0a0190 = null;
  }
}
