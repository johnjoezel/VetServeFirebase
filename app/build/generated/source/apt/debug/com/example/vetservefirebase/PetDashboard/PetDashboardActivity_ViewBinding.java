// Generated code from Butter Knife. Do not modify!
package com.example.vetservefirebase.PetDashboard;

import android.view.View;
import android.widget.ImageView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.vetservefirebase.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PetDashboardActivity_ViewBinding implements Unbinder {
  private PetDashboardActivity target;

  private View view7f0a0111;

  private View view7f0a00c3;

  private View view7f0a006e;

  @UiThread
  public PetDashboardActivity_ViewBinding(PetDashboardActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public PetDashboardActivity_ViewBinding(final PetDashboardActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.rightarrow, "field 'rightarrow' and method 'leftandright'");
    target.rightarrow = Utils.castView(view, R.id.rightarrow, "field 'rightarrow'", ImageView.class);
    view7f0a0111 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.leftandright(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.leftarrow, "field 'leftarrow' and method 'leftandright'");
    target.leftarrow = Utils.castView(view, R.id.leftarrow, "field 'leftarrow'", ImageView.class);
    view7f0a00c3 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.leftandright(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.editpet, "method 'leftandright' and method 'editpet'");
    view7f0a006e = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.leftandright(p0);
        target.editpet();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    PetDashboardActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rightarrow = null;
    target.leftarrow = null;

    view7f0a0111.setOnClickListener(null);
    view7f0a0111 = null;
    view7f0a00c3.setOnClickListener(null);
    view7f0a00c3 = null;
    view7f0a006e.setOnClickListener(null);
    view7f0a006e = null;
  }
}
