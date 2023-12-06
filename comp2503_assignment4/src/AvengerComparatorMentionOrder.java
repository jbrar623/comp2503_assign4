import java.util.Comparator;

public class AvengerComparatorMentionOrder implements Comparator <Avenger> {

	@Override
	/**
	 * compares avanger objects: 
	 * Total order:
	 * descending order of performer frequency
	 * in case of tie, in ascending order of heroName length
	 * in case of tie, in ascending alphabetical order of Alias
	 * @return int - A negative integer, zero, or a positive integer as this avenger 
	 * is less, equal to, or greater than the other.
	 * @param two avenger objects to be compared
	 */
	public int compare(Avenger a1, Avenger a2) {
//		implement an additional new Comparator to order objects based
//		on their “mention index”
		
		return a1.getMentionIndex() - a2.getMentionIndex();
	}
}
