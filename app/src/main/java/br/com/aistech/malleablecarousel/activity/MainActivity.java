package br.com.aistech.malleablecarousel.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import br.com.aistech.carousel.views.CirclePageIndicator;
import br.com.aistech.carousel.widgets.CarouselViewPager;
import br.com.aistech.malleablecarousel.R;
import br.com.aistech.malleablecarousel.adapters.MainPersonAdapter;
import br.com.aistech.malleablecarousel.model.Person;

public class MainActivity extends AppCompatActivity {

    private CarouselViewPager carouselViewPager;
    private CirclePageIndicator circlePageIndicator;
    private MainPersonAdapter mainPersonAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        carouselViewPager = (CarouselViewPager) findViewById(R.id.carousel_pager);
        circlePageIndicator = (CirclePageIndicator) findViewById(R.id.indicator);

        mainPersonAdapter = new MainPersonAdapter(this, carouselViewPager, getSupportFragmentManager());

        mainPersonAdapter.addObject(new Person("Jonathan", 21));
        mainPersonAdapter.addObject(new Person("Jonathan Nobre", 22));
        mainPersonAdapter.addObject(new Person("Jonathan Nobre Ferreira 1", 23));
        mainPersonAdapter.addObject(new Person("Jonathan Nobre Ferreira 2", 23));
        mainPersonAdapter.addObject(new Person("Jonathan Nobre Ferreira 3", 23));
        mainPersonAdapter.addObject(new Person("Jonathan Nobre Ferreira 4", 23));
        mainPersonAdapter.addObject(new Person("Jonathan Nobre Ferreira 5", 23));
        mainPersonAdapter.addObject(new Person("Jonathan Nobre Ferreira 6", 23));
        mainPersonAdapter.addObject(new Person("Jonathan Nobre Ferreira 7", 23));
        mainPersonAdapter.addObject(new Person("Jonathan Nobre Ferreira 8", 23));
        mainPersonAdapter.addObject(new Person("Jonathan Nobre Ferreira 9", 23));
        mainPersonAdapter.addObject(new Person("Jonathan Nobre Ferreira 10", 23));

        carouselViewPager.setAdapter(mainPersonAdapter);
        carouselViewPager.setCurrentItem(0, true);
        carouselViewPager.setOffscreenPageLimit(3);
        carouselViewPager.setPageMargin(mainPersonAdapter.getPageMargin());
        circlePageIndicator.setViewPager(carouselViewPager, 0);
    }
}
