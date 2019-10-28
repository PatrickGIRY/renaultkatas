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
    var pins = stream(pinsString.split(",")).mapToInt(Integer::parseInt).toArray();
    int pinIndex = 0;
    int frameIndex = 0;
    BowlingFrame previousFrame = null;
    while (frameIndex < 10) {
      BowlingFrame frame;
      if (pins[pinIndex] == 10) {
        frame = new BowlingFrame(previousFrame, pins[pinIndex]);
        frameIndex++;
        pinIndex++;
      } else if (pins[pinIndex] + pins[pinIndex + 1] == 10) {
        if (frameIndex < 9) {
          frame = new BowlingFrame(previousFrame, pins[pinIndex], pins[pinIndex + 1]);
          pinIndex += 2;
        } else {
          frame =
              new BowlingFrame(previousFrame, pins[pinIndex], pins[pinIndex + 1], pins[pinIndex + 2]);
          pinIndex += 3;
        }
        frameIndex++;
      } else {
        frame = new BowlingFrame(previousFrame, pins[pinIndex], pins[pinIndex + 1]);
        frameIndex++;
        pinIndex += 2;
      }
      previousFrame = frame;
    }
    return null;
  }

  private int computeBasicScore(int[] pins) {
    return stream(pins).sum();
  }

  private class BowlingFrame {
    private BowlingFrame previousFrame;
    private int firstThrow;
    private int secondThrow;
    private int bonusThrow;

    public BowlingFrame(BowlingFrame previousFrame, int firstThrow) {
      this.previousFrame = previousFrame;
      this.firstThrow = firstThrow;
    }

    public BowlingFrame(BowlingFrame previousFrame, int firstThrow,
                        int secondThrow) {
      this.previousFrame = previousFrame;
      this.firstThrow = firstThrow;
      this.secondThrow = secondThrow;
    }

    public BowlingFrame(BowlingFrame previousFrame, int firstThrow, int secondThrow, int bonusThrow) {
      this.previousFrame = previousFrame;
      this.firstThrow = firstThrow;
      this.secondThrow = secondThrow;
      this.bonusThrow = bonusThrow;
    }
  }
}
