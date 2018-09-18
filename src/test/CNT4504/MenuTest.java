package CNT4504;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MenuTest {
    @Test
    public void getSelectedMenuOption() {
        Menu menu = new Menu("5", MenuOption.values());
        assertEquals(menu.getSelectedMenuOption(), MenuOption.CURRENT_USERS);
    }

    @Test
    public void promptUserForInput() {
        Menu menu = new Menu("Test1\nTest2\n5", MenuOption.values());
        assertEquals(menu.promptUserForInput(), "5");
    }

    @Test
    public void isInputValid() {
        Menu menu = new Menu(MenuOption.values());
        assertTrue(menu.isInputValid("1"));
        assertTrue(menu.isInputValid(String.valueOf(MenuOption.values().length)));
        assertFalse(menu.isInputValid(String.valueOf(MenuOption.values().length + 1)));
        assertFalse(menu.isInputValid("fgnjkdfjgd"));
        assertFalse(menu.isInputValid("-1"));
    }
}
