package mancala.api;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.*;

import mancala.api.models.*;
import mancala.domain.MancalaImpl;
import mancala.domain.MancalaException;

@Path("/play")
public class playPit {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response initialize(
            @Context HttpServletRequest request,
            PitIndex index) throws MancalaException {

        HttpSession session = request.getSession(true);
        MancalaImpl mancala = (MancalaImpl) session.getAttribute("mancala");
        String namePlayer1 = (String) session.getAttribute("player1");
        String namePlayer2 = (String) session.getAttribute("player2");

        System.out.println(index.getPitIndex());
        mancala.playPit(index.getPitIndex());

    var output = new Mancala(mancala, namePlayer1, namePlayer2);
    return Response.status(200).entity(output).build();
    }
}