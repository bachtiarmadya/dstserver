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
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.transaction.annotation.Transactional;
import psst.dst.entity.PsstServingtype;
import psst.dst.entity.PsstTransaction;
import psst.dst.itf.ITransactionDataService;
import psst.dst.service.EntityBase;

/**
 *
 * @author root
 */
@Path("/trx")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TransactionService extends EntityBase implements ITransactionDataService<PsstTransaction> {

    @POST
    @Path("/create")
    @Transactional
    @Override
    public Response create(PsstTransaction entity) {
        try {
            con = connect();
            PreparedStatement stmt = con.prepareStatement("INSERT INTO `psst_transaction` "
                    + "(`order_no`, `cust_name`, `coffee_name`, `serve_name`, `noncoffee_name`, `coffee_qty`, `noncoffee_qty`, `uprice`, `discount`, `tprice`, `cash_id`)"
                    + " VALUES (?,?,?,?,?,?,?,?,?,?,?)");
            stmt.setInt(1, entity.getOrderNo());
            stmt.setString(2, entity.getCustName());
            stmt.setString(3, entity.getCoffeeName());
            stmt.setString(4, entity.getServeName());
            stmt.setString(5, entity.getNoncoffeeName());
            stmt.setInt(6, entity.getCoffeeQty());
            stmt.setInt(7, entity.getNoncoffeeQty());
            stmt.setDouble(8, entity.getUprice());
            stmt.setDouble(9, entity.getDiscount());

            double tPrice, qty, price;
            if (entity.getCoffeeQty() != 0) {
                qty = entity.getCoffeeQty();
                price = entity.getUprice();

                tPrice = price * qty;
                stmt.setDouble(10, tPrice);

            } else if (entity.getNoncoffeeQty() != 0) {
                qty = entity.getNoncoffeeQty();
                price = entity.getUprice();

                tPrice = price * qty;
                stmt.setDouble(10, tPrice);
            }

            stmt.setInt(11, entity.getCashId());
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
            Logger.getLogger(TransactionService.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("list")
    @Transactional
    @Override
    public List<PsstTransaction> findAll() {
        List<PsstTransaction> trxList = new ArrayList<>();
        con = connect();
        try {

            Statement stmt = con.createStatement();
            String query = "SELECT `trx_id`, `order_no`, `cust_name`, `coffee_name`, `serve_name`, "
                    + "`noncoffee_name`, `coffee_qty`, `noncoffee_qty`, `uprice`, `discount`, `tprice`, "
                    + "`created_at`, `cash_id` FROM `psst_transaction` ORDER BY `cust_name` ASC";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                PsstTransaction trx = new PsstTransaction();
                trx.setTrxId(rs.getInt(1));
                trx.setOrderNo(rs.getInt(2));
                trx.setCustName(rs.getString(3));
                trx.setCoffeeName(rs.getString(4));
                trx.setServeName(rs.getString(5));
                trx.setNoncoffeeName(rs.getString(6));
                trx.setCoffeeQty(rs.getInt(7));
                trx.setNoncoffeeQty(rs.getInt(8));
                trx.setUprice(rs.getInt(9));
                trx.setDiscount(rs.getDouble(10));
                trx.setTprice(rs.getDouble(11));
                trx.setCreatedAt(rs.getString(12));
                trx.setCashId(rs.getInt(13));

                trxList.add(trx);
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(TransactionService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return trxList;
    }

    @Override
    public Response edit(PsstTransaction entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @DELETE
    @Path("/rm/{orderNo}")
    @Transactional
    @Override
    public Response remove(@PathParam("orderNo") Integer orderNo) {
        try {
            con = connect();
            String query = "DELETE FROM `psst_transaction` WHERE `order_no` = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, orderNo);
            int rs = stmt.executeUpdate();
            if (rs != 0) {
                con.close();
                return Response.status(Response.Status.ACCEPTED).build();
            } else {
                con.close();
                return Response.status(Response.Status.EXPECTATION_FAILED).build();
            }
        } catch (SQLException ex) {
            Logger.getLogger(TransactionService.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/find/id/{orderNo}")
    @Transactional
    @Override
    public List<PsstTransaction> find(@PathParam("orderNo") Integer orderNo) {
        List<PsstTransaction> trxList = new ArrayList<>();

        try {
            con = connect();
            String query = "SELECT `trx_id`, `order_no`, `cust_name`, `coffee_name`, `serve_name`, `noncoffee_name`, `coffee_qty`, `noncoffee_qty`, `uprice`, `discount`, `tprice`, `created_at`, `cash_id` "
                    + "FROM `psst_transaction` WHERE `order_no` = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, orderNo);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                PsstTransaction trx = new PsstTransaction();
                trx.setTrxId(rs.getInt(1));
                trx.setOrderNo(rs.getInt(2));
                trx.setCustName(rs.getString(3));
                trx.setCoffeeName(rs.getString(4));
                trx.setServeName(rs.getString(5));
                trx.setNoncoffeeName(rs.getString(6));
                trx.setCoffeeQty(rs.getInt(7));
                trx.setNoncoffeeQty(rs.getInt(8));
                trx.setUprice(rs.getInt(9));
                trx.setDiscount(rs.getDouble(10));
                trx.setTprice(rs.getDouble(11));
                trx.setCreatedAt(rs.getString(12));
                trx.setCashId(rs.getInt(13));

                trxList.add(trx);
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ServingTypeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return trxList;
    }

    @GET
    @Path("/find/name/{custName}")
    @Transactional
    @Override
    public List<PsstTransaction> findByName(@PathParam("custName")String custName) {
        List<PsstTransaction> trxList = new ArrayList<>();

        try {
            con = connect();
            String query = "SELECT `trx_id`, `order_no`, `cust_name`, `coffee_name`, `serve_name`, `noncoffee_name`, `coffee_qty`, `noncoffee_qty`, `uprice`, `discount`, `tprice`, `created_at`, `cash_id` "
                    + "FROM `psst_transaction` WHERE `cust_name` LIKE ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, "%" + custName + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                PsstTransaction trx = new PsstTransaction();
                trx.setTrxId(rs.getInt(1));
                trx.setOrderNo(rs.getInt(2));
                trx.setCustName(rs.getString(3));
                trx.setCoffeeName(rs.getString(4));
                trx.setServeName(rs.getString(5));
                trx.setNoncoffeeName(rs.getString(6));
                trx.setCoffeeQty(rs.getInt(7));
                trx.setNoncoffeeQty(rs.getInt(8));
                trx.setUprice(rs.getInt(9));
                trx.setDiscount(rs.getDouble(10));
                trx.setTprice(rs.getDouble(11));
                trx.setCreatedAt(rs.getString(12));
                trx.setCashId(rs.getInt(13));

                trxList.add(trx);
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ServingTypeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return trxList;
    }

}
