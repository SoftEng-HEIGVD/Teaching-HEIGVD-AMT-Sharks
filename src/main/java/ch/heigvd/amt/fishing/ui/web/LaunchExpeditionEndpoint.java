package ch.heigvd.amt.fishing.ui.web;

import ch.heigvd.amt.fishing.application.ExpeditionOutcomeDTO;
import ch.heigvd.amt.fishing.application.FishingExpeditionFacade;
import ch.heigvd.amt.fishing.application.LaunchExpeditionCommand;
import ch.heigvd.amt.fishing.domain.equipment.BoatType;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LaunchExpeditionEndpoint", urlPatterns = "/launchExpedition")
public class LaunchExpeditionEndpoint extends HttpServlet {

  @Inject
  FishingExpeditionFacade fishingExpeditionFacade;

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    int budget = Integer.parseInt(request.getParameter("budget"));
    int crewSize = Integer.parseInt(request.getParameter("crewSize"));
    BoatType boatType = BoatType.valueOf(request.getParameter("boatType"));

    LaunchExpeditionCommand launchExpeditionCommand = LaunchExpeditionCommand.builder()
      .budget(budget)
      .crewSize(crewSize)
      .boatType(boatType)
      .build();

    ExpeditionOutcomeDTO expeditionOutcomeDTO = fishingExpeditionFacade.launchExpedition(launchExpeditionCommand);

    request.setAttribute("expeditionOutcome", expeditionOutcomeDTO);
    request.getRequestDispatcher("/WEB-INF/views/expeditionOutcome.jsp").forward(request, response);
  }

}
