// Generated code from Butter Knife. Do not modify!
package com.example.vetservefirebase.PetOwnerProfile;

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

public class ProfileFragment_ViewBinding implements Unbinder {
  private ProfileFragment target;

  private View view7f0a010d;

  private View view7f0a00ad;

  private View view7f0a00ae;

  private View view7f0a00af;

  private View view7f0a00b0;

  private View view7f0a00b1;

  private View view7f0a0037;

  @UiThread
  public ProfileFragment_ViewBinding(final ProfileFragment target, View source) {
    this.target = target;

    View view;
    target.firstname = Utils.findRequiredViewAsType(source, R.id.firstname, "field 'firstname'", TextView.class);
    target.middlename = Utils.findRequiredViewAsType(source, R.id.middlename, "field 'middlename'", TextView.class);
    target.lastname = Utils.findRequiredViewAsType(source, R.id.lastname, "field 'lastname'", TextView.class);
    target.contact = Utils.findRequiredViewAsType(source, R.id.contact, "field 'contact'", TextView.class);
    target.address = Utils.findRequiredViewAsType(source, R.id.address, "field 'address'", TextView.class);
    view = Utils.findRequiredView(source, R.id.profilepic, "field 'profilepic' and method 'toedit'");
    target.profilepic = Utils.castView(view, R.id.profilepic, "field 'profilepic'", ImageView.class);
    view7f0a010d = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.toedit(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.imageView2, "method 'toedit'");
    view7f0a00ad = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.toedit(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.imageView3, "method 'toedit'");
    view7f0a00ae = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.toedit(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.imageView4, "method 'toedit'");
    view7f0a00af = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.toedit(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.imageView5, "method 'toedit'");
    view7f0a00b0 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.toedit(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.imageView6, "method 'toedit'");
    view7f0a00b1 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.toedit(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btnUpdate, "method 'toedit'");
    view7f0a0037 = view;
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
    ProfileFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.firstname = null;
    target.middlename = null;
    target.lastname = null;
    target.contact = null;
    target.address = null;
    target.profilepic = null;

    view7f0a010d.setOnClickListener(null);
    view7f0a010d = null;
    view7f0a00ad.setOnClickListener(null);
    view7f0a00ad = null;
    view7f0a00ae.setOnClickListener(null);
    view7f0a00ae = null;
    view7f0a00af.setOnClickListener(null);
    view7f0a00af = null;
    view7f0a00b0.setOnClickListener(null);
    view7f0a00b0 = null;
    view7f0a00b1.setOnClickListener(null);
    view7f0a00b1 = null;
    view7f0a0037.setOnClickListener(null);
    view7f0a0037 = null;
  }
}
