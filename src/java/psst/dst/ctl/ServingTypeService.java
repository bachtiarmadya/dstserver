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
import psst.dst.entity.PsstServingtype;
import psst.dst.entity.PsstUtility;
import psst.dst.itf.IServingTypeDataService;
import psst.dst.service.EntityBase;

/**
 *
 * @author root
 */
@Path("/servmod")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ServingTypeService extends EntityBase implements IServingTypeDataService<PsstServingtype> {

    @POST
    @Path("/create")
    @Transactional
    @Override
    public Response create(PsstServingtype entity) {
        try {
            con = connect();
            PreparedStatement stmt = con.prepareStatement("INSERT INTO `psst_servingtype`(`name`, `comp`, `price`, `util_id`, `mix_id`) "
                    + "VALUES (?,?,?,?,?)");

            stmt.setString(1, entity.getName());
            stmt.setDouble(2, entity.getComp());
            stmt.setDouble(3, entity.getPrice());
            stmt.setInt(4, entity.getMixId());
            stmt.setInt(5, entity.getMixId());
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
            Logger.getLogger(ServingTypeService.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("list")
    @Transactional
    @Override
    public List<PsstServingtype> findAll() {
        List<PsstServingtype> servList = new ArrayList<>();
        con = connect();
        try {

            Statement stmt = con.createStatement();
            String query = "SELECT `serv_id`, `name`, `comp`, `price`, `util_id`, `mix_id` "
                    + "FROM `psst_servingtype` ORDER BY `name` ASC";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                PsstServingtype serv = new PsstServingtype();
                serv.setServId(rs.getInt(1));
                serv.setName(rs.getString(2));
                serv.setComp(rs.getDouble(3));
                serv.setPrice(rs.getDouble(4));
                serv.setUtilId(rs.getInt(5));
                serv.setMixId(rs.getInt(6));
                servList.add(serv);
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ServingTypeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return servList;
    }

    @PUT
    @Path("/put/{servId}")
    @Transactional
    @Override
    public Response edit(PsstServingtype entity) {
        try {
            con = connect();
            String query = "UPDATE `psst_servingtype` SET `name`= ?,`comp`= ?,`price`= ?,`util_id`= ?,`mix_id`= ? "
                    + "WHERE `serv_id` = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, entity.getName());
            stmt.setDouble(2, entity.getComp());
            stmt.setDouble(3, entity.getPrice());
            stmt.setInt(4, entity.getUtilId());
            stmt.setInt(5, entity.getMixId());
            stmt.setInt(6, entity.getServId());

            int rs = stmt.executeUpdate();
            if (rs != 0) {
                con.close();
                return Response.status(Response.Status.ACCEPTED).build();
            } else {
                con.close();
                return Response.status(Response.Status.EXPECTATION_FAILED).build();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServingTypeService.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/rm/{servId}")
    @Transactional
    @Override
    public Response remove(@PathParam("servId") Integer servId) {
        try {
            con = connect();
            String query = "DELETE FROM `psst_servingtype` WHERE `serv_id` = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, servId);
            int rs = stmt.executeUpdate();
            if (rs != 0) {
                con.close();
                return Response.status(Response.Status.ACCEPTED).build();
            } else {
                con.close();
                return Response.status(Response.Status.EXPECTATION_FAILED).build();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServingTypeService.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/find/id/{servId}")
    @Transactional
    @Override
    public PsstServingtype find(@PathParam("servId") Integer servId) {
        PsstServingtype stock = new PsstServingtype();
        try {
            con = connect();
            String query = "SELECT `serv_id`, `name`, `comp`, `price`, `util_id`, `mix_id` FROM `psst_servingtype` "
                    + "WHERE `serv_id` = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, servId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                stock.setServId(rs.getInt(1));
                stock.setName(rs.getString(2));
                stock.setComp(rs.getDouble(3));
                stock.setPrice(rs.getDouble(4));
                stock.setUtilId(rs.getInt(5));
                stock.setMixId(rs.getInt(6));
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ServingTypeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stock;
    }

    @GET
    @Path("/find/name/{name}")
    @Transactional
    @Override
    public PsstServingtype findByName(@PathParam("name") String name) {
        PsstServingtype stock = new PsstServingtype();
        try {
            con = connect();
            String query = "SELECT `serv_id`, `name`, `comp`, `price`, `util_id`, `mix_id` FROM `psst_servingtype` "
                    + "WHERE `name` LIKE ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, "%" + name + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                stock.setServId(rs.getInt(1));
                stock.setName(rs.getString(2));
                stock.setComp(rs.getDouble(3));
                stock.setPrice(rs.getDouble(4));
                stock.setUtilId(rs.getInt(5));
                stock.setMixId(rs.getInt(6));
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ServingTypeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stock;
    }

}
