/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psst.dst.service;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import psst.dst.entity.PsstCashier;

/**
 *
 * @author root
 */
@Path("/cashreg")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RegisterCashier {

    public RegisterCashier() {

    }
    
    @POST
    @Path("/doregister")
    @Produces(MediaType.APPLICATION_JSON)
    public String register(PsstCashier entity) {
        String response = "";
        int cash_id = entity.getCashId();
        String name = entity.getName();
        String phone = entity.getPhone();
        String email = entity.getEmail();
        String doc = entity.getDoc();
        String DefImage = "cashier/img/add_photo.png";
        String img = DefImage;
        String pwd = entity.getPwd();
        
        
        int retCode = registerUser(cash_id, name, phone, email, doc,img, pwd);
        if (retCode == 0) {
            response = Utility.constructJSON("register", true);
        } else if (retCode == 1) {
            response = Utility.constructJSON("register", false, "ID is in use");
        } else if (retCode == 2) {
            response = Utility.constructJSON("register", false, "Special characters are not allowed on ID");
        } else if (retCode == 3) {
            response = Utility.constructJSON("register", false, "Error occured");
        }
        return response;
    }

    private int registerUser(Integer cash_id, String name, String phone, String email, String doc,
            String img, String pwd) {
        System.out.println("Inside checkCredentials");
        int result = 3;
        if (Utility.isNotNull(cash_id.toString()) && Utility.isNotNull(pwd)) {
            try {
                if (DBConnection.regCashier(cash_id, name, phone, email, doc,img, pwd)) {
                    System.out.println("Register User if");
                    result = 0;
                }
            } catch (SQLException sqle) {
                System.out.println("Register User catch sqle");
                //When Primary key violation occurs that means user is already registered
                if (sqle.getErrorCode() == 1062) {
                    result = 1;
                } //When special characters are used in name,username or password
                else if (sqle.getErrorCode() == 1064) {
                    System.out.println(sqle.getErrorCode());
                    result = 2;
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                System.out.println("Inside checkCredentials catch e ");
                result = 3;
            }
        } else {
            System.out.println("Inside checkCredentials else");
            result = 3;
        }

        return result;
    }

}
