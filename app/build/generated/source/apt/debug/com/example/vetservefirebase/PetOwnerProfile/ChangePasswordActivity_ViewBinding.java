// Generated code from Butter Knife. Do not modify!
package com.example.vetservefirebase.PetOwnerProfile;

import android.view.View;
import android.widget.EditText;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.vetservefirebase.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ChangePasswordActivity_ViewBinding implements Unbinder {
  private ChangePasswordActivity target;

  private View view7f0a0036;

  @UiThread
  public ChangePasswordActivity_ViewBinding(ChangePasswordActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ChangePasswordActivity_ViewBinding(final ChangePasswordActivity target, View source) {
    this.target = target;

    View view;
    target.txtoldpassword = Utils.findRequiredViewAsType(source, R.id.txtoldpassword, "field 'txtoldpassword'", EditText.class);
    target.txtnewpassword = Utils.findRequiredViewAsType(source, R.id.txtnewpassword, "field 'txtnewpassword'", EditText.class);
    target.txtcnewpassword = Utils.findRequiredViewAsType(source, R.id.txtcnewpassword, "field 'txtcnewpassword'", EditText.class);
    view = Utils.findRequiredView(source, R.id.btnChangepass, "method 'changePass'");
    view7f0a0036 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.changePass();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    ChangePasswordActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.txtoldpassword = null;
    target.txtnewpassword = null;
    target.txtcnewpassword = null;

    view7f0a0036.setOnClickListener(null);
    view7f0a0036 = null;
  }
}
