// Generated code from Butter Knife. Do not modify!
package com.example.vetservefirebase.PetOwner;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.vetservefirebase.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class EditProfileFragment_ViewBinding implements Unbinder {
  private EditProfileFragment target;

  private View view7f0a0102;

  private View view7f0a00a7;

  private View view7f0a00a8;

  private View view7f0a00a9;

  private View view7f0a00aa;

  private View view7f0a00ab;

  private View view7f0a003b;

  @UiThread
  public EditProfileFragment_ViewBinding(final EditProfileFragment target, View source) {
    this.target = target;

    View view;
    target.firstname = Utils.findRequiredViewAsType(source, R.id.firstname, "field 'firstname'", TextView.class);
    target.middlename = Utils.findRequiredViewAsType(source, R.id.middlename, "field 'middlename'", TextView.class);
    target.lastname = Utils.findRequiredViewAsType(source, R.id.lastname, "field 'lastname'", TextView.class);
    target.contact = Utils.findRequiredViewAsType(source, R.id.contact, "field 'contact'", TextView.class);
    target.address = Utils.findRequiredViewAsType(source, R.id.address, "field 'address'", TextView.class);
    view = Utils.findRequiredView(source, R.id.profilepic, "field 'profilepic' and method 'toedit'");
    target.profilepic = Utils.castView(view, R.id.profilepic, "field 'profilepic'", ImageView.class);
    view7f0a0102 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.toedit(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.imageView2, "method 'toedit'");
    view7f0a00a7 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.toedit(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.imageView3, "method 'toedit'");
    view7f0a00a8 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.toedit(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.imageView4, "method 'toedit'");
    view7f0a00a9 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.toedit(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.imageView5, "method 'toedit'");
    view7f0a00aa = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.toedit(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.imageView6, "method 'toedit'");
    view7f0a00ab = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.toedit(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btnUpdate, "method 'toedit'");
    view7f0a003b = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.toedit(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    EditProfileFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.firstname = null;
    target.middlename = null;
    target.lastname = null;
    target.contact = null;
    target.address = null;
    target.profilepic = null;

    view7f0a0102.setOnClickListener(null);
    view7f0a0102 = null;
    view7f0a00a7.setOnClickListener(null);
    view7f0a00a7 = null;
    view7f0a00a8.setOnClickListener(null);
    view7f0a00a8 = null;
    view7f0a00a9.setOnClickListener(null);
    view7f0a00a9 = null;
    view7f0a00aa.setOnClickListener(null);
    view7f0a00aa = null;
    view7f0a00ab.setOnClickListener(null);
    view7f0a00ab = null;
    view7f0a003b.setOnClickListener(null);
    view7f0a003b = null;
  }
}
