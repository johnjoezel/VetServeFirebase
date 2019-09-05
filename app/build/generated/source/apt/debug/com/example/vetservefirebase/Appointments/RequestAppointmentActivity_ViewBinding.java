// Generated code from Butter Knife. Do not modify!
package com.example.vetservefirebase.Appointments;

import android.view.View;
import android.widget.EditText;
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

public class RequestAppointmentActivity_ViewBinding implements Unbinder {
  private RequestAppointmentActivity target;

  private View view7f0a0025;

  private View view7f0a0028;

  private View view7f0a002a;

  private View view7f0a00d0;

  private View view7f0a003c;

  @UiThread
  public RequestAppointmentActivity_ViewBinding(RequestAppointmentActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public RequestAppointmentActivity_ViewBinding(final RequestAppointmentActivity target,
      View source) {
    this.target = target;

    View view;
    target.petpicture = Utils.findRequiredViewAsType(source, R.id.petpicture, "field 'petpicture'", ImageView.class);
    target.petname = Utils.findRequiredViewAsType(source, R.id.petname, "field 'petname'", TextView.class);
    view = Utils.findRequiredView(source, R.id.appointmentDate, "field 'appointmentDate' and method 'onClicks'");
    target.appointmentDate = Utils.castView(view, R.id.appointmentDate, "field 'appointmentDate'", TextView.class);
    view7f0a0025 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClicks(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.appointmentServices, "field 'appointmentServices' and method 'onClicks'");
    target.appointmentServices = Utils.castView(view, R.id.appointmentServices, "field 'appointmentServices'", TextView.class);
    view7f0a0028 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClicks(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.appointmentTime, "field 'appointmentTime' and method 'onClicks'");
    target.appointmentTime = Utils.castView(view, R.id.appointmentTime, "field 'appointmentTime'", TextView.class);
    view7f0a002a = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClicks(p0);
      }
    });
    target.appointmentNotes = Utils.findRequiredViewAsType(source, R.id.appointmentNotes, "field 'appointmentNotes'", EditText.class);
    view = Utils.findRequiredView(source, R.id.linearLayout2, "method 'onClicks'");
    view7f0a00d0 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClicks(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btnRequest, "method 'onClicks'");
    view7f0a003c = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClicks(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    RequestAppointmentActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.petpicture = null;
    target.petname = null;
    target.appointmentDate = null;
    target.appointmentServices = null;
    target.appointmentTime = null;
    target.appointmentNotes = null;

    view7f0a0025.setOnClickListener(null);
    view7f0a0025 = null;
    view7f0a0028.setOnClickListener(null);
    view7f0a0028 = null;
    view7f0a002a.setOnClickListener(null);
    view7f0a002a = null;
    view7f0a00d0.setOnClickListener(null);
    view7f0a00d0 = null;
    view7f0a003c.setOnClickListener(null);
    view7f0a003c = null;
  }
}
