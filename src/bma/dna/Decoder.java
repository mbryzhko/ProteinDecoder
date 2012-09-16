package bma.dna;

import java.util.LinkedList;
import java.util.List;

public class Decoder {

	private final String dna;

	public Decoder(String dna) {
		this.dna = dna;
	}

	public List<String> proteins() {
		List<String> result = new LinkedList<String>();
		StringBuilder current = new StringBuilder();

		DnaReader reader = new DnaReader(dna);
		Codon codon = Codon.UNK;
		Step step = Step.LOOKING_START;

		while (reader.hasNextCodon()) {
			switch (step) {
			case LOOKING_START:
				codon = readCodon(reader, false);
				if (codon.isStart()) {
					current.append(codon.getCode());
					step = Step.START_FOUND;
				}
				break;
			case START_FOUND:
				// Skip two chars to set cursor on next codon
				reader.skip(2);
				step = Step.LOOKING_PROTEIN;
				break;
			case LOOKING_PROTEIN:
				codon = readCodon(reader, true);
				if (codon.isProtein()) {
					current.append(codon.getCode());
				} else if (codon.isStop()) {
					step = Step.END_FOUND;
				} else {
					System.out.println("Unknown protein has been read");
				}
				break;
			case END_FOUND:
				result.add(current.toString());
				current = new StringBuilder();
				step = Step.LOOKING_START;
				break;
			}
		}
		return result;
	}

	private Codon readCodon(DnaReader reader, boolean shift) {
		return Codon.getCodonByName(reader.readCodon(shift));
	}

	public static void printProteins(List<String> proteins) {
		for (String s : proteins) {
			System.out.println(s);
		}
	}

	private enum Step {
		LOOKING_START, START_FOUND, LOOKING_PROTEIN, END_FOUND;
	}
}
