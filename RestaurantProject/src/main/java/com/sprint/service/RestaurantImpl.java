package com.sprint.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.models.Admin;
import com.sprint.models.Restaurant;
import com.sprint.repository.RestaurantRepository;

@Service
public class RestaurantImpl implements RestaurantService{

	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Override
	public Restaurant createRestaurant(Restaurant restaurant) {
		Admin admin=new Admin();
		admin.setAdminId(1);
		restaurant.setAdmin(admin);
		return restaurantRepository.save(restaurant);
	}
	@Override
	public void getTablesAvailableOnDateTime(Date date) {
		
		
	}

	@Override
	public void getTablesBySeating() {
		
		
	}

	@Override
	public void getAllAvailableTables() {
		
		
	}

}
