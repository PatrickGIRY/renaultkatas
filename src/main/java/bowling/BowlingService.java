package bowling;

import static java.util.Arrays.stream;

public class BowlingService {

  public int computeScore(String pinsString) {
    int[] pins = parse(pinsString);
    return computeBasicScore(pins) + computeSpareBonus(pins);
  }

  private int computeSpareBonus(int[] pins) {
    int bonus = 0;
    for (int i = 2; i < pins.length; i+= 2) {
      bonus += computePreviousFrameSpareBonus(pins, i);
    }
    return bonus;
  }


  private int computePreviousFrameSpareBonus(int[] pins, int i)
  {
    return isPreviousFrameSpare(pins, i) ? pins[i] : 0;
  }


  private boolean isPreviousFrameSpare(int[] pins, int i)
  {
    return pins[i - 2] + pins[i - 1] == 10;
  }


  private int[] parse(String pinsString) {
    return stream(pinsString.split(",")).mapToInt(Integer::parseInt).toArray();
  }

  private int computeBasicScore(int[] pins) {
    return stream(pins).sum();
  }
}
