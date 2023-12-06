import java.util.Comparator;

public class AvengerComparatorFreqDesc implements Comparator <Avenger>{

	@Override
	/**
	 * Total order:
	 * descending order of total frequency 
	 * in case of tie, in ascending alphabetical order of performer's last name
	 */
	public int compare(Avenger a1, Avenger a2) {
		int diff = a2.getTotalFrequency() - a1.getTotalFrequency();
		if (diff == 0) {
			return a1.getPerformer().compareTo(a2.getPerformer());
		}
		return diff;
	}
}

