package br.com.quantati.shohs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author carlos
 */
@Path("/ws")
public class Redirect {

  @GET
  @Path("/shortlink/{link}")
  @Produces("text/plain")
  public Response shortlink(@PathParam("link") String link) throws IOException {
    URL url = new URL("http://cncopt.com/w/2" + link);
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("GET");
    conn.setRequestProperty("Content-Type", "text/plain");
    conn.setDoOutput(true);

    link = "";

    Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
    for (int c = in.read(); c != -1; c = in.read()) {
      link += (char) c;
    }

    return Response.ok()
      .header("Access-Control-Allow-Origin", "*")
      .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
      .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With")
      .status(200).entity("http://cncopt.com/" + link.trim()).build();

  }

}
