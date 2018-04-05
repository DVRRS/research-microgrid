/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE', which is part of this source code package.
 */
package edu.ucdenver.park.microgrid.data.abs;

/**
 * Datum
 *
 * abstract class
 *
 * immutable
 *
 * This class is intended to represent one reading at one point in time from one instrument. Depending on the final
 *  protocol implementation, packets may contain multiple instances of data. Packets might only contain one datum.
 *
 * This class should fit into one INSERT call into a SQL database.
 *
 * The datum with the most recent timestamp should represent the most up-to-date reading of an instrument at a given
 *  time.
 *
 * This class is abstract because datum can have either float or boolean values. See the subclasses BooleanDatum and
 *  FloatDatum.
 *
 * This class also makes no claim to part of a microgrid system. See the subclass MicrogridDatum
 *
 * Important: the _id field of a Datum should be unique to the combination of its type and measurement location
 *  as a result, data from the same measurement location and type will have the same _id but different
 *  timestamp and value; this is important for data caching in LiveMicrogrid
 *
 * A unique identifier for an individual datum object should contain its _id AND its timestamp
 *
 *  @author Jake Billings
 */
public abstract class Datum extends Entity {
    /**
     * timestamp
     *
     * long
     *
     * the number of milliseconds from Jan 1, 1970 when this datum was recorded
     */
    private final long timestamp;

    public Datum(String _id, long timestamp) {
        super(_id);
        if (timestamp < 0) throw new IllegalArgumentException("timestamp must be positive when instantiating Datum object");
        this.timestamp = timestamp;
    }
}
