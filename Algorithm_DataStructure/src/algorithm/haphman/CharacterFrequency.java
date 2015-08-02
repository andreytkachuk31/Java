package algorithm.haphman;

/**
 * @author Andrii_Tkachuk
 * @since 4/14/2015
 */
public class CharacterFrequency implements Comparable<CharacterFrequency> {

    private char symbol;
    private int frequency;

    public CharacterFrequency(char symbol, int frequency) {
        this.symbol = symbol;
        this.frequency = frequency;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CharacterFrequency that = (CharacterFrequency) o;

        if (frequency != that.frequency) return false;
        if (symbol != that.symbol) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) symbol;
        result = 31 * result + frequency;
        return result;
    }

    @Override
    public String toString() {
        return "{" + "symbol=" + symbol +  ", frequency=" + frequency +  '}';
    }

    @Override
    public int compareTo(CharacterFrequency o) {
        return frequency - o.getFrequency();
    }
}
