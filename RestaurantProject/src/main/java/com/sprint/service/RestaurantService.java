package com.sprint.service;
import java.util.Date;
import com.sprint.models.Restaurant;
public interface RestaurantService {
	public void getTablesAvailableOnDateTime(Date date);
	public void getTablesBySeating();
	public void getAllAvailableTables();
	public Restaurant createRestaurant(Restaurant restaurant);
}


