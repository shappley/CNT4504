package CNT4504;

public enum MenuOption {
    DATE("Host Current Date and Time", "cmd.exe /c date /t"),
    UPTIME("Host Uptime", "uptime"),
    MEMORY_USE("Host Memory USe", "free"),
    NETSTAT("Host Netstat", "netstat"),
    CURRENT_USERS("Host Current Users", "who"),
    RUNNING_PROCESSES("Host Running Processes", "ps"),
    QUIT("Quit", "");

    private String displayName, command;


    private MenuOption(String displayName, String command) {
        this.displayName = displayName;
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public String getDisplayName() {
        return displayName;
    }
}
