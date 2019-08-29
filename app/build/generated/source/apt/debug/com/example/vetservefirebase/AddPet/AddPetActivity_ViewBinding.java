// Generated code from Butter Knife. Do not modify!
package com.example.vetservefirebase.AddPet;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
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

  private View view7f0a014c;

  private View view7f0a014b;

  private View view7f0a0107;

  private View view7f0a0106;

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
    view7f0a014c = view;
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
    view7f0a014b = view;
    ((AdapterView<?>) view).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> p0, View p1, int p2, long p3) {
        target.onItemSelected(Utils.castParam(p0, "onItemSelected", 0, "onItemSelected", 0, Spinner.class), p2);
      }

      @Override
      public void onNothingSelected(AdapterView<?> p0) {
      }
    });
    target.spnrBreed = Utils.findRequiredViewAsType(source, R.id.spnrBreed, "field 'spnrBreed'", EditText.class);
    target.txtpetname = Utils.findRequiredViewAsType(source, R.id.txtpetname, "field 'txtpetname'", EditText.class);
    view = Utils.findRequiredView(source, R.id.petpicture, "field 'petpicture' and method 'addpetpic'");
    target.petpicture = Utils.castView(view, R.id.petpicture, "field 'petpicture'", ImageView.class);
    view7f0a0107 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.addpetpic();
      }
    });
    target.txtpetcolor = Utils.findRequiredViewAsType(source, R.id.petColor, "field 'txtpetcolor'", EditText.class);
    view = Utils.findRequiredView(source, R.id.petdateofbirth, "field 'txtpetDOB' and method 'getpetDOB'");
    target.txtpetDOB = Utils.castView(view, R.id.petdateofbirth, "field 'txtpetDOB'", TextView.class);
    view7f0a0106 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.getpetDOB();
      }
    });
    view = Utils.findRequiredView(source, R.id.btnaddpet, "field 'btnUpAdd' and method 'toadd'");
    target.btnUpAdd = Utils.castView(view, R.id.btnaddpet, "field 'btnUpAdd'", Button.class);
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
    target.btnUpAdd = null;

    ((AdapterView<?>) view7f0a014c).setOnItemSelectedListener(null);
    view7f0a014c = null;
    ((AdapterView<?>) view7f0a014b).setOnItemSelectedListener(null);
    view7f0a014b = null;
    view7f0a0107.setOnClickListener(null);
    view7f0a0107 = null;
    view7f0a0106.setOnClickListener(null);
    view7f0a0106 = null;
    view7f0a0039.setOnClickListener(null);
    view7f0a0039 = null;
  }
}
