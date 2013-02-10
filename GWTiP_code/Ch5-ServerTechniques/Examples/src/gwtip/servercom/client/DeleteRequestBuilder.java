package gwtip.servercom.client;

import com.google.gwt.http.client.RequestBuilder;

public class DeleteRequestBuilder extends RequestBuilder {
  
   public DeleteRequestBuilder( String url) {
    super("DELETE", url);
  }
}
