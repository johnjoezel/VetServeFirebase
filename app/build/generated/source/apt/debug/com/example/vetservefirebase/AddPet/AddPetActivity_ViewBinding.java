// Generated code from Butter Knife. Do not modify!
package com.example.vetservefirebase.AddPet;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.vetservefirebase.R;
import fr.ganfra.materialspinner.MaterialSpinner;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AddPetActivity_ViewBinding implements Unbinder {
  private AddPetActivity target;

  private View view7f0a0146;

  private View view7f0a0145;

  private View view7f0a0144;

  private View view7f0a0108;

  private View view7f0a0107;

  private View view7f0a0039;

  @UiThread
  public AddPetActivity_ViewBinding(AddPetActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AddPetActivity_ViewBinding(final AddPetActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.spnrSpecies, "field 'spnrSpecies' and method 'onItemSelected'");
    target.spnrSpecies = Utils.castView(view, R.id.spnrSpecies, "field 'spnrSpecies'", MaterialSpinner.class);
    view7f0a0146 = view;
    ((AdapterView<?>) view).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> p0, View p1, int p2, long p3) {
        target.onItemSelected(Utils.castParam(p0, "onItemSelected", 0, "onItemSelected", 0, Spinner.class), p2);
      }

      @Override
      public void onNothingSelected(AdapterView<?> p0) {
      }
    });
    view = Utils.findRequiredView(source, R.id.spnrGender, "field 'spnrGender' and method 'onItemSelected'");
    target.spnrGender = Utils.castView(view, R.id.spnrGender, "field 'spnrGender'", MaterialSpinner.class);
    view7f0a0145 = view;
    ((AdapterView<?>) view).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> p0, View p1, int p2, long p3) {
        target.onItemSelected(Utils.castParam(p0, "onItemSelected", 0, "onItemSelected", 0, Spinner.class), p2);
      }

      @Override
      public void onNothingSelected(AdapterView<?> p0) {
      }
    });
    view = Utils.findRequiredView(source, R.id.spnrBreed, "field 'spnrBreed' and method 'onItemSelected'");
    target.spnrBreed = Utils.castView(view, R.id.spnrBreed, "field 'spnrBreed'", MaterialSpinner.class);
    view7f0a0144 = view;
    ((AdapterView<?>) view).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> p0, View p1, int p2, long p3) {
        target.onItemSelected(Utils.castParam(p0, "onItemSelected", 0, "onItemSelected", 0, Spinner.class), p2);
      }

      @Override
      public void onNothingSelected(AdapterView<?> p0) {
      }
    });
    target.txtpetname = Utils.findRequiredViewAsType(source, R.id.txtpetname, "field 'txtpetname'", EditText.class);
    view = Utils.findRequiredView(source, R.id.petpicture, "field 'petpicture' and method 'addpetpic'");
    target.petpicture = Utils.castView(view, R.id.petpicture, "field 'petpicture'", ImageView.class);
    view7f0a0108 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.addpetpic();
      }
    });
    target.txtpetcolor = Utils.findRequiredViewAsType(source, R.id.petColor, "field 'txtpetcolor'", EditText.class);
    view = Utils.findRequiredView(source, R.id.petdateofbirth, "field 'txtpetDOB' and method 'getpetDOB'");
    target.txtpetDOB = Utils.castView(view, R.id.petdateofbirth, "field 'txtpetDOB'", TextView.class);
    view7f0a0107 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.getpetDOB();
      }
    });
    view = Utils.findRequiredView(source, R.id.btnaddpet, "method 'toadd'");
    view7f0a0039 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.toadd();
      }
    });

    Context context = source.getContext();
    Resources res = context.getResources();
    target.speciesArray = res.getStringArray(R.array.spinner_species_items);
  }

  @Override
  @CallSuper
  public void unbind() {
    AddPetActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.spnrSpecies = null;
    target.spnrGender = null;
    target.spnrBreed = null;
    target.txtpetname = null;
    target.petpicture = null;
    target.txtpetcolor = null;
    target.txtpetDOB = null;

    ((AdapterView<?>) view7f0a0146).setOnItemSelectedListener(null);
    view7f0a0146 = null;
    ((AdapterView<?>) view7f0a0145).setOnItemSelectedListener(null);
    view7f0a0145 = null;
    ((AdapterView<?>) view7f0a0144).setOnItemSelectedListener(null);
    view7f0a0144 = null;
    view7f0a0108.setOnClickListener(null);
    view7f0a0108 = null;
    view7f0a0107.setOnClickListener(null);
    view7f0a0107 = null;
    view7f0a0039.setOnClickListener(null);
    view7f0a0039 = null;
  }
}
