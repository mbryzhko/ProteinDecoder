package bma.dna;

import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

public class DecoderTest {

	@Test
	public void verifyThatDnaCanBeDecripted() {
		Decoder d = new Decoder(
				"ACATGGTGCACCTGACTCTCATTTGAGATATAAAAAAACCATGAGATCGATGGCGCTACGCATAATATAAAAA");
		List<String> p = d.proteins();
		Assert.assertThat(p.size(), CoreMatchers.is(2));
		Decoder.printProteins(p);
	}

	@Test
	public void verifyPefrormance() {
		int times = 100000;
		long start = System.nanoTime();
		for (int i = 0; i < times; i++) {
			Decoder d = new Decoder(
			"ACATGGTGCACCTGACTCTCATTTGAGATATAAAAAAACCATGAGATCGATGGCGCTACGCATAATATAAAAA");
			d.proteins();
		}
		long end = System.nanoTime();
		System.out.println("Performance test. cycles: " + times);
		System.out.println("Average, nanosec: " + (end - start) / times);
	}
}
