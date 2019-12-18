package com.eduard.consoleReader;

import com.eduard.model.FlightException;

public interface ActionHandler {
   void doAction(Handler handler) throws FlightException;
}
