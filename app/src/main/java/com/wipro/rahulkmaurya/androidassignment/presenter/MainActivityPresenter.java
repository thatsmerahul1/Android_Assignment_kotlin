package com.wipro.rahulkmaurya.androidassignment.presenter;


import com.wipro.rahulkmaurya.androidassignment.model.Facts;

/**
 * Created by rahul.k.maurya on 2018-09-24.
 *
 * 0. In MVP the presenter assumes the functionality of the "middle-man". All presentation logic is pushed to the presenter.
 * 1. Listens to user action and model updates
 * 2. Updates model and view
 */

public class MainActivityPresenter {

    private Facts facts;
    private View view;

    public MainActivityPresenter(View view) {
        this.facts = new Facts("",null);
        this.view = view;
    }

    public void updateFactsData(Facts factsResponse){
        this.facts = factsResponse;
        view.updateFactsView(this.facts);
    }

    public interface View{
        void updateFactsView(Facts facts);
        void showProgressBar();
        void hideProgressBar();

    }
}
