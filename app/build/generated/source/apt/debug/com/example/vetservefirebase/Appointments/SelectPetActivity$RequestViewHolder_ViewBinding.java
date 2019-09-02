// Generated code from Butter Knife. Do not modify!
package com.example.vetservefirebase.Appointments;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.vetservefirebase.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SelectPetActivity$RequestViewHolder_ViewBinding implements Unbinder {
  private SelectPetActivity.RequestViewHolder target;

  @UiThread
  public SelectPetActivity$RequestViewHolder_ViewBinding(SelectPetActivity.RequestViewHolder target,
      View source) {
    this.target = target;

    target.petImage = Utils.findRequiredViewAsType(source, R.id.petImage, "field 'petImage'", ImageView.class);
    target.viewpetname = Utils.findRequiredViewAsType(source, R.id.viewpetname, "field 'viewpetname'", TextView.class);
    target.viewpetbreed = Utils.findRequiredViewAsType(source, R.id.viewpetbreed, "field 'viewpetbreed'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SelectPetActivity.RequestViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.petImage = null;
    target.viewpetname = null;
    target.viewpetbreed = null;
  }
}
