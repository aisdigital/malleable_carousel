package br.com.aistech.carousel.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import java.util.LinkedList;
import java.util.List;

import br.com.aistech.carousel.animations.CarouselAnimation;
import br.com.aistech.carousel.fragments.CarouselFragment;
import br.com.aistech.carousel.utils.DeviceUtils;
import br.com.aistech.carousel.utils.FragmentPagerUtils;

/**
 * Created by jonathan on 02/03/17.
 */

public abstract class CarouselAdapter<T> extends FragmentPagerAdapter implements ViewPager.OnPageChangeListener {

    protected final static Integer FIRST_PAGE = 0;

    public final static Float BIG_SCALE = 0.85f;
    public final static Float SMALL_SCALE = 0.35f;

    private List<T> objects;

    private Context context;
    private ViewPager viewPager;
    private FragmentManager fragmentManager;

    public CarouselAdapter(Context context, ViewPager viewPager, FragmentManager fm) {
        super(fm);
        this.context = context;
        this.fragmentManager = fm;
        this.viewPager = viewPager;
        this.viewPager.addOnPageChangeListener(this);

        objects = new LinkedList<>();
    }

    @Override
    public Fragment getItem(int position) {

        // Make the first pager bigger than others
        Float scale;

        if (position == FIRST_PAGE) {
            scale = getBigScale();
        } else {
            scale = getSmallScale();
        }

        position = position % getCount();

        return getCarouselFragment(position, scale);
    }

    @Override
    public int getCount() {
        return this.objects.size();
    }

    /* ViewPager.OnPageChangeListener */

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        try {
            CarouselAnimation current = getFragmentRootView(position);

            if (positionOffset >= 0f && positionOffset <= 1f) {
                current.setScaleBoth(BIG_SCALE - getDiffScale() * positionOffset);

                if (position < getCount() - 1) {
                    CarouselAnimation next = getFragmentRootView(position + 1);
                    next.setScaleBoth(SMALL_SCALE + getDiffScale() * positionOffset);
                }
                if (position > 0) {
                    CarouselAnimation previous = getFragmentRootView(position - 1);
                    previous.setScaleBoth(SMALL_SCALE - getDiffScale() * positionOffset);
                }
            }
        } catch (Exception e) {
            Log.e(getClass().getSimpleName(), e.getMessage());
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /* Auxiliary Methods */

    public T getObjectItem(int position) {
        return getObjects().get(position);
    }

    public void addObject(@NonNull T object) {
        if (!getObjects().contains(object)) {
            getObjects().add(object);
        }
    }

    public void addAllObject(@NonNull List<T> objects) {
        for (T object : objects) {
            addObject(object);
        }
    }

    private CarouselAnimation getFragmentRootView(Integer position) throws Exception {
        Fragment fragment = getFragmentManager().findFragmentByTag(FragmentPagerUtils.getFragmentTag(this.viewPager, position));
        View fragmentRootView = fragment.getView();
        if (fragmentRootView instanceof CarouselAnimation) {
            return (CarouselAnimation) fragmentRootView;
        } else {
            throw new Exception(fragment.getClass().getSimpleName() + " must include CarouselAnimation as rootView");
        }
    }

    /* Abstract methods */

    public abstract CarouselFragment getCarouselFragment(Integer position, Float scale);

    public abstract Integer getPageMarginForPortrait();

    public abstract Integer getPageMarginForLandscape();

    public Integer getPageMargin() {
        switch (DeviceUtils.getDeviceOrientation(getContext())) {
            case LANDSCAPE:
                return getPageMarginForLandscape();
            case PORTRAIT:
                return getPageMarginForPortrait();
            default:
                return 0;
        }
    }

    /* Getters */

    public FragmentManager getFragmentManager() {
        return fragmentManager;
    }

    public List<T> getObjects() {
        return objects;
    }

    protected Float getBigScale() {
        return BIG_SCALE;
    }

    protected Float getSmallScale() {
        return SMALL_SCALE;
    }

    protected Float getDiffScale() {
        return BIG_SCALE - SMALL_SCALE;
    }

    private Integer getLastPagePosition() {
        return getCount();
    }

    public Context getContext() {
        return context;
    }
}
