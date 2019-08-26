// Generated code from Butter Knife. Do not modify!
package com.example.vetservefirebase.SignIn;

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

public class SignInActivity_ViewBinding implements Unbinder {
  private SignInActivity target;

  private View view7f0a013b;

  private View view7f0a003b;

  @UiThread
  public SignInActivity_ViewBinding(SignInActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SignInActivity_ViewBinding(final SignInActivity target, View source) {
    this.target = target;

    View view;
    target.email = Utils.findRequiredViewAsType(source, R.id.signInEmail, "field 'email'", EditText.class);
    target.password = Utils.findRequiredViewAsType(source, R.id.signInPassword, "field 'password'", EditText.class);
    view = Utils.findRequiredView(source, R.id.signup, "method 'onSignUpButtonClick'");
    view7f0a013b = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onSignUpButtonClick();
      }
    });
    view = Utils.findRequiredView(source, R.id.btnlogin, "method 'onLoginButtonClick'");
    view7f0a003b = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onLoginButtonClick();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    SignInActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.email = null;
    target.password = null;

    view7f0a013b.setOnClickListener(null);
    view7f0a013b = null;
    view7f0a003b.setOnClickListener(null);
    view7f0a003b = null;
  }
}
