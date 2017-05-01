package dk.dtu.view;

import org.glassfish.jersey.server.mvc.Template;
import org.glassfish.jersey.server.mvc.Viewable;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

public class mvc {
    @Path("foo")
    public class Foo {

        @GET
        public Viewable get() {
            return new Viewable("index.foo", "FOO");
        }
    }
}
