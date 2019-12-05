package com.eduard.consoleReader;

import com.eduard.FlightException;

public interface ActionHandler {
   void doAction(Handler handler) throws FlightException;
}
