package controller;

import model.Hotel;
import view.HotelView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HotelController {
    private Hotel hotelModel;
    private HotelView hotelView;

    public HotelController(Hotel model, HotelView view) {
        this.hotelModel = model;
        this.hotelView = view;
    }
}
