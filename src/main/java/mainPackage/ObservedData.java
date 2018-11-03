package mainPackage;

import java.util.List;
import java.util.Observable;

import display.DisplayModel;

public class ObservedData extends Observable {
	
    public void setHeadlines(List<String> headLines, DisplayModel displayModel) {
    	
        for (int s=displayModel.getId(); s<headLines.size(); s++){
        	if (Thread.interrupted()) {
                // We've been interrupted: no more crunching.
                return;
            }
            //set change
            setChanged();
            //notify observers for change
            notifyObservers(headLines.get(s));
            displayModel.setId(s);
            try {
                Thread.sleep(10000);
                System.out.println(Thread.currentThread() + "Observe Thread Sleep");
            } catch (InterruptedException e) {
                System.out.println("Error Occurred.");
                return;
            }
        }
    }
}