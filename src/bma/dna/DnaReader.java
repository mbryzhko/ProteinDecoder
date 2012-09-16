package bma.dna;

public class DnaReader {

	private char[] ca;
	private int pos;
	private static final int CODON_LEN = 3;

	public DnaReader(String dna) {
		ca = dna.toCharArray();
		pos = 0;
	}

	public String readCodon(boolean shift) {
		if (!canReader())
			throw new IllegalStateException("EOF");
		String result = String.valueOf(nextThreeChar());
		//System.out.println("Read codon: " + result);
		if (shift) {
			pos += 3;
		} else {
			pos++;
		}
		return result;
	}

	private boolean canReader() {
		return ca.length - pos >= CODON_LEN;
	}
	
	private char[] nextThreeChar() {
		return new char[] { ca[pos], ca[pos + 1], ca[pos + 2] };
	}

	public boolean hasNextCodon() {
		return canReader();
	}

	public void skip(int i) {
		pos += i;
	}
}
