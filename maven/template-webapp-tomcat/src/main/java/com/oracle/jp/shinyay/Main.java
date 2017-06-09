package com.oracle.jp.shinyay;

import com.oracle.jp.shinyay.servlet.HelloServlet;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Optional;

/**
 * Main class.
 */
public class Main {

    public static final String BASE_URI;
    public static final String PROTOCOL;
    public static String HOST;
    public static final String HOST_LOCALHOST = "localhost";
    public static final String HOST_DEFAULT_ROOT = "0.0.0.0";
    public static final String APPLICATION_ROOT = ".";
    public static final String CONTEXT_PATH = "";
    public static final Optional<String> PORT;
    public static final Optional<String> APP_HOME;

    static {
        PROTOCOL = "http://";
        HOST = HOST_LOCALHOST;
        PORT = Optional.ofNullable(System.getenv("PORT"));
        APP_HOME = Optional.ofNullable(System.getenv("APP_HOME"));
        if (APP_HOME.isPresent()) {
            HOST = HOST_DEFAULT_ROOT;
        }
        BASE_URI = PROTOCOL
                + HOST
                + ":"
                + PORT.orElseGet(() -> "8080")
                + "/";
    }

    public static Tomcat startServer() throws ServletException {

        Tomcat server = new Tomcat();
        server.setPort(Integer.parseInt(PORT.orElseGet(() -> "8080")));
        server.getHost().setAppBase(APPLICATION_ROOT);
        server.addWebapp(CONTEXT_PATH, APPLICATION_ROOT);
        server.addServlet(CONTEXT_PATH, "HelloServlet", new HelloServlet()).addMapping("/*");

        return server;
    }

    /**
     * Main method.
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException, LifecycleException, ServletException {
        final Tomcat server = startServer();
        server.start();
//        server.getServer().await();
        System.out.println(String.format("Tomcat app started at "
                + "%s\nHit enter to stop it...", BASE_URI));
        System.in.read();
        server.stop();
    }
}

