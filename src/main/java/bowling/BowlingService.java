package bowling;

import static java.util.Arrays.stream;

public class BowlingService {

  public int computeScore(String pinsString) {
    int[] pins = parse(pinsString);
    int scoreWithFirstFrameBonus = computeBasicScore(pins) + spareBonusForFirstFrame(pins);
    int secondFrameBonus = getSecondFrameBonus(pins);
    return scoreWithFirstFrameBonus + secondFrameBonus;
  }

    private int getSecondFrameBonus(int[] pins) {
        return pins[4];
    }

    private int[] parse(String pinsString) {
    return stream(pinsString.split(",")).mapToInt(Integer::parseInt).toArray();
  }

  private int spareBonusForFirstFrame(int[] pins) {
    return isFirstFrameSpare(pins) ? extractThirdThrowScore(pins) : 0;
  }

  private int computeBasicScore(int[] pins) {
    return stream(pins).sum();
  }

  private boolean isFirstFrameSpare(int[] pins) {
    return computeFirstFrameScore(pins) == 10;
  }

  private int computeFirstFrameScore(int[] pins) {
    return pins[0] + pins[1];
  }

  private int extractThirdThrowScore(int[] pins) {
    return pins[2];
  }
}
