[![](https://jitpack.io/v/aistech/malleable_carousel.svg)](https://jitpack.io/#aistech/malleable_carousel)

Malleable Carousel
=======================

Based on ViewPager, this library allows you to achieve the Carousel List effect

Installation
============

In your project build.gradle

```groovy
allprojects {
    repositories {
        jcenter()
        maven { url 'https://jitpack.io' }
    }
}
```

In your app's build.gradle

```groovy
dependencies {
  compile 'com.github.aistech:malleable_carousel:1.0.0'
}
```

How to Use
==========

1) Declare the ```CarouselViewPager``` on your xml. Optionaly, you can also declare a CirclePageIndicator.

```xml
<br.com.aistech.carousel.widgets.CarouselViewPager
    android:id="@+id/carousel_pager"
    android:layout_width="match_parent"
    android:layout_height="230dp"
    android:layout_marginTop="20dp" />

<br.com.aistech.brasilcap.views.CirclePageIndicator
    android:id="@+id/main_page_indicator"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_below="@+id/carousel_pager"
    android:layout_marginTop="10dp"
    android:padding="10dp" />
```

2) Create your own implementation of adapter. See that you need to provide the necessary values about the pageMargin in order to carousel work properly. 
Also, Usually you would provide the Fragment using the ```FragmentPagerAdapter#getItem(position)```  method, but since we use a custom Fragment implementation,
you have to use the ```getCarouselFragment(Integer position, Float scale)```, with the position you can retrive the necessary object in the array of objects,
the scale parameter you shall pass into the CarouselFragment's static factory method.

```java
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
            return (int) ((width / 2.3) * -1);
        }
        return 0;
    }
}
```

3) Declare your own fragment implementation. Your fragment must extend ```CarouselFragment``` and forget about the traditional way of creating Fragment, we took care of it, you just need to provide your custom layout in the method getLayoutResource(). In another words, if you override onCreateView, be sure to call and return the View from his parent.

```java
public class PersonCarouselFragment extends CarouselFragment {

    public static final String PERSON_KEY = PersonCarouselFragment.class.getSimpleName().concat("person");

    private Person person;
    private TextView titleTextView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            person = (Person) getArguments().getSerializable(PERSON_KEY);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        titleTextView = (TextView) view.findViewById(R.id.carousel_title);

        titleTextView.setText(person.getName());
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.carousel_item_example;
    }
}
```

This is your layoutResource example

```xml
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_gravity="center"
    android:gravity="center"
    android:orientation="vertical">

    <ImageView
        android:id="@+id/photo"
        android:layout_width="150dp"
        android:layout_height="150dp"
        android:background="@drawable/shape_avatar_image" />

    <TextView
        android:id="@+id/carousel_title"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="2dp"
        android:text="Name"
        android:textColor="@color/colorPrimary" />

</LinearLayout>
```

4) Finally, you just need to connect the dots and provide DataSet.

```java
carouselViewPager = (CarouselViewPager) findViewById(R.id.carousel_pager);
circlePageIndicator = (CirclePageIndicator) findViewById(R.id.indicator);

mainPersonAdapter = new MainPersonAdapter(this, carouselViewPager, getSupportFragmentManager());

mainPersonAdapter.addObject(new Person("João 01", 21));
mainPersonAdapter.addObject(new Person("João 02", 22));
mainPersonAdapter.addObject(new Person("João 03", 23));
mainPersonAdapter.addObject(new Person("João 04", 23));
mainPersonAdapter.addObject(new Person("João 05", 23));
mainPersonAdapter.addObject(new Person("João 06", 23));
mainPersonAdapter.addObject(new Person("João 07", 23));
mainPersonAdapter.addObject(new Person("João 08", 23));
mainPersonAdapter.addObject(new Person("João 09", 23));
mainPersonAdapter.addObject(new Person("João 10", 23));
mainPersonAdapter.addObject(new Person("João 11", 23));
mainPersonAdapter.addObject(new Person("João 12", 23));

carouselViewPager.setAdapter(mainPersonAdapter);
carouselViewPager.setCurrentItem(0, true);
carouselViewPager.setOffscreenPageLimit(3);
carouselViewPager.setPageMargin(mainPersonAdapter.getPageMargin());
carouselViewPager.setClipToPadding(false);
circlePageIndicator.setViewPager(carouselViewPager, 0);
```

For now, That's all Folks!
