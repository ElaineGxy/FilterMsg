public class testPath {
    public String locPath="library/location.csv";
    public String evtPath="library/event.csv";
    public testPath(){

    }
    public String test() {
    	return this.getClass().getResource(locPath).getPath();
    }

}
