// Generated code from Butter Knife. Do not modify!
package com.example.vetservefirebase.ServiceProvider;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.vetservefirebase.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ServiceProvidersList_ViewBinding implements Unbinder {
  private ServiceProvidersList target;

  @UiThread
  public ServiceProvidersList_ViewBinding(ServiceProvidersList target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ServiceProvidersList_ViewBinding(ServiceProvidersList target, View source) {
    this.target = target;

    target.searchProviders = Utils.findRequiredViewAsType(source, R.id.searchProviders, "field 'searchProviders'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ServiceProvidersList target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.searchProviders = null;
  }
}
