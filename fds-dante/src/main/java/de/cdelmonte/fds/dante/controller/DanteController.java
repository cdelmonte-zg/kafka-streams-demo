package de.cdelmonte.fds.dante.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import de.cdelmonte.fds.dante.dto.ResponseDTO;
import de.cdelmonte.fds.dante.service.DanteService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "v1/status")
public class DanteController {

  @Autowired
  private DanteService danteService;

  @ApiOperation(value = "Retrieve status from Dante Realtime Fraud Detection System.",
      response = Iterable.class)
  @RequestMapping(value = "/frauds/", method = RequestMethod.GET)
  public ResponseDTO searchMerchantsOverElasticSearch() {
    return danteService.getFraudsStatus();
  }
}
