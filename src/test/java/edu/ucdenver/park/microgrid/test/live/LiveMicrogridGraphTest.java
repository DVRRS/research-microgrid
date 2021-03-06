/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE', which is part of this source code package.
 */
package edu.ucdenver.park.microgrid.test.live;

import edu.ucdenver.park.microgrid.data.MicrogridGraph;
import edu.ucdenver.park.microgrid.dummy.DummyMicrogrid;
import edu.ucdenver.park.microgrid.live.LiveMicrogridGraph;
import edu.ucdenver.park.microgrid.message.MicrogridGraphMessage;
import org.junit.jupiter.api.Test;

class LiveMicrogridGraphTest {
    @Test
    void shouldStartWithEmptyState() {
        LiveMicrogridGraph empty = new LiveMicrogridGraph();

        MicrogridGraph state = empty.getCurrentState();

        assert state.getEdges().size() == 0;
        assert state.getNodes().size() == 0;
    }

    @Test
    void shouldAddDummyGraphToState() {
        LiveMicrogridGraph live = new LiveMicrogridGraph();
        MicrogridGraphMessage message = new MicrogridGraphMessage(new DummyMicrogrid(), System.currentTimeMillis()+60000);
        live.receiveMessage(message);
        MicrogridGraph state = live.getCurrentState();

        assert state.getEdges().size() > 0;
        assert state.getNodes().size() > 0;
    }

    @Test
    void shouldExpireDummyGraphState() {
        LiveMicrogridGraph live = new LiveMicrogridGraph();
        MicrogridGraphMessage message = new MicrogridGraphMessage(new DummyMicrogrid(), System.currentTimeMillis()-1000);
        live.receiveMessage(message);
        MicrogridGraph state = live.getCurrentState();

        assert state.getEdges().size() == 0;
        assert state.getNodes().size() == 0;
    }

}