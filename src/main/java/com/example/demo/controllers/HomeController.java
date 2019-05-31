package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


@Controller
@CrossOrigin
public class HomeController {
    @Autowired
    AirportController airportController;

    @Autowired
    AirplaneController airplaneController;

    @Autowired
    FlyController flyController;

    /**
     * ###############################   GET   #######################################
     */
    @RequestMapping(value = "airports", method = RequestMethod.GET)
    public String airports(Model model
    ) {
        model.addAttribute("airports", airportController.getAirPorts(null));
        return "airports";
    }


    @RequestMapping(value = "airplanes", method = RequestMethod.GET)
    public String airplanes(Model model) {
        model.addAttribute("airplane", airplaneController.getAirplanes());
        return "airplane/airplane";
    }

    @RequestMapping(value = "flights", method = RequestMethod.GET)
    public String flights(Model model) {
        model.addAttribute("flights", flyController.getFlights());
        return "fly/fly";
    }


    /**
     * ##############################    DELETE    ######################################
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String delete(Model model,
                         @RequestParam(value = "del") String name) {
        airportController.delete(name);
        return airports(model);
    }

    @RequestMapping(value = "delete-airplane", method = RequestMethod.POST)
    public String deleteAirplane(Model model,
                                 @RequestParam(value = "del") String serialNo) {
        airplaneController.delete(serialNo);
        return airplanes(model);
    }

    @RequestMapping(value = "delete-fly", method = RequestMethod.POST)
    public String deleteFly(Model model,
                            @RequestParam(value = "del") Long id) {
        flyController.deleteFly(id);
        return flights(model);
    }


    /**
     * ##############################  UPDATE FORM   ####################################
     */


    @RequestMapping(value = "update-form", method = RequestMethod.GET)
    public String updateSite(Model model, String name) {
        model.addAttribute("airport", airportController.getAirport(name));
        return "updateairport";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(Model model,
                         @RequestParam(value = "name") String name,
                         @RequestParam(value = "city", required = false) String city,
                         @RequestParam(value = "newName", required = false) String newName
    ) {
        airportController.updateAirPort(name, city, newName);
        return airports(model);
    }

    @RequestMapping(value = "update-form-airplane", method = RequestMethod.GET)
    public String updateAirplane(Model model,
                                 String serialNo) {
        model.addAttribute("airports", airportController.getAirPorts(null));
        model.addAttribute("airplane", airplaneController.getAirplane(serialNo));
        return "airplane/updateairplane";
    }


    @RequestMapping(value = "updateairplane", method = RequestMethod.POST)
    public String update(Model model,
                         @RequestParam(value = "serialNo") String serialNo,
                         @RequestParam(value = "planeModel", required = false) String planeModel,
                         @RequestParam(value = "numberOfSeats", required = false) int numberOfSeats,
                         @RequestParam(value = "airport", required = false) String airportName) {

        airplaneController.updateAirPlane(serialNo, planeModel, numberOfSeats, airportName);
        return airplanes(model);
    }

    @RequestMapping(value = "update-form-fly", method = RequestMethod.GET)
    public String updateFlyForm(Model model, Long id) {
        model.addAttribute("airplane", airplaneController.getAirplanes());
        model.addAttribute("fly", flyController.getFly(id));
        return "fly/update";
    }

    @RequestMapping(value = "updatefly", method = RequestMethod.POST)
    public String update(Model model,
                         @RequestParam(value = "id") Long id,
                         @RequestParam(value = "dateDeparture") String dateD,
                         @RequestParam(value = "dateArrival", required = false) String dateA,
                         @RequestParam(value = "airplane", required = false) String airplane, //serialNo -  VIN
                         @RequestParam(value = "tookPlace") boolean tookPlace

    ) {
        flyController.updateFly(id, dateD, dateA, tookPlace, airplane);
        return flights(model);
    }


    /**
     * #############################  ADD FORM  #######################################
     */
    @RequestMapping(value = "add-form", method = RequestMethod.GET)
    public String add(Model model) {
        //pobiera lotniska i kieruje na strone z dodawaniem
        model.addAttribute("airports", airportController.getAirPorts(null));
        return "addairport";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model,
                      @RequestParam(value = "name", required = false) String name,
                      @RequestParam(value = "city", required = false) String city
    ) {
        airportController.addAirport(name, city);
        return airports(model);
    }


    @RequestMapping(value = "add-form-airplane", method = RequestMethod.GET)
    public String addAirplane(Model model, @RequestParam(value = "airport", required = false) String airport) {
        model.addAttribute("airport", airportController.getAirPorts(airport));
        model.addAttribute("airplane", airplaneController.getAirplanes());
        return "airplane/addairplane";
    }

    @RequestMapping(value = "addairplane", method = RequestMethod.POST)
    public String addAirplane(Model model,
                              @RequestParam(value = "airport") String airportName,
                              @RequestParam(value = "plane") String planeModel,
                              @RequestParam(value = "places") int places,
                              @RequestParam(value = "series") String serialNo
    ) {
        airplaneController.addAirPlane(planeModel, places, serialNo, airportName);
        return airplanes(model);
    }

    @RequestMapping(value = "add-form-fly", method = RequestMethod.GET)
    public String addFly(Model model
    ) {
        model.addAttribute("airports", airportController.getAirPorts(null));
        model.addAttribute("airplane", airplaneController.getAirplanes());
        model.addAttribute("flights", flyController.getFlights());
        return "fly/addfly";
    }

    @RequestMapping(value = "addfly", method = RequestMethod.POST)
    public String add(Model model,
                      @RequestParam(value = "dateDeparture") String dateD,
                      @RequestParam(value = "dateArrival") String dateA,
                      @RequestParam(value = "airportDeparture") String airportD,
                      @RequestParam(value = "airportArrival") String airportA,
                      @RequestParam(value = "airplane") String airplane) //serialNo -  VIN
    {
        flyController.addFly(dateD, dateA, airportA, airportD, airplane);
        return flights(model);
    }

}
