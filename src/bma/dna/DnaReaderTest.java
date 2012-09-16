package bma.dna;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

public class DnaReaderTest {

	@Test
	public void verifyThatThreeLetterCodonCanBeRead() {
		DnaReader reader = new DnaReader("ABCD");
		String codon = reader.readCodon(false);
		Assert.assertThat(codon, CoreMatchers.equalTo("ABC"));
	}

	@Test
	public void verifyThatBCDisReturnedOnSecondReadedWithoutShift() {
		DnaReader reader = new DnaReader("ABCD");
		reader.readCodon(false);
		String codon = reader.readCodon(false);
		Assert.assertThat(codon, CoreMatchers.equalTo("BCD"));
	}

	@Test
	public void verifyThatDEFisReturnedOnSecondReadedWithoutShift() {
		DnaReader reader = new DnaReader("ABCDEF");
		reader.readCodon(true);
		String codon = reader.readCodon(true);
		Assert.assertThat(codon, CoreMatchers.equalTo("DEF"));
	}

	@Test(expected = IllegalStateException.class)
	public void veifyThatExceptionIsThrownIfNotEnoughToReade() {
		DnaReader reader = new DnaReader("AB");
		reader.readCodon(false);
	}

	@Test
	public void verifyThatHasNextCodonReturnsTrueIfMoreCodonsCabBeRead() {
		DnaReader reader = new DnaReader("ABCD");
		Assert.assertThat(reader.hasNextCodon(), CoreMatchers.is(true));
	}
	
	@Test
	public void verifyThatHasNextCodonReturnsFalseIfNoMoreCodonsCabBeRead() {
		DnaReader reader = new DnaReader("AB");
		Assert.assertThat(reader.hasNextCodon(), CoreMatchers.is(false));
	}
	
	@Test
	public void verifyThatCharsInDnaCanBeSkipped() {
		DnaReader reader = new DnaReader("ABCDEF");
		reader.skip(2);
		String codon = reader.readCodon(false);
		Assert.assertThat(codon, CoreMatchers.equalTo("CDE"));
	}
}
