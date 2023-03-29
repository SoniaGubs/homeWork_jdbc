package com.academy.model.repository.impl;

import com.academy.model.DataSourceManager;
import com.academy.model.entity.*;
import com.academy.model.repository.PlaneRepository;
import com.academy.model.repository.PlaneRouteRepository;

import java.sql.SQLException;
import java.util.List;

public class PlaneRouteRepositoryImpl implements PlaneRouteRepository {


    @Override
    public void create(PlaneRoute entity) {

    }

    @Override
    public void update(PlaneRoute entity) {

    }

    @Override
    public void delete(PlaneRoute entity) {

    }

    @Override
    public List<PlaneRoute> findAll() {
        return null;
    }

    @Override
    public PlaneRoute findById(Integer id) {

        var planeRoute = new PlaneRoute();

        var connection = DataSourceManager.getInstance().getConnection();
        var query = "select  plane_route.id, plane.id as plane, model as model_plane,  departure_city.city as departure_city, arrival_city.city as arrival_city  from plane_route\n" +
                "join plane on plane_route.plane_id = plane.id\n" +
                "join route on plane_route.route_id = route.id\n" +
                "join city as departure_city on route.departure_city_id = departure_city.id\n" +
                "join city as arrival_city on route.arrival_city_id = arrival_city.id\n" +
                "where plane_route.id = ? ";

        try (var statement = connection.prepareStatement(query)){
            statement.setInt(1, id);

            var result = statement.executeQuery();

            if (result.next()) {
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

                planeRoute = PlaneRoute.builder()
                        .id(result.getInt("plane_route.id"))
                        .plane(plane)
                        .route(route)
                        .build();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return planeRoute;
    }
}
