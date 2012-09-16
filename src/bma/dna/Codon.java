package bma.dna;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public enum Codon {
	ATG("M"),
	GTG("V"),
	CAC("H"),
	CTG("L"),
	ACT("T"),
	CTC("L"),
	ATT("I"),
	AGA("R"),
	TCG("S"),
	GCG("A"),
	CTA("L"),
	CGC("R"),
	ATA("I"),
	TAA("STOP"),
	TGA("STOP"),
	TAG("STOP"),
	UNK("?");
	
	private static Set<Codon> PROTEINS = new HashSet<Codon>();
	private static Set<String> ALL_CODES = new HashSet<String>();
	
	static {
		Collections.addAll(PROTEINS, ATG, GTG, CAC, CTG, ACT, CTC, ATT, AGA,
				TCG, GCG, CTA, CGC, ATA);

		for (Codon c : values()) {
			ALL_CODES.add(c.name());
		}
	}
	                
	Codon(String code) {
		this.code = code;
	}
	
	private String code;

	public String getCode() {
		return code;
	}
	
	public boolean isStart() {
		return this == ATG;
	}
	
	public boolean isProtein() {
		return PROTEINS.contains(this);
	}
	
	public boolean isStop() {
		return this == TAA ||  this == TGA || this == TAG;
	}
		
	public static Codon getCodonByName(String name) {
		return ALL_CODES.contains(name) ? valueOf(name) : UNK;
	}
}
