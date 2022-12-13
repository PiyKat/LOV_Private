package src.java.utils;

/**
 * Wrap the logging level control.
 */
public class Logger {
    private static int boundary;

    /**
     * Show the message with DEBUG severity.
     *
     * @param msg to print.
     */
    public static void debug(Object msg) {
        if (boundary >= 3) {
            SysOut.println("[DEBUG]: " + msg);
        }
    }

    /**
     * Show the message with INFO severity.
     *
     * @param msg to print.
     */
    public static void info(Object msg) {
        if (boundary >= 2) {
            SysOut.println("[INFO]: " + msg);
        }
    }

    /**
     * Show the message with WARNING severity.
     *
     * @param msg to print.
     */
    public static void warning(Object msg) {
        if (boundary >= 1) {
            SysOut.println("[WARNING]: " + msg);
        }
    }

    /**
     * Show the message with ERROR severity.
     *
     * @param msg to print.
     */
    public static void error(Object msg) {
        if (boundary >= 0) {
            SysOut.println("[ERROR]: " + msg);
        }
    }

    public static void setLevel(int boundary) {
        Logger.boundary = boundary;
    }
}
