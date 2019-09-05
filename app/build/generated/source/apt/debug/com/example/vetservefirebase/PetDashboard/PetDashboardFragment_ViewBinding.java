// Generated code from Butter Knife. Do not modify!
package com.example.vetservefirebase.PetDashboard;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.vetservefirebase.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PetDashboardFragment_ViewBinding implements Unbinder {
  private PetDashboardFragment target;

  private View view7f0a011e;

  private View view7f0a00c8;

  private View view7f0a007c;

  @UiThread
  public PetDashboardFragment_ViewBinding(final PetDashboardFragment target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.rightarrow, "field 'rightarrow' and method 'leftandright'");
    target.rightarrow = Utils.castView(view, R.id.rightarrow, "field 'rightarrow'", ImageView.class);
    view7f0a011e = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.leftandright(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.leftarrow, "field 'leftarrow' and method 'leftandright'");
    target.leftarrow = Utils.castView(view, R.id.leftarrow, "field 'leftarrow'", ImageView.class);
    view7f0a00c8 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.leftandright(p0);
      }
    });
    target.swiperLayout = Utils.findRequiredViewAsType(source, R.id.swiperLayout, "field 'swiperLayout'", RelativeLayout.class);
    target.blanklayout = Utils.findRequiredViewAsType(source, R.id.blanklayout, "field 'blanklayout'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.editpet, "field 'editpet', method 'leftandright', and method 'editpet'");
    target.editpet = Utils.castView(view, R.id.editpet, "field 'editpet'", Button.class);
    view7f0a007c = view;
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
    PetDashboardFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rightarrow = null;
    target.leftarrow = null;
    target.swiperLayout = null;
    target.blanklayout = null;
    target.editpet = null;

    view7f0a011e.setOnClickListener(null);
    view7f0a011e = null;
    view7f0a00c8.setOnClickListener(null);
    view7f0a00c8 = null;
    view7f0a007c.setOnClickListener(null);
    view7f0a007c = null;
  }
}
