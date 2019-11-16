import java.util.ArrayList; 
import java.math.BigInteger;

/*
 *  Desc: This class generates primes, twin primes, and hexagon crosses using BigInteger data types.
 */
public class PrimeOperations {
	
	// Pair class implementation.
	private class Pair<T> {
		T leftNum;
		T rightNum;
		
		Pair(T lNum, T rNum) {
			leftNum = lNum;
			rightNum = rNum;
		}
		public String toString() {
			return leftNum + " , " + rightNum;
		}
	}
	public boolean isPrime(BigInteger number) {
		BigInteger i = new BigInteger("2");
		
		while(i.compareTo(number) < 0) {
	        if (number.mod(i).equals(BigInteger.ZERO)) {
	            return false;
	        }
	        i = i.add(BigInteger.valueOf(1));
	    }
	    return true;
	}
	// Member variables for containing out lists of integers, twin primes, hexagon crosses, and the pairs of twin primes that make up the hex crosses.
	ArrayList<BigInteger> primes = new ArrayList<BigInteger>();
	ArrayList<Pair<BigInteger>> twinPrimes = new ArrayList<Pair<BigInteger>>();
	ArrayList<Pair<Pair<BigInteger>>> hexagon = new ArrayList<Pair<Pair<BigInteger>>>();

	// Add a prime to the prime list if and only iff it is not already in the list. (ignore duplicates)
	public void addPrime(BigInteger x) {
		primes.add(x);
	}
	
	// Output the prime list. Each prime should be on a separate line and the total number of primes should be on the following line.
	public void printPrimes()
	{
		for(int i=0; i < primes.size(); ++i) {
			System.out.println(primes.get(i));
		}
		System.out.println("Total primes: " + primes.size());
	}
	
	// Output the twin prime list. Each twin prime should be on a separate line with a comma separating them, and the total number of twin primes should be on the following line.
	public void printTwins() {
		for(int i=0; i < twinPrimes.size(); ++i) {
			System.out.println(twinPrimes.get(i));
		}
		System.out.println("Total twins: " + twinPrimes.size());
	}
		
	// Output the hexagon cross list. Each should be on a separate line listing the two twin primes and the corresponding hexagon cross, with the total number on the following line.
	public void printHexes() {
		for(int i=0; i < hexagon.size(); ++i) {
			System.out.println("Primes Pair: " + hexagon.get(i).leftNum + " and " + hexagon.get(i).rightNum + " seperated by " + hexagon.get(i).leftNum.leftNum.add(BigInteger.ONE) + " , " + hexagon.get(i).rightNum.leftNum.add(BigInteger.ONE));
		}
		System.out.println("Total Hexes: " + hexagon.size());
	}
		
	// Generate and store a list of primes.
	public void generatePrimes(int count) {
		BigInteger numPrimes = new BigInteger("1");
		BigInteger num = new BigInteger("3");
		this.primes.add(BigInteger.valueOf(2));
		while(numPrimes.compareTo(BigInteger.valueOf(count)) < 0) {
			if(isPrime(num)) {
				addPrime(num);
				numPrimes = numPrimes.add(BigInteger.valueOf(1));
			}
			num = num.add(BigInteger.valueOf(2));
		}

	}
	
	// Generate and store a list of twin primes.
	public void generateTwinPrimes() {
		for(int i = 0; i < primes.size()-1; ++i) {
			if(primes.get(i+1).subtract(primes.get(i)).equals(BigInteger.valueOf(2))) {
				Pair<BigInteger> twinPair = new Pair<BigInteger>(primes.get(i),primes.get(i+1));
				twinPrimes.add(twinPair);
			}
		}
	}
	
	// Generate and store the hexagon crosses, along with the two twin primes that generate the hexagon cross.
	public void generateHexPrimes() {
		for(int i = 0; i < twinPrimes.size();++i) {
			for(int j = i; j < twinPrimes.size();++j) {
				if(twinPrimes.get(i).leftNum.add(BigInteger.ONE).multiply(BigInteger.valueOf(2)).equals(twinPrimes.get(j).leftNum.add(BigInteger.ONE))) {
					Pair<BigInteger> leftPair = new Pair<BigInteger>(twinPrimes.get(i).leftNum,twinPrimes.get(i).rightNum);
					Pair<BigInteger> rightPair = new Pair<BigInteger>(twinPrimes.get(j).leftNum,twinPrimes.get(j).rightNum);
					Pair<Pair<BigInteger>> cross = new Pair<Pair<BigInteger>>(leftPair,rightPair);
					hexagon.add(cross);
				}
			}
		}
	}
}
