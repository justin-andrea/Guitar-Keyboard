import java.lang.Math;

public class GuitarHero {
	public static void main(String[] args) throws Exception {
		int numNotes = 37;

		String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

		GuitarString[] gStrings = new GuitarString[numNotes];

		// initialize
		for (int i = 0; i < numNotes; i++)
			gStrings[i] = new GuitarString(440.0 * Math.pow(2.0, (i - 24.0) / 12.0));

		while (true) {

			// check if the user has typed a key; if so, process it   
			if (StdDraw.hasNextKeyTyped()) {
				char key = StdDraw.nextKeyTyped();
				if (keyboard.indexOf(key) >= 0) {
					gStrings[keyboard.indexOf(key)].pluck();
				}
			}

			// compute the superposition of samples
			// advance the simulation of each guitar string by one step   
			double sample = 0.0;
			for (int i = 0; i < numNotes; i++) {
				sample += gStrings[i].sample();
				gStrings[i].tic();
			}

			// send the result to the sound card
			StdAudio.play(sample);

		}
	}
}