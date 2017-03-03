package br.com.aistech.malleablecarousel.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import br.com.aistech.carousel.fragments.CarouselFragment;
import br.com.aistech.malleablecarousel.R;
import br.com.aistech.malleablecarousel.model.Person;

/**
 * Created by jonathan on 02/03/17.
 */

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
