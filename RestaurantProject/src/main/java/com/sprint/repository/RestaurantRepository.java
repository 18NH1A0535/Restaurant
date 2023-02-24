package com.sprint.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprint.models.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant,Long>{

}
