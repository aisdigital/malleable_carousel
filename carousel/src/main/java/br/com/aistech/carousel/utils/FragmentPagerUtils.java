package br.com.aistech.carousel.utils;

import android.support.v4.view.ViewPager;

/**
 * Created by jonathan on 02/03/17.
 */

public class FragmentPagerUtils {

    public static String getFragmentTag(ViewPager viewPager, Integer position) {
        return "android:switcher:" + viewPager.getId() + ":" + position;
    }
}
