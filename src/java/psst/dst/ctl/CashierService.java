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
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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
import psst.dst.entity.PsstCashier;
import psst.dst.itf.ICashierDataService;
import psst.dst.service.EntityBase;
import psst.dst.service.Server;

/**
 *
 * @author root
 */
@Path("/cashier")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CashierService extends EntityBase implements ICashierDataService<PsstCashier> {

    public final static String IMG_FILE = "cashier/img/";
    public static final String IMG_FOLDER = Server.SERVER_FOLDER + "cashier/img/";

    public final static String DOC_FILE = "cashier/doc/";
    public static final String DOC_FOLDER = Server.SERVER_FOLDER + "cashier/doc/";

    int ext;

    @GET
    @Path("list")
    @Transactional
    @Override
    public List<PsstCashier> findAll() {
        List<PsstCashier> cashList = new ArrayList<>();
        con = connect();
        try {

            Statement stmt = con.createStatement();
            String query = "SELECT `cash_id`, `name`, `phone`, `email`, `doc`, `img`, `pwd`, `created_at` FROM `psst_cashier` ORDER BY `cash_id` ASC";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                PsstCashier cashier = new PsstCashier();
                cashier.setCashId(rs.getInt(1));
                cashier.setName(rs.getString(2));
                cashier.setPhone(rs.getString(3));
                cashier.setEmail(rs.getString(4));
                cashier.setDoc(rs.getString(5));
                cashier.setImg(rs.getString(6));
                cashier.setPwd(rs.getString(7));
                cashier.setCreatedAt(rs.getString(8));

                cashList.add(cashier);

            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(CashierService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cashList;
    }

    @DELETE
    @Path("/rm/{cashId}")
    @Transactional
    @Override
    public Response remove(@PathParam("cashId") Integer cashId) {
        try {
            con = connect();
            String query = "DELETE FROM `psst_cashier` WHERE `cash_id` = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, cashId);
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

    @PUT
    @Path("/put/{cashId}")
    @Transactional
    @Override
    public Response edit(PsstCashier entity) {

        try {
            con = connect();
            String query = "UPDATE `psst_cashier` SET `name`= ?,`phone`= ?,`email`= ?,`doc`= ?,`img` = ? ,`pwd`= ?, `created_at` = ? WHERE `cash_id`= ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, entity.getName());
            stmt.setString(2, entity.getPhone());
            stmt.setString(3, entity.getEmail());
            stmt.setString(4, entity.getDoc());
            stmt.setString(5, entity.getImg());
            stmt.setString(6, entity.getPwd());
            stmt.setString(7, entity.getCreatedAt());
            stmt.setInt(8, entity.getCashId());
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

    @GET
    @Path("/find/id/{cashId}")
    @Transactional
    @Override
    public PsstCashier find(@PathParam("cashId") Integer cashId) {
        PsstCashier cashier = new PsstCashier();
        try {
            con = connect();
            String query = "SELECT `cash_id`, `name`, `phone`, `email`, `doc`, `img`, `pwd`, `created_at` FROM `psst_cashier` WHERE `cash_id` = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, cashId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                cashier.setCashId(rs.getInt(1));
                cashier.setName(rs.getString(2));
                cashier.setPhone(rs.getString(3));
                cashier.setEmail(rs.getString(4));
                cashier.setDoc(rs.getString(5));
                cashier.setImg(rs.getString(6));
                cashier.setPwd(rs.getString(7));
                cashier.setCreatedAt(rs.getString(8));
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(CashierService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cashier;
    }

    @GET
    @Path("/find/name/{name}")
    @Transactional
    @Override
    public PsstCashier findBynama(@PathParam("name") String name) {
        PsstCashier cashier = new PsstCashier();
        try {
            con = connect();
            String query = "SELECT `cash_id`, `name`, `phone`, `email`, `doc`, `img`, `pwd`, `created_at` FROM `psst_cashier` "
                    + "WHERE `name` LIKE ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1,"%" +name + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                cashier.setCashId(rs.getInt(1));
                cashier.setName(rs.getString(2));
                cashier.setPhone(rs.getString(3));
                cashier.setEmail(rs.getString(4));
                cashier.setDoc(rs.getString(5));
                cashier.setImg(rs.getString(6));
                cashier.setPwd(rs.getString(7));
                cashier.setCreatedAt(rs.getString(8));
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(CashierService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cashier;
    }

    @Override
    public Response edit(Integer cashId, String gcm_token) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @POST
    @Path("/img")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Override
    public Response upImg(@FormDataParam("img") InputStream fileInputStream,
            @FormDataParam("img") FormDataContentDisposition fileMetaData) {
        try {
            ext = 1;
            writeToFileServer(fileInputStream, fileMetaData.getFileName());

        } catch (IOException e) {
            throw new WebApplicationException("Error while uploading file. Please try again !!");
        }
        return Response.status(Response.Status.CREATED).build();
    }

    @POST
    @Path("/doc")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Override
    public Response upDoc(@FormDataParam("doc") InputStream fileInputStream,
            @FormDataParam("doc") FormDataContentDisposition fileMetaData) {
        try {
            ext = 2;
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
        if (ext == 1) {
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
        } else if (ext == 2) {
            String qualifiedUploadFilePath = DOC_FOLDER + fileName;

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
                flush = false;
                return flush;
            }
        }
        return flush;

    }

    private Boolean updateToDb(int cashId, String data) {
        Boolean save = null;
        try {
            con = connect();
            if (ext == 1) {
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
            } else if (ext == 2) {
                String query = "UPDATE `psst_cashier` SET `doc` = ? WHERE `cash_id` = ?";
                PreparedStatement stmt = con.prepareStatement(query);
                stmt.setString(1, DOC_FILE + data);
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
            }

        } catch (SQLException ex) {
            disconect();
            return save;
        }
        return save;
    }

}
