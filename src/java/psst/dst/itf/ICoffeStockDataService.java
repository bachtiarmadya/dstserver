/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psst.dst.itf;

import java.util.List;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 *
 * @author root
 */
public interface ICoffeStockDataService <T> {

    public Response create(T entity);

    public List<T> findAll();

    public Response edit(T entity);

    public Response remove(@PathParam("coffeeId") Integer coffeeId);

    public T find(@PathParam("coffeeId") Integer coffeeId);

    public T findByName(@PathParam("name") String name);
}
