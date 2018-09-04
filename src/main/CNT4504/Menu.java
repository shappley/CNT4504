package CNT4504;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Menu {
    private MenuOption[] options;
    private Scanner scanner;
    private Pattern validInputPattern;

    public Menu(MenuOption... options) {
        this(System.in, options);
    }

    public Menu(String source, MenuOption... options) {
        this(new ByteArrayInputStream(source.getBytes()), options);
    }

    public Menu(InputStream in, MenuOption... options) {
        this.options = options;
        this.scanner = new Scanner(in);
        this.validInputPattern = Pattern.compile("[1-" + options.length + "]");
    }

    /**
     * Returns the user's selected Menu Option
     */
    public MenuOption getSelectedMenuOption() {
        this.showMenu();
        final String input = promptUserForInput();
        final int index = Integer.parseInt(input);
        //users enter the index in 1-based format, so we need to subtract 1 to make it 0-based
        return this.options[index - 1];
    }

    /**
     * Prints the menu for the user to select from
     * Formatted as (index). (option)
     */
    private void showMenu() {
        for (int i = 0; i < this.options.length; i++) {
            System.out.printf("%d. %s\n", i + 1, this.options[i].getDisplayName());
        }
    }

    /**
     * Repeatedly prompts user for input until what they enter is valid
     *
     * @return the value the user entered
     */
    public String promptUserForInput() {
        String s = null;
        do {
            //if 's' isn't null, then we've been here before
            //meaning the user entered something wrong
            if (s != null) {
                System.out.printf("Invalid input \"%s\"\n", s);
            }
            System.out.printf("Enter a number between 1 and %d: ", options.length);
            s = scanner.nextLine();
        } while (!isInputValid(s));
        return s;
    }

    /**
     * Checks if the user's input is valid.
     *
     * @param input the user's input to check
     * @return True if the input is valid. False otherwise.
     */
    public boolean isInputValid(String input) {
        return validInputPattern.matcher(input).matches();
    }
}
