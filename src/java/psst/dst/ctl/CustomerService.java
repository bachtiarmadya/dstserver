/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psst.dst.ctl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.transaction.annotation.Transactional;
import static psst.dst.ctl.CashierService.DOC_FILE;
import static psst.dst.ctl.CashierService.DOC_FOLDER;
import static psst.dst.ctl.CashierService.IMG_FILE;
import static psst.dst.ctl.CashierService.IMG_FOLDER;
import psst.dst.entity.PsstCashier;
import psst.dst.entity.PsstCustomer;
import psst.dst.itf.ICustomerDataService;
import psst.dst.service.EntityBase;
import psst.dst.service.Server;

/**
 *
 * @author root
 */
@Path("/customer")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerService extends EntityBase implements ICustomerDataService<PsstCustomer> {

    public final static String IMG_FILE = "customer/img/";
    public static final String IMG_FOLDER = Server.SERVER_FOLDER + "customer/img/";

    @GET
    @Path("list")
    @Transactional
    @Override
    public List<PsstCustomer> findAll() {
        List<PsstCustomer> custList = new ArrayList<>();
        con = connect();
        try {

            Statement stmt = con.createStatement();
            String query = "SELECT `cust_id`, `name`, `phone`, `email`, `pwd`, `gcm_token`, `img`, `created_at` FROM `psst_customer`ORDER BY `name` ASC";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                PsstCustomer cust = new PsstCustomer();
                cust.setCustId(rs.getInt(1));
                cust.setName(rs.getString(2));
                cust.setPhone(rs.getString(3));
                cust.setEmail(rs.getString(4));
                cust.setPwd(rs.getString(5));
                cust.setGcmToken(rs.getString(6));
                cust.setImg(rs.getString(7));
                cust.setCreatedAt(rs.getString(8));

                custList.add(cust);

            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return custList;
    }

    @PUT
    @Path("/put/{custId}")
    @Transactional
    @Override
    public Response edit(PsstCustomer entity) {
        try {
            con = connect();
            String query = "UPDATE `psst_customer` SET `name`= ?,`phone`= ?,`email`= ?,`pwd`= ?,`gcm_token`= ?,`img`= ?,`created_at`= ?"
                    + " WHERE `cust_id` = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, entity.getName());
            stmt.setString(2, entity.getPhone());
            stmt.setString(3, entity.getEmail());
            stmt.setString(4, entity.getPwd());
            stmt.setString(5, entity.getGcmToken());
            stmt.setString(6, entity.getImg());
            stmt.setString(7, entity.getCreatedAt());
            stmt.setInt(8, entity.getCustId());
            //System.out.println(query);
            int rs = stmt.executeUpdate();
            if (rs != 0) {
                con.close();
                return Response.status(Response.Status.ACCEPTED).build();
            } else {
                con.close();
                return Response.status(Response.Status.EXPECTATION_FAILED).build();
            }
        } catch (SQLException ex) {
            Logger.getLogger(CashierService.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/rm/{custId}")
    @Transactional
    @Override
    public Response remove(@PathParam("custId") Integer custId) {
        try {
            con = connect();
            String query = "DELETE FROM `psst_customer` WHERE `cust_id` = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, custId);
            int rs = stmt.executeUpdate();
            if (rs != 0) {
                con.close();
                return Response.status(Response.Status.ACCEPTED).build();
            } else {
                con.close();
                return Response.status(Response.Status.EXPECTATION_FAILED).build();
            }
        } catch (SQLException ex) {
            Logger.getLogger(CashierService.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/find/id/{custId}")
    @Transactional
    @Override
    public PsstCustomer find(@PathParam("custId") Integer custId) {
        PsstCustomer customer = new PsstCustomer();
        try {
            con = connect();
            String query = "SELECT `cust_id`, `name`, `phone`, `email`, `pwd`, `gcm_token`, `img`, `created_at` "
                    + "FROM `psst_customer` WHERE `cust_id` = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, custId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                customer.setCustId(rs.getInt(1));
                customer.setName(rs.getString(2));
                customer.setPhone(rs.getString(3));
                customer.setEmail(rs.getString(4));
                customer.setPwd(rs.getString(5));
                customer.setGcmToken(rs.getString(6));
                customer.setImg(rs.getString(7));
                customer.setCreatedAt(rs.getString(8));
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(CashierService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return customer;
    }

    @GET
    @Path("/find/name/{name}")
    @Transactional
    @Override
    public PsstCustomer findBynama(@PathParam("name") String name) {
        PsstCustomer customer = new PsstCustomer();
        try {
            con = connect();
            String query = "SELECT `cust_id`, `name`, `phone`, `email`, `pwd`, `gcm_token`, `img`, `created_at` "
                    + "FROM `psst_customer` WHERE `name` LIKE ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, "%" + name + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                customer.setCustId(rs.getInt(1));
                customer.setName(rs.getString(2));
                customer.setPhone(rs.getString(3));
                customer.setEmail(rs.getString(4));
                customer.setPwd(rs.getString(5));
                customer.setGcmToken(rs.getString(6));
                customer.setImg(rs.getString(7));
                customer.setCreatedAt(rs.getString(8));
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(CashierService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return customer;
    }

    @Override
    public Response edit(Integer custId, String gcmToken) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @POST
    @Path("/img")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Override
    public Response upImg(@FormDataParam("img") InputStream fileInputStream,
            @FormDataParam("img") FormDataContentDisposition fileMetaData) {
        try {
            writeToFileServer(fileInputStream, fileMetaData.getFileName());

        } catch (IOException e) {
            throw new WebApplicationException("Error while uploading file. Please try again !!");
        }
        return Response.status(Response.Status.CREATED).build();
    }

    /**
     *
     * @param inputStream
     * @param fileName
     * @throws IOException
     */
    private Boolean writeToFileServer(InputStream inputStream, String fileName) throws IOException {

        Boolean flush = null;
        OutputStream outputStream = null;

        String qualifiedUploadFilePath = IMG_FOLDER + fileName;

        String filenameArray[] = fileName.split("\\.");
        Boolean success = updateToDb(Integer.valueOf(filenameArray[0]), fileName);
        if (success) {
            File file = new File(qualifiedUploadFilePath);
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            outputStream = new FileOutputStream(file);
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
            outputStream.flush();
            flush = true;
            return flush;
        } else {
            return flush = false;
        }

    }

    private Boolean updateToDb(int cashId, String data) {
        Boolean save = null;
        try {
            con = connect();
            String query = "UPDATE `psst_cashier` SET `img` = ? WHERE `cash_id` = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, IMG_FILE + data);
            stmt.setInt(2, cashId);
            //System.out.println(query);
            int rs = stmt.executeUpdate();
            if (rs != 0) {
                disconect();
                save = true;
                return save;
            } else {
                disconect();
                save = false;
                return save;
            }

        } catch (SQLException ex) {
            disconect();
            return save;
        }
    }

}
