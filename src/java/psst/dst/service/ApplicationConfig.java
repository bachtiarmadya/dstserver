/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psst.dst.service;

import java.util.Set;
import javax.ws.rs.core.Application;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

/**
 *
 * @author root
 */
@javax.ws.rs.ApplicationPath("api")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        resources.add(MultiPartFeature.class);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        
        resources.add(psst.dst.ctl.CashierService.class);
        resources.add(psst.dst.ctl.CoffeeStockService.class);
        resources.add(psst.dst.ctl.CustomerService.class);
        resources.add(psst.dst.ctl.MixItemService.class);
        resources.add(psst.dst.ctl.NonCoffeeStockService.class);
        resources.add(psst.dst.ctl.ServingTypeService.class);
        resources.add(psst.dst.ctl.TransactionService.class);
        resources.add(psst.dst.ctl.UtilityService.class);
        resources.add(psst.dst.service.LoginCashier.class);
        resources.add(psst.dst.service.LoginCust.class);
        resources.add(psst.dst.service.RegisterCashier.class);
        resources.add(psst.dst.service.RegisterCust.class);
    }
    
    
}
