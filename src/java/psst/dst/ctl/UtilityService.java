/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psst.dst.ctl;

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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.transaction.annotation.Transactional;
import psst.dst.entity.PsstNoncoffeestock;
import psst.dst.entity.PsstUtility;
import psst.dst.itf.IUtilityDataService;
import psst.dst.service.EntityBase;

/**
 *
 * @author root
 */
@Path("/utilstock")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UtilityService extends EntityBase implements IUtilityDataService<PsstUtility> {

    @POST
    @Path("/create")
    @Transactional
    @Override
    public Response create(PsstUtility entity) {
        try {
            con = connect();
            PreparedStatement stmt = con.prepareStatement("INSERT INTO `psst_utility`(`name`, `amount`, `unit`) "
                    + "VALUES (?, ?, ?)");

            stmt.setString(1, entity.getName());
            stmt.setDouble(2, entity.getAmount());
            stmt.setString(3, entity.getUnit());
            //System.out.println(query);
            int rs = stmt.executeUpdate();
            System.out.println(rs + " records inserted");
            if (rs != 0) {
                con.close();
                return Response.status(Response.Status.ACCEPTED).build();
            } else {
                con.close();
                return Response.status(Response.Status.EXPECTATION_FAILED).build();
            }
        } catch (SQLException ex) {
            Logger.getLogger(UtilityService.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("list")
    @Transactional
    @Override
    public List<PsstUtility> findAll() {
        List<PsstUtility> utilList = new ArrayList<>();
        con = connect();
        try {

            Statement stmt = con.createStatement();
            String query = "SELECT `util_id`, `name`, `amount`, `unit`, `updated_at` "
                    + "FROM `psst_utility` ORDER BY `name` ASC";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                PsstUtility stock = new PsstUtility();
                stock.setUtilId(rs.getInt(1));
                stock.setName(rs.getString(2));
                stock.setAmount(rs.getDouble(3));
                stock.setUnit(rs.getString(4));
                stock.setUpdatedAt(rs.getString(5));
                utilList.add(stock);
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(UtilityService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return utilList;
    }

    @PUT
    @Path("/put/{utilId}")
    @Transactional
    @Override
    public Response edit(PsstUtility entity) {
        try {
            con = connect();
            String query = "UPDATE `psst_utility` SET `name`= ?,`amount`= ?,`unit`= ?,`updated_at`= ?"
                    + " WHERE `util_id`= ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, entity.getName());
            stmt.setDouble(2, entity.getAmount());
            stmt.setString(3, entity.getUnit());
            stmt.setString(4, entity.getUpdatedAt());
            stmt.setInt(5, entity.getUtilId());

            int rs = stmt.executeUpdate();
            if (rs != 0) {
                con.close();
                return Response.status(Response.Status.ACCEPTED).build();
            } else {
                con.close();
                return Response.status(Response.Status.EXPECTATION_FAILED).build();
            }
        } catch (SQLException ex) {
            Logger.getLogger(UtilityService.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/rm/{utilId}")
    @Transactional
    @Override
    public Response remove(@PathParam("utilId") Integer utilId) {
        try {
            con = connect();
            String query = "DELETE FROM `psst_utility` WHERE `util_id` = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, utilId);
            int rs = stmt.executeUpdate();
            if (rs != 0) {
                con.close();
                return Response.status(Response.Status.ACCEPTED).build();
            } else {
                con.close();
                return Response.status(Response.Status.EXPECTATION_FAILED).build();
            }
        } catch (SQLException ex) {
            Logger.getLogger(UtilityService.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/find/id/{utilId}")
    @Transactional
    @Override
    public PsstUtility find(@PathParam("utilId") Integer utilId) {
        PsstUtility stock = new PsstUtility();
        try {
            con = connect();
            String query = "SELECT `util_id`, `name`, `amount`, `unit`, `updated_at` FROM `psst_utility` "
                    + "WHERE `util_id` = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, utilId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                stock.setUtilId(rs.getInt(1));
                stock.setName(rs.getString(2));
                stock.setAmount(rs.getDouble(3));
                stock.setUnit(rs.getString(4));
                stock.setUpdatedAt(rs.getString(5));
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(UtilityService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stock;
    }

    @GET
    @Path("/find/name/{name}")
    @Transactional
    @Override
    public PsstUtility findByName(@PathParam("name") String name) {
        PsstUtility stock = new PsstUtility();
        try {
            con = connect();
            String query = "SELECT `util_id`, `name`, `amount`, `unit`, `updated_at` FROM `psst_utility` "
                    + "WHERE `name` LIKE ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, "%" + name + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                stock.setUtilId(rs.getInt(1));
                stock.setName(rs.getString(2));
                stock.setAmount(rs.getDouble(3));
                stock.setUnit(rs.getString(4));
                stock.setUpdatedAt(rs.getString(5));
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(UtilityService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stock;
    }

}
