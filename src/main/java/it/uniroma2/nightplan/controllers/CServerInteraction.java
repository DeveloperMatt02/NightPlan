package it.uniroma2.nightplan.controllers;

import java.util.concurrent.Semaphore;

public class CServerInteraction {

    protected CServerInteraction(){}
    protected static Semaphore semaphore;
    protected static ClientListener listener;
    protected static Thread listenerThread;
    protected static CFacade facade;
}
