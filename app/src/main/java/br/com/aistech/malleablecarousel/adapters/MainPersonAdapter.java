package br.com.aistech.malleablecarousel.adapters;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;

import br.com.aistech.carousel.adapters.CarouselAdapter;
import br.com.aistech.carousel.fragments.CarouselFragment;
import br.com.aistech.malleablecarousel.fragments.PersonCarouselFragment;
import br.com.aistech.malleablecarousel.model.Person;

/**
 * Created by jonathan on 02/03/17.
 */

public class MainPersonAdapter extends CarouselAdapter<Person> {

    public MainPersonAdapter(Context context, ViewPager viewPager, FragmentManager fm) {
        super(context, viewPager, fm);
    }

    @Override
    public CarouselFragment getCarouselFragment(Integer position, Float scale) {
        Person person = getObjectItem(position);

        Bundle bundle = new Bundle();
        bundle.putSerializable(PersonCarouselFragment.PERSON_KEY, person);

        return PersonCarouselFragment.newInstance(PersonCarouselFragment.class, getContext(), position, scale, bundle);
    }

    @Override
    public Integer getPageMarginForPortrait() {
        return calculateMarginViewPagerByScreenForPortrait();
    }

    @Override
    public Integer getPageMarginForLandscape() {
        return calculateMarginViewPagerByScreenForLandscape();
    }

    private int calculateMarginViewPagerByScreenForLandscape() {
        if (getContext() instanceof Activity) {
            DisplayMetrics dm = new DisplayMetrics();
            ((Activity) getContext()).getWindow().getWindowManager().getDefaultDisplay().getMetrics(dm);
            int width = dm.widthPixels;
            return ((width / 3) * -1) * 2;
        }
        return 0;
    }

    private int calculateMarginViewPagerByScreenForPortrait() {
        if (getContext() instanceof Activity) {
            DisplayMetrics dm = new DisplayMetrics();
            ((Activity) getContext()).getWindow().getWindowManager().getDefaultDisplay().getMetrics(dm);
            int width = dm.widthPixels;
            return (int) ((width / 3.2) * -1);
        }
        return 0;
    }
}
