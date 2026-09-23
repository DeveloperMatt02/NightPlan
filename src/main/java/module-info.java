module it.uniroma2.nightplan {
    opens it.uniroma2.nightplan.graphiccontrollers;
    opens it.uniroma2.nightplan.controllers;
    opens it.uniroma2.nightplan.utils;
    opens it.uniroma2.nightplan.beans;
    opens it.uniroma2.nightplan.model;
    opens it.uniroma2.nightplan.view;
    opens it.uniroma2.nightplan.server;

    requires java.base;
    requires java.net.http;
    requires java.desktop;
    requires java.logging;
    requires transitive java.sql;

    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires javafx.web;
    requires transitive javafx.graphics;

    requires org.json;

    requires google.api.client;
    requires com.google.api.client;
    requires com.google.api.client.auth;
    requires com.google.api.client.extensions.java6.auth;
    requires com.google.api.client.extensions.jetty.auth;
    requires com.google.api.client.json.gson;
    requires com.google.api.client.json.jackson2;
    requires com.opencsv;

    exports it.uniroma2.nightplan.view;
    exports it.uniroma2.nightplan.graphiccontrollers;
    exports it.uniroma2.nightplan.controllers;
    exports it.uniroma2.nightplan.utils;
    exports it.uniroma2.nightplan.beans;
    exports it.uniroma2.nightplan.exceptions;
    exports it.uniroma2.nightplan.server;
    exports it.uniroma2.nightplan.utils.enums;
    exports it.uniroma2.nightplan.model;

    opens it.uniroma2.nightplan.utils.enums;
    exports it.uniroma2.nightplan.controllers.factory;
    opens it.uniroma2.nightplan.controllers.factory;

}