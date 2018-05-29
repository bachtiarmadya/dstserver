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
import psst.dst.entity.PsstCoffeestock;
import psst.dst.entity.PsstNoncoffeestock;
import psst.dst.itf.INonCoffeeStockDataService;
import psst.dst.service.EntityBase;

/**
 *
 * @author root
 */
@Path("/noncoffstock")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class NonCoffeeStockService extends EntityBase implements INonCoffeeStockDataService<PsstNoncoffeestock> {

    @POST
    @Path("/create")
    @Transactional
    @Override
    public Response create(PsstNoncoffeestock entity) {
        try {
            con = connect();
            PreparedStatement stmt = con.prepareStatement("INSERT INTO `psst_noncoffeestock`"
                    + "(`name`, `varian`, `type`, `amount`, `unit`) "
                    + "VALUES ( ?, ?, ?, ?, ?)");

            stmt.setString(1, entity.getName());
            stmt.setString(2, entity.getVarian());
            stmt.setString(3, entity.getType());
            stmt.setDouble(4, entity.getAmount());
            stmt.setString(5, entity.getUnit());
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
            Logger.getLogger(NonCoffeeStockService.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("list")
    @Transactional
    @Override
    public List<PsstNoncoffeestock> findAll() {
        List<PsstNoncoffeestock> stockList = new ArrayList<>();
        con = connect();
        try {

            Statement stmt = con.createStatement();
            String query = "SELECT `item_id`, `name`, `varian`, `type`, `amount`, `unit`, `updated_at` "
                    + "FROM `psst_noncoffeestock` ORDER BY `name` ASC";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                PsstNoncoffeestock stock = new PsstNoncoffeestock();
                stock.setItemId(rs.getInt(1));
                stock.setName(rs.getString(2));
                stock.setVarian(rs.getString(3));
                stock.setType(rs.getString(4));
                stock.setAmount(rs.getDouble(5));
                stock.setUnit(rs.getString(6));
                stock.setUpdatedAt(rs.getString(7));

                stockList.add(stock);
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(NonCoffeeStockService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stockList;
    }

    @PUT
    @Path("/put/{itemId}")
    @Transactional
    @Override
    public Response edit(PsstNoncoffeestock entity) {
        try {
            con = connect();
            String query = "UPDATE `psst_noncoffeestock` SET `name`= ?,`varian`= ?,`type`= ?,"
                    + "`amount`= ?,`unit`= ?,`updated_at`= ? WHERE `item_id` = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, entity.getName());
            stmt.setString(2, entity.getVarian());
            stmt.setString(3, entity.getType());
            stmt.setDouble(4, entity.getAmount());
            stmt.setString(5, entity.getUnit());
            stmt.setString(6, entity.getUpdatedAt());
            stmt.setInt(7, entity.getItemId());

            int rs = stmt.executeUpdate();
            if (rs != 0) {
                con.close();
                return Response.status(Response.Status.ACCEPTED).build();
            } else {
                con.close();
                return Response.status(Response.Status.EXPECTATION_FAILED).build();
            }
        } catch (SQLException ex) {
            Logger.getLogger(NonCoffeeStockService.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/rm/{itemId}")
    @Transactional
    @Override
    public Response remove(@PathParam("itemId")Integer itemId) {
        try {
            con = connect();
            String query = "DELETE FROM `psst_noncoffeestock` WHERE `item_id`= ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, itemId);
            int rs = stmt.executeUpdate();
            if (rs != 0) {
                con.close();
                return Response.status(Response.Status.ACCEPTED).build();
            } else {
                con.close();
                return Response.status(Response.Status.EXPECTATION_FAILED).build();
            }
        } catch (SQLException ex) {
            Logger.getLogger(NonCoffeeStockService.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/find/id/{itemId}")
    @Transactional
    @Override
    public PsstNoncoffeestock find(@PathParam("itemId") Integer itemId) {
        PsstNoncoffeestock stock = new PsstNoncoffeestock();
        try {
            con = connect();
            String query = "SELECT `item_id`, `name`, `varian`, `type`, `amount`, `unit`, `updated_at` "
                    + "FROM `psst_noncoffeestock` WHERE `item_id`  = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, itemId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                stock.setItemId(rs.getInt(1));
                stock.setName(rs.getString(2));
                stock.setVarian(rs.getString(3));
                stock.setType(rs.getString(4));
                stock.setAmount(rs.getDouble(5));
                stock.setUnit(rs.getString(6));
                stock.setUpdatedAt(rs.getString(7));
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(NonCoffeeStockService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stock;
    }

    @GET
    @Path("/find/name/{name}")
    @Transactional
    @Override
    public PsstNoncoffeestock findByName(@PathParam("name") String name) {
        PsstNoncoffeestock stock = new PsstNoncoffeestock();
        try {
            con = connect();
            String query = "SELECT `item_id`, `name`, `varian`, `type`, `amount`, `unit`, `updated_at` "
                    + "FROM `psst_noncoffeestock` WHERE `name` LIKE ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, "%" + name + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                stock.setItemId(rs.getInt(1));
                stock.setName(rs.getString(2));
                stock.setVarian(rs.getString(3));
                stock.setType(rs.getString(4));
                stock.setAmount(rs.getDouble(5));
                stock.setUnit(rs.getString(6));
                stock.setUpdatedAt(rs.getString(7));
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(NonCoffeeStockService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stock;
    }

}
