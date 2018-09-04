package CNT4504;

public enum MenuOption {
    DATE("Host Current Date and Time", "date"),
    UPTIME("Host Uptime", "uptime"),
    MEMORY_USE("Host Memory USe", "free"),
    NETSTAT("Host Netstat", "netstat"),
    CURRENT_USERS("Host Current Users", "who"),
    RUNNING_PROCESSES("Host Running Processes", "ps", "fe");

    private String displayName, command, arguments;

    private MenuOption(String displayName, String command) {
        this(displayName, command, "");
    }

    private MenuOption(String displayName, String command, String arguments) {
        this.displayName = displayName;
        this.command = command;
        this.arguments = arguments;
    }

    public String getCommand() {
        return command;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getArguments() {
        return arguments;
    }
}
