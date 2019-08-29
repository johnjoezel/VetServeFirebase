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

public class ProvidersListView_ViewBinding implements Unbinder {
  private ProvidersListView target;

  @UiThread
  public ProvidersListView_ViewBinding(ProvidersListView target, View source) {
    this.target = target;

    target.searchProviders = Utils.findRequiredViewAsType(source, R.id.searchProviders, "field 'searchProviders'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ProvidersListView target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.searchProviders = null;
  }
}
