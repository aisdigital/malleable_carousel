package br.com.aistech.carousel.listeners;

import android.support.v4.view.ViewPager;

/**
 * Created by Paulo on 21/10/2015.
 */
public interface PageIndicator extends ViewPager.OnPageChangeListener {

    void setViewPager(ViewPager view);

    void setViewPager(ViewPager view, Integer initialPosition);

    void setOnPageChangeListener(ViewPager.OnPageChangeListener listener);

    void notifyDataSetChanged();

    void setCurrentItem(Integer item);
}
