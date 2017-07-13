package org.educama.shipment.boundary;

import java.util.List;

import org.educama.shipment.api.datastructure.ShipmentPlanItemDS;
import org.educama.shipment.api.datastructure.ShipmentTaskDS;


/**
 * Boundary service for shipment tasks.
 */
public interface ShipmentTaskBoundaryService {
    /**
     * Retrieves all tasks.
     *
     * @return a collection of all tasks
     */
     List<ShipmentTaskDS> findAllActive();
     /**
      * Retrieves all enabled plan items for a particular trackingId.
      *
      * @return a collection of all enabled plan items
      */
      List<ShipmentPlanItemDS> findAllEnabled(String trackingId);
}
