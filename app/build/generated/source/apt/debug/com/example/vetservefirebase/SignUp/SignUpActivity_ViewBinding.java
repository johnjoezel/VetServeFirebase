// Generated code from Butter Knife. Do not modify!
package com.example.vetservefirebase.SignUp;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.vetservefirebase.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SignUpActivity_ViewBinding implements Unbinder {
  private SignUpActivity target;

  private View view7f0a00af;

  private View view7f0a0036;

  @UiThread
  public SignUpActivity_ViewBinding(SignUpActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SignUpActivity_ViewBinding(final SignUpActivity target, View source) {
    this.target = target;

    View view;
    target.progressBar = Utils.findRequiredViewAsType(source, R.id.progressBar, "field 'progressBar'", ProgressBar.class);
    target.email = Utils.findRequiredViewAsType(source, R.id.txtemail, "field 'email'", EditText.class);
    target.password = Utils.findRequiredViewAsType(source, R.id.txtpassword, "field 'password'", EditText.class);
    target.cpassword = Utils.findRequiredViewAsType(source, R.id.txtcpassword, "field 'cpassword'", EditText.class);
    target.displayname = Utils.findRequiredViewAsType(source, R.id.txtdisplayname, "field 'displayname'", EditText.class);
    view = Utils.findRequiredView(source, R.id.imgProfpic, "field 'imgProfpic' and method 'uploadpicture'");
    target.imgProfpic = Utils.castView(view, R.id.imgProfpic, "field 'imgProfpic'", ImageView.class);
    view7f0a00af = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.uploadpicture();
      }
    });
    view = Utils.findRequiredView(source, R.id.btnRegister, "method 'onSignUpButtonClick'");
    view7f0a0036 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onSignUpButtonClick();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    SignUpActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.progressBar = null;
    target.email = null;
    target.password = null;
    target.cpassword = null;
    target.displayname = null;
    target.imgProfpic = null;

    view7f0a00af.setOnClickListener(null);
    view7f0a00af = null;
    view7f0a0036.setOnClickListener(null);
    view7f0a0036 = null;
  }
}
