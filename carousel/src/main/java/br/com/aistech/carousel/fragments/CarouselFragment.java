package br.com.aistech.carousel.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;

import br.com.aistech.carousel.R;
import br.com.aistech.carousel.adapters.CarouselAdapter;
import br.com.aistech.carousel.animations.CarouselAnimation;

/**
 * Created by jonathan on 02/03/17.
 */

public abstract class CarouselFragment extends Fragment {

    protected final static Float DEFAULT_SCALE = CarouselAdapter.SMALL_SCALE;

    public static final String POSITION_KEY = CarouselFragment.class.getSimpleName().concat("position");
    public static final String SCALE_KEY = CarouselFragment.class.getSimpleName().concat("scale");

    private Integer position;
    private Float scale;

    public static CarouselFragment newInstance(Class<? extends CarouselFragment> clazz, Context context, Integer position, Float scale, Bundle bundle) {

        if (bundle == null) {
            bundle = new Bundle();
        }

        if (position == null) {
            throw new RuntimeException(CarouselFragment.class.getSimpleName() + " need position to work properly");
        }

        if (scale == null) {
            scale = DEFAULT_SCALE;
        }

        if (!bundle.containsKey(POSITION_KEY)) {
            bundle.putInt(POSITION_KEY, position);
        }

        if (!bundle.containsKey(SCALE_KEY)) {
            bundle.putFloat(SCALE_KEY, scale);
        }

        try {
            CarouselFragment carouselFragment = clazz.newInstance();
            carouselFragment.setArguments(bundle);
            return carouselFragment;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

    public CarouselFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            position = getArguments().getInt(POSITION_KEY);
            scale = getArguments().getFloat(SCALE_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (container == null) {
            return super.onCreateView(inflater, container, savedInstanceState);
        }

        CarouselAnimation rootView = (CarouselAnimation) inflater.inflate(R.layout.carousel_animation_fragment, container, false);
        ViewStub viewStub = (ViewStub) rootView.findViewById(R.id.view_stub);

        viewStub.setLayoutResource(getLayoutResource());
        viewStub.inflate();

        rootView.setScaleBoth(getScale());

        return rootView;
    }

    /* Abstract Methods */

    @LayoutRes
    protected abstract int getLayoutResource();

    /* Getters */

    public Integer getPosition() {
        return position;
    }

    public Float getScale() {
        return scale;
    }
}
