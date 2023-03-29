package com.academy.model.repository.impl;

import com.academy.model.DataSourceManager;
import com.academy.model.entity.*;
import com.academy.model.repository.OrderRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderRepositoryImpl implements OrderRepository {
    @Override
    public void create(Order order) {
        var connection = DataSourceManager.getInstance().getConnection();

        var query = "insert into orders ( plane_route_id,passenger_id, departure_data_time,arrival_data_time,status) values (?,?,?,?,?)";
        try (var statement = connection.prepareStatement(query)) {
            statement.setInt(1, order.getPlaneRoute().getId());
            statement.setInt(2, order.getPassenger().getId());
            statement.setString(3,order.getDepartureDataTime());
            statement.setString(4, order.getArrivalDataTime());
            statement.setString(5, "new");

            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Order order) {
        var connection = DataSourceManager.getInstance().getConnection();

        var query = "update orders set plane_route_id = ?, passenger_id = ?,departure_data_time = ?,arrival_data_time = ?, status = ? where orders.id = ?";
        try (var statement = connection.prepareStatement(query)) {
            statement.setInt(1, order.getPlaneRoute().getId());
            statement.setInt(2, order.getPassenger().getId());
            statement.setString(3,order.getDepartureDataTime());
            statement.setString(4, order.getArrivalDataTime());
            statement.setString(5, order.getStatus());
            statement.setInt(6, order.getId());

            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Order order) {
        var connection = DataSourceManager.getInstance().getConnection();

        var query = "delete from orders where id = ?";
        try (var statement = connection.prepareStatement(query)) {
            statement.setInt(1, order.getId());
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Order> findAll() {
        var orders = new ArrayList<Order>();

        var connection = DataSourceManager.getInstance().getConnection();

        var query = "select orders.id as id, name, surname, dob, passport_number, plane.id as plane, model as model_plane, " +
                " departure_city.city as departure_city, departure_data_time, arrival_city.city as arrival_city, arrival_data_time, status from orders\n" +
                "join passenger on orders.passenger_id = passenger.id\n" +
                "join plane_route on orders.plane_route_id = plane_route.id\n" +
                "join plane on plane_route.plane_id = plane.id\n" +
                "join route on plane_route.route_id = route.id\n" +
                "join city as departure_city on route.departure_city_id = departure_city.id\n" +
                "join city as arrival_city on route.arrival_city_id = arrival_city.id\n";

        try (var statement = connection.prepareStatement(query)) {
            var result = statement.executeQuery();

            while (result.next()) {
                var passenger = Passenger.builder()
                        //  .id (result.getInt("passenger.id"))
                        .name(result.getString("name"))
                        .surname(result.getString("surname"))
                        .dateOfBirth(result.getString("dob"))
                        .passportNumber(result.getString("passport_number"))
                        .build();

                var plane = Plane.builder()
                        .id(result.getInt("plane"))
                        .model(result.getString("model_plane"))
                        .build();

                var departureCity = City.builder()
                        .cityName(result.getString("departure_city"))
                        .build();

                var arrivalCity = City.builder()
                        .cityName(result.getString("arrival_city"))
                        .build();

                var route = Route.builder()
                        .departureCity(departureCity)
                        .arrivalCity(arrivalCity)
                        .build();

                var planeRoute = PlaneRoute.builder()
                        .plane(plane)
                        .route(route)
                        .build();

                var order = Order.builder()
                        .id(result.getInt("id"))
                        .passenger(passenger)
                        .planeRoute(planeRoute)
                        .departureDataTime(result.getString("departure_data_time"))
                        .arrivalDataTime(result.getString("arrival_data_time"))
                        .status(result.getString("status"))
                        .build();

                orders.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public Order findById(Integer id) {

        var order = new Order();
        var connection = DataSourceManager.getInstance().getConnection();

        var query = "select orders.id as id, name, surname, dob, passport_number, plane_route.id as plane_route_id, plane.id as plane, model as model_plane, " +
                " departure_city.city as departure_city, departure_data_time, arrival_city.city as arrival_city, arrival_data_time, status from orders\n" +
                "join passenger on orders.passenger_id = passenger.id\n" +
                "join plane_route on orders.plane_route_id = plane_route.id\n" +
                "join plane on plane_route.plane_id = plane.id\n" +
                "join route on plane_route.route_id = route.id\n" +
                "join city as departure_city on route.departure_city_id = departure_city.id\n" +
                "join city as arrival_city on route.arrival_city_id = arrival_city.id\n" +
                "where orders.id = ?";

        try (var statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);

            var result = statement.executeQuery();

            if (result.next()) {
                var passenger = Passenger.builder()
                        .name(result.getString("name"))
                        .surname(result.getString("surname"))
                        .dateOfBirth(result.getString("dob"))
                        .passportNumber(result.getString("passport_number"))
                        .build();

                var plane = Plane.builder()
                        .id(result.getInt("plane"))
                        .model(result.getString("model_plane"))
                        .build();

                var departureCity = City.builder()
                        .cityName(result.getString("departure_city"))
                        .build();

                var arrivalCity = City.builder()
                        .cityName(result.getString("arrival_city"))
                        .build();

                var route = Route.builder()
                        .departureCity(departureCity)
                        .arrivalCity(arrivalCity)
                        .build();

                var planeRoute = PlaneRoute.builder()
                        .id(result.getInt("plane_route_id"))
                        .plane(plane)
                        .route(route)
                        .build();


                order.setId(result.getInt("id"));
                order.setPassenger(passenger);
                order.setPlaneRoute(planeRoute);
                order.setDepartureDataTime(result.getString("departure_data_time"));
                order.setArrivalDataTime(result.getString("arrival_data_time"));
                order.setStatus(result.getString("status"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }
}