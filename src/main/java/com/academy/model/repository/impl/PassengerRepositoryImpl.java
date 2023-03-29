package com.academy.model.repository.impl;

import com.academy.model.DataSourceManager;
import com.academy.model.entity.*;
import com.academy.model.repository.PassengerRepository;

import java.sql.SQLException;
import java.util.List;

public class PassengerRepositoryImpl implements PassengerRepository {
    @Override
    public void create(Passenger entity) {

    }

    @Override
    public void update(Passenger entity) {

    }

    @Override
    public void delete(Passenger entity) {

    }

    @Override
    public List<Passenger> findAll() {
        return null;
    }

    @Override
    public Passenger findById(Integer id) {

        var passenger = new Passenger();

        var connection = DataSourceManager.getInstance().getConnection();
        var query = "SELECT * FROM passenger where id = ? ";

        try (var statement = connection.prepareStatement(query)){
            statement.setInt(1, id);

            var result = statement.executeQuery();

            if (result.next()) {
                passenger = Passenger.builder()
                        .id (result.getInt("passenger.id"))
                        .name(result.getString("name"))
                        .surname(result.getString("surname"))
                        .dateOfBirth(result.getString("dob"))
                        .passportNumber(result.getString("passport_number"))
                        .build();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return passenger;
    }
}
