import java.util.Comparator;

public class AvengerPerformerComparatorFreqDesc implements Comparator <Avenger>{

	@Override
	/**
	 * Total order:
	 * descending order of performer frequency
	 * in case of tie, in ascending order of heroName length
	 * in case of tie, in ascending alphabetical order of Alias
	 */
	public int compare(Avenger a1, Avenger a2) {
		int diff = a2.getPerformerFreq() - a1.getPerformerFreq();
		if (diff == 0) {
			diff = a1.getName().length() - a2.getName().length();
			if (diff == 0)
				return a1.getAlias().compareTo(a2.getAlias());
		}
		return diff;
	}
}

