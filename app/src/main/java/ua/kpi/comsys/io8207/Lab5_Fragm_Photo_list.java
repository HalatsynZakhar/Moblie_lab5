package ua.kpi.comsys.io8207;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.Guideline;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Lab5_Fragm_Photo_list extends Fragment {
    private static final int RESULT_LOAD_IMAGE = 2;

    private View view;
    private ScrollView scroll_view;
    private LinearLayout linear_layout;
    private ArrayList<ImageView> imgs;
    private ArrayList<ArrayList<Object>> Photo_adds;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup cont, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.lab5_photolist, cont, false);

        scroll_view = view.findViewById(R.id.photo_list);
        linear_layout = view.findViewById(R.id.linearImage);

        imgs = new ArrayList<>();
        Photo_adds = new ArrayList<>();

        FloatingActionButton addPhotoButton = view.findViewById(R.id.ImageAdd);
        addPhotoButton.setOnClickListener(v -> {
            Intent galleryImages = new Intent(Intent.ACTION_GET_CONTENT);
            galleryImages.setType("image/*");
            startActivityForResult(galleryImages, RESULT_LOAD_IMAGE);
        });
        correctLocationOfImage();
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == Activity.RESULT_OK) {
            Uri imageUri = data.getData();
            add_img(linear_layout, scroll_view, imageUri, imgs, Photo_adds);
        }
    }

    private void add_img(LinearLayout linearLayout, ScrollView scrollView, Uri UriImage, ArrayList<ImageView> images_list, ArrayList<ArrayList<Object>> addImage_list) {
        ImageView Image_new = new ImageView(view.getContext());
        Image_new.setImageURI(UriImage);

        Image_new.setScaleType(ImageView.ScaleType.CENTER_CROP);
        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_CONSTRAINT,
                ConstraintLayout.LayoutParams.MATCH_CONSTRAINT);
        params.setMargins(5, 5, 5, 5);
        params.dimensionRatio = "1";
        Image_new.setLayoutParams(params);
        Image_new.setId(Image_new.hashCode());

        ConstraintLayout layout = null;
        ConstraintSet set = null;
        if (images_list.size() > 0) {
            layout = (ConstraintLayout) getConstraintArrayList(0, addImage_list);
            set = (ConstraintSet) getConstraintArrayList(1, addImage_list);

            set.clone(layout);

            set.setMargin(Image_new.getId(), ConstraintSet.START, 5);
            set.setMargin(Image_new.getId(), ConstraintSet.TOP, 5);
            set.setMargin(Image_new.getId(), ConstraintSet.END, 5);
            set.setMargin(Image_new.getId(), ConstraintSet.BOTTOM, 5);
        }

        if (images_list.size() % 7 != 0) {
            layout.addView(Image_new);
        }

        switch (images_list.size() % 7) {
            case 0: {
                addImage_list.add(new ArrayList<>());

                ConstraintLayout constraint = new ConstraintLayout(view.getContext());
                addImage_list.get(addImage_list.size()-1).add(constraint);
                constraint.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));
                linearLayout.addView(constraint);

                Guideline vertical0 = makeGuideline(ConstraintLayout.LayoutParams.VERTICAL, 0f);
                Guideline vertical20 = makeGuideline(ConstraintLayout.LayoutParams.VERTICAL, 0.2f);
                Guideline vertical40 = makeGuideline(ConstraintLayout.LayoutParams.VERTICAL, 0.4f);
                Guideline vertical60 = makeGuideline(ConstraintLayout.LayoutParams.VERTICAL, 0.6f);
                Guideline vertical80 = makeGuideline(ConstraintLayout.LayoutParams.VERTICAL, 0.8f);
                Guideline vertical100 = makeGuideline(ConstraintLayout.LayoutParams.VERTICAL, 1f);

                Guideline horizontal0 = makeGuideline(ConstraintLayout.LayoutParams.HORIZONTAL, 0f);
                Guideline horizontal33 = makeGuideline(ConstraintLayout.LayoutParams.HORIZONTAL, 0.33f);
                Guideline horizontal66 = makeGuideline(ConstraintLayout.LayoutParams.HORIZONTAL, 0.66f);
                Guideline horizontal100 = makeGuideline(ConstraintLayout.LayoutParams.HORIZONTAL, 1f);

                constraint.addView(vertical0, 0);
                constraint.addView(vertical20, 1);
                constraint.addView(vertical40, 2);
                constraint.addView(vertical60, 3);
                constraint.addView(vertical80, 4);
                constraint.addView(vertical100, 5);

                constraint.addView(horizontal0, 6);
                constraint.addView(horizontal33, 7);
                constraint.addView(horizontal66, 8);
                constraint.addView(horizontal100, 9);


                constraint.addView(Image_new);

                ConstraintSet constraintSet = new ConstraintSet();
                addImage_list.get(addImage_list.size()-1).add(constraintSet);
                constraintSet.clone(constraint);

                constraintSet.connect(Image_new.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START);
                constraintSet.connect(Image_new.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP);
                constraintSet.connect(Image_new.getId(), ConstraintSet.END, vertical20.getId(), ConstraintSet.START);
                constraintSet.connect(Image_new.getId(), ConstraintSet.BOTTOM, horizontal33.getId(), ConstraintSet.TOP);

                constraintSet.applyTo(constraint);
                break;
            }

            case 1: {
                set.connect(Image_new.getId(), ConstraintSet.START, layout.getChildAt(1).getId(), ConstraintSet.END);
                set.connect(Image_new.getId(), ConstraintSet.TOP, layout.getChildAt(6).getId(), ConstraintSet.TOP);
                set.connect(Image_new.getId(), ConstraintSet.END, layout.getChildAt(4).getId(), ConstraintSet.END);
                set.connect(Image_new.getId(), ConstraintSet.BOTTOM, layout.getChildAt(9).getId(), ConstraintSet.TOP);

                set.applyTo(layout);
                break;
            }

            case 2: {
                set.connect(Image_new.getId(), ConstraintSet.START, layout.getChildAt(4).getId(), ConstraintSet.END);
                set.connect(Image_new.getId(), ConstraintSet.TOP, layout.getChildAt(6).getId(), ConstraintSet.TOP);
                set.connect(Image_new.getId(), ConstraintSet.END, layout.getChildAt(5).getId(), ConstraintSet.END);
                set.connect(Image_new.getId(), ConstraintSet.BOTTOM, layout.getChildAt(7).getId(), ConstraintSet.TOP);

                set.applyTo(layout);
                break;
            }

            case 3: {
                set.connect(Image_new.getId(), ConstraintSet.START, layout.getChildAt(0).getId(), ConstraintSet.END);
                set.connect(Image_new.getId(), ConstraintSet.TOP, layout.getChildAt(7).getId(), ConstraintSet.TOP);
                set.connect(Image_new.getId(), ConstraintSet.END, layout.getChildAt(1).getId(), ConstraintSet.END);
                set.connect(Image_new.getId(), ConstraintSet.BOTTOM, layout.getChildAt(8).getId(), ConstraintSet.TOP);

                set.applyTo(layout);
                break;
            }

            case 4: {
                set.connect(Image_new.getId(), ConstraintSet.START, layout.getChildAt(0).getId(), ConstraintSet.END);
                set.connect(Image_new.getId(), ConstraintSet.TOP, layout.getChildAt(8).getId(), ConstraintSet.TOP);
                set.connect(Image_new.getId(), ConstraintSet.END, layout.getChildAt(1).getId(), ConstraintSet.END);
                set.connect(Image_new.getId(), ConstraintSet.BOTTOM, layout.getChildAt(9).getId(), ConstraintSet.TOP);

                set.applyTo(layout);
                break;
            }

            case 5: {
                set.connect(Image_new.getId(), ConstraintSet.START, layout.getChildAt(4).getId(), ConstraintSet.END);
                set.connect(Image_new.getId(), ConstraintSet.TOP, layout.getChildAt(7).getId(), ConstraintSet.TOP);
                set.connect(Image_new.getId(), ConstraintSet.END, layout.getChildAt(5).getId(), ConstraintSet.END);
                set.connect(Image_new.getId(), ConstraintSet.BOTTOM, layout.getChildAt(8).getId(), ConstraintSet.TOP);

                set.applyTo(layout);
                break;
            }

            case 6: {
                set.connect(Image_new.getId(), ConstraintSet.START, layout.getChildAt(4).getId(), ConstraintSet.END);
                set.connect(Image_new.getId(), ConstraintSet.TOP, layout.getChildAt(8).getId(), ConstraintSet.TOP);
                set.connect(Image_new.getId(), ConstraintSet.END, layout.getChildAt(5).getId(), ConstraintSet.END);
                set.connect(Image_new.getId(), ConstraintSet.BOTTOM, layout.getChildAt(9).getId(), ConstraintSet.TOP);

                set.applyTo(layout);
                break;
            }
        }

        images_list.add(Image_new);
        scrollView.post(() -> scrollView.fullScroll(View.FOCUS_DOWN));
    }

    private Guideline makeGuideline(int orientation, float percent){
        Guideline guideline = new Guideline(view.getContext());
        guideline.setId(guideline.hashCode());

        ConstraintLayout.LayoutParams guidelineParams =
                new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT,
                        ConstraintLayout.LayoutParams.WRAP_CONTENT);
        guidelineParams.orientation = orientation;

        guideline.setLayoutParams(guidelineParams);

        guideline.setGuidelinePercent(percent);

        return guideline;
    }

    public void correctLocationOfImage() {
        for (ImageView image: imgs) {
            image.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
    }

    private Object getConstraintArrayList(int index, ArrayList<ArrayList<Object>> list){
        return list.get(list.size()-1).get(index);
    }
}