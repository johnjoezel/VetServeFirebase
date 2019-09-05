// Generated code from Butter Knife. Do not modify!
package com.example.vetservefirebase.ServiceProvider;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.vetservefirebase.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ServiceProviderFragment_ViewBinding implements Unbinder {
  private ServiceProviderFragment target;

  @UiThread
  public ServiceProviderFragment_ViewBinding(ServiceProviderFragment target, View source) {
    this.target = target;

    target.listofprovider = Utils.findRequiredViewAsType(source, R.id.listofprovider, "field 'listofprovider'", RecyclerView.class);
    target.searchProviders = Utils.findRequiredViewAsType(source, R.id.searchProviders, "field 'searchProviders'", Button.class);
    target.empty_view = Utils.findRequiredViewAsType(source, R.id.empty_view, "field 'empty_view'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ServiceProviderFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.listofprovider = null;
    target.searchProviders = null;
    target.empty_view = null;
  }
}
