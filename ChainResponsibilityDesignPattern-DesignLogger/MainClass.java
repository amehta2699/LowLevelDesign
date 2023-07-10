
// THIS DESIGN PATTERN IS USED TO DESIGN ATM, VENDING MACHINE OR LOGGER(THAT WE IMPLEMENTED)
public class MainClass {

    public static void main(String args[]){

        LogProcessor logObject = new InfoLogProcessor(new DebugLogProcessor(new ErrorLogProcessor(null)));

        logObject.log(LogProcessor.ERROR, "Exception happens");
        logObject.log(LogProcessor.DEBUG, "need to debug this");
        logObject.log(LogProcessor.INFO, "just for info");



    }
}
