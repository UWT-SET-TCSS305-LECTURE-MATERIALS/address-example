package edu.uw.tcss.model;

import java.util.Objects;

/**
 * This class deemonstrates concepts related to class design.
 * @author Charles Bryan
 * @version Autumn 2024 1.2
 */
public class MailingAddress implements Address {

    /** Default value for addresses without a second line. */
    private static final String NO_SECOND_LINE = "";

    /** The minimum allowed value for zip codes. */
    private static final int MINIMUM_ZIP = 501;

    /** The minimum allowed value for zip codes. */
    private static final int MAXIMUM_ZIP = 99950;

    /** Stores the first line of Address objects. */
    private final String myFirstLine;

    /**
     * Stores the second line of Address objects. Use NO_SECOND_LINE
     * for Address objects that do not require a second line.
     */
    private final String mySecondLine;

    /** Stores the City of Address objects. */
    private final String myCity;


    /** Stores the State of Address objects. */
    private final String myState;

    /**
     * Stores the Zip of Address objects. Zip Codes must be in the range
     * [501-99950]
     */
    private final int myZip;

    /**
     * Instantiates an Address object that does not require a Second Address line.
     * <br />
     * Zip codes must be in the range [501-99950].
     * @param theFirstLine The Street number and name of this Address object
     * @param theCity The City name of this Address object
     * @param theState The State name of this Address object
     * @param theZip The Zip of this Address object
     * @throws IllegalArgumentException when Firstline, City, or State are the empty String
     * or when the Zip Code is outside of the valid range.
     * @throws NullPointerException when Firstling, Ciry, or State are null
     */
    public MailingAddress(final String theFirstLine,
                          final String theCity,
                          final String theState,
                          final int theZip) {
        this(theFirstLine, NO_SECOND_LINE, theCity, theState, theZip);
    }

    /**
     * Instantiates an Address object that require a Second Address line.
     * <br />
     * Zip codes must be in the range [501-99950].
     * <br />
     * The argurment, theSecondLine may be the empty String. In this case, the Address
     * object reverts to an Address Object without a second line and will return false
     * on calls to hasSecondLine(). Note, null is not allowed for this argument.
     *
     * @param theFirstLine The Street number and name of this Address object
     * @param theSecondLine The optional additional information of this Address object
     * @param theCity The City name of this Address object
     * @param theState The State name of this Address object
     * @param theZip The Zip of this Address object
     * @throws IllegalArgumentException when Firstline, City, or State are the empty String
     * or when the Zip Code is outside of the valid range.
     * @throws NullPointerException when Firstline, SecondLine, City, or State are null
     */
    public MailingAddress(final String theFirstLine,
                          final String theSecondLine,
                          final String theCity,
                          final String theState,
                          final int theZip) {
        super();
        if (Objects.requireNonNull(theFirstLine).isEmpty()
                || Objects.requireNonNull(theState).isEmpty()
                || Objects.requireNonNull(theCity).isEmpty()
                || theZip < MINIMUM_ZIP || theZip > MAXIMUM_ZIP) {
            throw new IllegalArgumentException();
        }
        myFirstLine = theFirstLine;
        mySecondLine = Objects.requireNonNull(theSecondLine);
        myCity = theCity;
        myState = theState;
        myZip = theZip;
    }

    @Override
    public String getFirstLine() {
        return myFirstLine;
    }

    /**
     * Accessor for this Address object's optional additional information.
     * Note, a call to hasSecondLine() should be made before calls to this method.
     *
     * @return the optional additional information
     * @throws IllegalStateException when calls to hasSecondLine() return false
     */
    @Override
    public String getSecondLine() {
        if (mySecondLine == null) {
            throw new IllegalStateException("No Second line!");
        }
        return mySecondLine;
    }


    @Override
    public String getCity() {
        return myCity;
    }


    @Override
    public String getState() {
        return myState;
    }


    @Override
    public int getZip() {
        return myZip;
    }

    /**
     * Returns a String representation of thie Address object's mailing label.
     * <br />When calls to this Address object's hasSecondLine() return true,
     * the result of this method will follow this format:
     * <br />
     * [First Line][newline character]<br />
     * [Second Line][newline character]<br />
     * [City], [State] [Zip]<br />
     * <br />When calls to this Address object's hasSecondLine() return false,
     * the result of this method will follow this format:
     * <br />
     * [First Line][newline character]<br />
     * [City], [State] [Zip]<br />
     * @return a String representation of thie Address object's mailing label
     */
    @Override
    public String generateLabel() {
        final StringBuilder result = new StringBuilder(myFirstLine);
        result.append('\n');
        if (hasSecondLine()) {
            result.append(mySecondLine);
            result.append('\n');
        }
        result.append(myCity);
        result.append(", ");
        result.append(myState);
        result.append(" ");
        result.append(myZip);
        return result.toString();
    }


    @Override
    public boolean hasSecondLine() {
        return mySecondLine != null;
    }
}
