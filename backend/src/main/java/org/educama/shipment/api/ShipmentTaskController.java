package org.educama.shipment.api;

import java.util.List;

import org.educama.shipment.api.datastructure.ShipmentPlanItemDS;
import org.educama.shipment.api.datastructure.ShipmentTaskDS;
import org.educama.shipment.api.resource.ShipmentPlanItemListResource;
import org.educama.shipment.api.resource.ShipmentTaskListResource;
import org.educama.shipment.boundary.ShipmentTaskBoundaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST-Service to access shipment task resources.
 */
@RestController
public class ShipmentTaskController {

    public static final String SHIPMENT_ACTIVE_TASK_RESOURCE_PATH = "/educama/v1/tasks";
    public static final String SHIPMENT_ENABLED_TASK_RESOURCE_PATH = "/educama/v1/tasks/enabled";


    @Autowired
    private ShipmentTaskBoundaryService shipmentTaskBoundaryService;

    /**
     *
     * @return a Tasklist assigned to user "educama"
     */
    @RequestMapping(method = RequestMethod.GET, path = ShipmentTaskController.SHIPMENT_ACTIVE_TASK_RESOURCE_PATH, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ShipmentTaskListResource getActiveTasks() {
        List <ShipmentTaskDS> tasks = shipmentTaskBoundaryService.findAllActive();
        ShipmentTaskListResource taskList = new ShipmentTaskListResource().fromTaskCollection(tasks);
        return taskList;
    }
    /**
    * @param bases on the -by construction- precondition that the trackingId equals to the business key of the caseInstance
    * @return a list of enabled activities for a particular shipment id
    */
   @RequestMapping(method = RequestMethod.GET, path = ShipmentTaskController.SHIPMENT_ENABLED_TASK_RESOURCE_PATH, produces = {MediaType.APPLICATION_JSON_VALUE})
   public ShipmentPlanItemListResource getEnabledTasks(@RequestParam("trackingId") String trackingId) {
       List <ShipmentPlanItemDS> enabledPlanItems = shipmentTaskBoundaryService.findAllEnabled(trackingId);
       ShipmentPlanItemListResource enabledPlanItemsList = new ShipmentPlanItemListResource().fromPlanItemCollection(enabledPlanItems);
       return enabledPlanItemsList;
   }
}
