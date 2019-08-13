// Generated code from Butter Knife. Do not modify!
package com.example.vetservefirebase.SignUp;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.vetservefirebase.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class Personal_Information_ViewBinding implements Unbinder {
  private Personal_Information target;

  private View view7f0a005b;

  private View view7f0a003a;

  @UiThread
  public Personal_Information_ViewBinding(Personal_Information target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public Personal_Information_ViewBinding(final Personal_Information target, View source) {
    this.target = target;

    View view;
    target.txtfirstname = Utils.findRequiredViewAsType(source, R.id.txtfirstname, "field 'txtfirstname'", EditText.class);
    target.txtmname = Utils.findRequiredViewAsType(source, R.id.txtmname, "field 'txtmname'", EditText.class);
    target.txtlastname = Utils.findRequiredViewAsType(source, R.id.txtlastname, "field 'txtlastname'", EditText.class);
    target.txtcontact = Utils.findRequiredViewAsType(source, R.id.txtcontact, "field 'txtcontact'", EditText.class);
    target.txtaddress = Utils.findRequiredViewAsType(source, R.id.txtaddress, "field 'txtaddress'", EditText.class);
    view = Utils.findRequiredView(source, R.id.dateofbirth, "field 'dateofbirth' and method 'getDOB'");
    target.dateofbirth = Utils.castView(view, R.id.dateofbirth, "field 'dateofbirth'", TextView.class);
    view7f0a005b = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.getDOB();
      }
    });
    view = Utils.findRequiredView(source, R.id.btncontinue, "method 'toContinue'");
    view7f0a003a = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.toContinue();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    Personal_Information target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.txtfirstname = null;
    target.txtmname = null;
    target.txtlastname = null;
    target.txtcontact = null;
    target.txtaddress = null;
    target.dateofbirth = null;

    view7f0a005b.setOnClickListener(null);
    view7f0a005b = null;
    view7f0a003a.setOnClickListener(null);
    view7f0a003a = null;
  }
}
