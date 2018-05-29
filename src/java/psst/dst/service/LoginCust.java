/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psst.dst.service;

import java.sql.SQLException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.codehaus.jettison.json.JSONException;
import org.springframework.transaction.annotation.Transactional;
import psst.dst.entity.PsstCashier;
import psst.dst.entity.PsstCustomer;

/**
 *
 * @author root
 */
@Path("/custlog")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoginCust {
    public LoginCust() {

    }
    @POST
    @Path("/dologin")
    @Transactional
    public String login(PsstCustomer entity) throws JSONException {
        String response = "";
        String email = entity.getEmail();
        String pwd = entity.getPwd();

        int retCode = checkUser(email, pwd);
        if (retCode == 0) {
            response = Utility.constructJSON("login", true);
        } else if (retCode == 1) {
            response = Utility.constructJSON("login", false, "Wrong email or Password !!");
        }
        return response;
    }

    private int checkUser(String email, String pwd) {
        System.out.println("Inside checkCredentials");
        int result = 1;
        if (Utility.isNotNull(email) && Utility.isNotNull(pwd)) {
            try {
                if (DBConnection.logCust(email, pwd) == 1) {
                    System.out.println("Checking credentials ...");
                    result = 0;
                } else if (DBConnection.logCust(email, pwd) == 2) {
                    result = 1;
                }
            } catch (SQLException sqle) {
                System.out.println("Credentials catch sqle");
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
