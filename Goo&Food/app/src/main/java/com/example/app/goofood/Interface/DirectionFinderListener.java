package com.example.app.goofood.Interface;

import com.example.app.goofood.Model.Route;

import java.util.List;

public interface DirectionFinderListener {
    void onDirectionFinderStart();
    void onDirectionFinderSuccess(List<Route> route);
}