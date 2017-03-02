package br.com.aistech.malleablecarousel.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);

        titleTextView = (TextView) rootView.findViewById(R.id.carousel_title);

        titleTextView.setText(person.getName());

        return rootView;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.carousel_item_example;
    }
}
