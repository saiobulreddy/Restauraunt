import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalTime;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RestaurantTest {

    Restaurant restaurant;
    Integer initialMenuSize;

    public RestaurantTest() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = new Restaurant("Amelie's cafe", "Chennai", openingTime, closingTime);
        restaurant.addToMenu("Sweet corn soup", 119);
        restaurant.addToMenu("Vegetable lasagne", 269);
        initialMenuSize = restaurant.getMenu().size();
    }

    /**
     * IF YOU FACE ANY TROUBLE OR ANY TEST CASE GET FAILED
     * RUN THE TESTS BELOW INDIVIDUALLY ONE BY ONE INSTEAD OF RUNNING RestaurantTest class DIRECTLY
     */

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time() {
        Restaurant mock_restaurant = Mockito.spy(restaurant);
        LocalTime mock_currentTime = LocalTime.parse("11:00:00");
        Mockito.when(mock_restaurant.getCurrentTime()).thenReturn(mock_currentTime);
        assertTrue(mock_restaurant.isRestaurantOpen());
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time() {
        Restaurant mock_restaurant = Mockito.spy(restaurant);
        LocalTime mock_currentTime = LocalTime.parse("10:00:00");
        Mockito.when(mock_restaurant.getCurrentTime()).thenReturn(mock_currentTime);
        assertFalse(mock_restaurant.isRestaurantOpen());
    }
    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1() {
        restaurant.addToMenu("Sizzling brownie", 319);
        assertEquals(initialMenuSize + 1, restaurant.getMenu().size());
    }

    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize - 1, restaurant.getMenu().size());
    }

    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        assertThrows(itemNotFoundException.class, () -> restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //<<<<<<<<<<<<<<<<<<<<<<<ORDER VALE>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    @Test
    public void order_value_should_be_addition_of_all_item_values_in_the_order() throws itemNotFoundException {
        int orderValue = restaurant.getOrderValue("Sweet corn soup", "Vegetable lasagne");
        assertEquals(119 + 269, orderValue);
    }

    @Test
    public void ordering_item_that_does_not_exist_should_throw_exception() {
        assertThrows(itemNotFoundException.class, () -> restaurant.getOrderValue("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<ORDER VALE>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

}