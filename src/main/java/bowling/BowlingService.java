package bowling;

import static java.util.Arrays.stream;

public class BowlingService {

  public int computeScore(String pinsString) {
    int[] pins = parse(pinsString);
    return computeBasicScore(pins) + computeSpareBonus(pins) + computeStrikeBonus(pins);
  }

  private int computeStrikeBonus(int[] pins) {
    int bonus = 0;
    int pinIndex = 0;
    int frameIndex = 0;
    while (frameIndex < 10) {
      if (pins[pinIndex] == 10) {
        bonus += pins[pinIndex + 1] + pins[pinIndex + 2];
        frameIndex++;
        pinIndex++;
      } else if (pins[pinIndex] + pins[pinIndex + 1] == 10) {
        pinIndex += frameIndex < 9 ? 2 : 3;
        frameIndex++;
      } else {
        frameIndex++;
        pinIndex += 2;
      }
    }
    return bonus;
  }


  private int computeSpareBonus(int[] pins) {
    int bonus = 0;
    int pinIndex = 0;
    int frameIndex = 0;
    while (frameIndex < 10) {
      if (pins[pinIndex] == 10) {
        frameIndex++;
        pinIndex++;
      } else if (pins[pinIndex] + pins[pinIndex + 1] == 10) {
        bonus += pins[pinIndex + 2];
        pinIndex += frameIndex < 9 ? 2 : 3;
        frameIndex++;
      } else {
        frameIndex++;
        pinIndex += 2;
      }
    }

    return bonus;
  }


  private int[] parse(String pinsString) {
    return stream(pinsString.split(",")).mapToInt(Integer::parseInt).toArray();
  }

  private int computeBasicScore(int[] pins) {
    return stream(pins).sum();
  }
}
