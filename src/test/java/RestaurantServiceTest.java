import org.junit.jupiter.api.*;
import java.time.LocalTime;
import static org.junit.jupiter.api.Assertions.*;


class RestaurantServiceTest {
    RestaurantService restaurantService;
    Restaurant restaurant;
    Integer initialNumberOfRestaurants;

    public RestaurantServiceTest() {
        restaurantService = new RestaurantService();
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurantService.getRestaurants().clear();
        restaurant = restaurantService.addRestaurant("Amelie's cafe", "Chennai", openingTime, closingTime);
        initialNumberOfRestaurants = restaurantService.getRestaurants().size();
    }

    //>>>>>>>>>>>>>>>>>>>>>>SEARCHING<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void searching_for_existing_restaurant_should_return_expected_restaurant_object() throws restaurantNotFoundException {
        Restaurant searchedRestaurantByName = restaurantService.findRestaurantByName("Amelie's cafe");
        assertNotNull(searchedRestaurantByName);
        assertEquals(restaurant, searchedRestaurantByName);
    }

    @Test
    public void searching_for_non_existing_restaurant_should_throw_exception() {
        assertThrows(restaurantNotFoundException.class, () -> restaurantService.findRestaurantByName("Amelie cafe"));
    }
    //<<<<<<<<<<<<<<<<<<<<SEARCHING>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>ADMIN: ADDING & REMOVING RESTAURANTS<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void remove_restaurant_should_reduce_list_of_restaurants_size_by_1() throws restaurantNotFoundException {
        restaurantService.removeRestaurant("Amelie's cafe");
        assertEquals(initialNumberOfRestaurants - 1, restaurantService.getRestaurants().size());
    }

    @Test
    public void removing_restaurant_that_does_not_exist_should_throw_exception() {
        assertThrows(restaurantNotFoundException.class, () -> restaurantService.removeRestaurant("Amelie cafe"));
    }

    @Test
    public void add_restaurant_should_increase_list_of_restaurants_size_by_1() {
        restaurantService.addRestaurant("Pumpkin Tales", "Chennai", LocalTime.parse("12:00:00"), LocalTime.parse("23:00:00"));
        assertEquals(initialNumberOfRestaurants + 1, restaurantService.getRestaurants().size());
    }
    //<<<<<<<<<<<<<<<<<<<<ADMIN: ADDING & REMOVING RESTAURANTS>>>>>>>>>>>>>>>>>>>>>>>>>>
}