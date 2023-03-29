package com.academy;

import com.academy.model.DataSourceManager;
import com.academy.model.entity.*;
import com.academy.model.repository.impl.OrderRepositoryImpl;
import com.academy.model.repository.impl.PassengerRepositoryImpl;
import com.academy.model.repository.impl.PlaneRouteRepositoryImpl;

public class Main {
    public static void main(String[] args) {
        var orderRepository = new OrderRepositoryImpl();
        var passengerRepository = new PassengerRepositoryImpl();
        var planeRouteRepository = new PlaneRouteRepositoryImpl();

        var orders = orderRepository.findAll();

        for (Order order : orders) {
            System.out.println("Order " + order.getId() + ", passenger: " +
                    order.getPassenger().getName() + " " + order.getPassenger().getSurname() + " passport " +
                    order.getPassenger().getPassportNumber() + ", plane: " +
                    order.getPlaneRoute().getPlane().getId() + " " + order.getPlaneRoute().getPlane().getModel() + ", departure: " +
                    order.getPlaneRoute().getRoute().getDepartureCity().getCityName() + " " +
                    order.getDepartureDataTime() + ", arrival: " +
                    order.getPlaneRoute().getRoute().getArrivalCity().getCityName() + " " +
                    order.getArrivalDataTime() + ", status:  " + order.getStatus());
        }

        var foundOrder = orderRepository.findById(2);

        System.out.println("Found Order:");
        System.out.println("Order " + foundOrder.getId() + ", passenger: " +
                foundOrder.getPassenger().getName() + " " + foundOrder.getPassenger().getSurname() + " passport " +
                foundOrder.getPassenger().getPassportNumber() + ", plane: " +
                foundOrder.getPlaneRoute().getPlane().getId() + " " + foundOrder.getPlaneRoute().getPlane().getModel() + ", departure: " +
                foundOrder.getPlaneRoute().getRoute().getDepartureCity().getCityName() + " " +
                foundOrder.getDepartureDataTime() + ", arrival: " +
                foundOrder.getPlaneRoute().getRoute().getArrivalCity().getCityName() + " " +
                foundOrder.getArrivalDataTime() + ", status: " + foundOrder.getStatus());

//create new order

        var newOrder = new Order();
        newOrder.setPassenger(passengerRepository.findById(1));
        newOrder.setPlaneRoute(planeRouteRepository.findById(2));
        newOrder.setArrivalDataTime("11.12.2023 18.22");
        newOrder.setDepartureDataTime("11.12.2023 20.50");

        orderRepository.create(newOrder);

//update order

        var updateOrder = orderRepository.findById(1);
        updateOrder.setPassenger(passengerRepository.findById(2));
        orderRepository.update(updateOrder);

// delete order
      /*  var deleteOrder = new Order();
        deleteOrder.setId(4);
        orderRepository.delete(deleteOrder);*/
    }
}