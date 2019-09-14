// Generated code from Butter Knife. Do not modify!
package com.example.vetservefirebase.Appointments;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.vetservefirebase.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SelectServicesActivity_ViewBinding implements Unbinder {
  private SelectServicesActivity target;

  private View view7f0a014c;

  @UiThread
  public SelectServicesActivity_ViewBinding(SelectServicesActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SelectServicesActivity_ViewBinding(final SelectServicesActivity target, View source) {
    this.target = target;

    View view;
    target.services = Utils.findRequiredViewAsType(source, R.id.services, "field 'services'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.submitServices, "method 'toSubmit'");
    view7f0a014c = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.toSubmit();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    SelectServicesActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.services = null;

    view7f0a014c.setOnClickListener(null);
    view7f0a014c = null;
  }
}
