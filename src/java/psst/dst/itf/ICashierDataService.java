/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psst.dst.itf;

import java.io.InputStream;
import java.util.List;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

/**
 *
 * @author root
 */
public interface ICashierDataService<T> {

    
    public List<T> findAll();

    public Response edit(T entity);

    public Response remove(@PathParam("cash_id") Integer cashId);

    public T find(@PathParam("cash_id") Integer cashId);

    public T findBynama(@PathParam("name") String name);

    public Response edit(@PathParam("cash_id") Integer cashId, @PathParam("gcm_token") String gcm_token);

    public Response upImg(@FormDataParam("file") InputStream fileInputStream,
            @FormDataParam("file") FormDataContentDisposition fileMetaData);
     public Response upDoc(@FormDataParam("file") InputStream fileInputStream,
            @FormDataParam("file") FormDataContentDisposition fileMetaData);

}
