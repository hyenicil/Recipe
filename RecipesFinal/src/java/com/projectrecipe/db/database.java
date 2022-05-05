package com.projectrecipe.db;

import com.projectrecipe.model.Recipes;
import com.projectrecipe.model.User;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class database {

    private static database refDb;

    private DataSource dataSource;

    private String ds = "jdbc:derby://localhost:1527/Deneme";

    public static database getRefDatabase() throws Exception {
        if (refDb == null) {
            refDb = new database();
        }
        return refDb;
    }

    @SuppressWarnings("unused")
    private Connection getDbConnection() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver Bulunamadi");
        }
        try {
            Connection con = DriverManager.getConnection(ds, "PAP", "PAP");
            System.out.println("Baglanti basarili");
            return con;
        } catch (SQLException ex) {
            System.out.println("Baglanti basarisiz");
            return null;
        }

    }

    public User userInformation(String email, String password) {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement psmt = null;

        try {
            con = getDbConnection();

            String sorgu = "SELECT *FROM PAP.\"Users\" where email=? and password=?";
            psmt = con.prepareStatement(sorgu);
            psmt.setString(1, email);
            psmt.setString(2, password);
            rs = psmt.executeQuery();
            //butun kullnaicilari dolasma islemi
            User us = new User();
            if (rs.next()) {
                us = new User();//Yeni  bir nesne olusturulup asagida istenen bilgiler atandi
                us.setUserName(rs.getString("username"));
                us.setSurName(rs.getString("surname"));
                us.seteMail(rs.getString("email"));
                us.setPassword(rs.getString("password"));
                us.setNickName(rs.getString("nickname"));
                us.setPermission(rs.getString("permission"));
                us.setUserId(rs.getInt("id"));

                return us;
            }

        } catch (Exception ex) {
            Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public List<User> getUserlist() throws SQLException {
        List<User> userlist = new ArrayList<User>();
        Connection con = null;
        ResultSet rs = null;
        Statement stmt = null;
        try {
            con = getDbConnection();
            String sorgu = "SELECT *FROM PAP.\"Users\"";

            stmt = con.createStatement();
            rs = stmt.executeQuery(sorgu);

            while (rs.next()) {
                int userid = rs.getInt("id");
                String userName = rs.getString("username");
                String surName = rs.getString("surname");
                String eMail = rs.getString("email");
                String password = rs.getString("password");
                String nickname = rs.getString("nickname");
                String permission = rs.getString("permission");
                User user = new User(userid, userName, surName, eMail, password, nickname);
                userlist.add(user);
            }

            return userlist;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            rs.close();
            stmt.close();
            con.close();
        }

        return null;
    }

    ///Aranan kullnici alip duzenleme metodu
    public User getUser(int getUserId) throws SQLException {

        Connection con = null;
        ResultSet rs = null;
        PreparedStatement psmt = null;
        try {
            con = getDbConnection();
            String sorgu = "SELECT * FROM PAP.\"Users\" where id=?";
            psmt = con.prepareStatement(sorgu);
            rs = psmt.executeQuery();
            User user;
            if (rs.next()) {
                user = new User();
                user.setUserId(rs.getInt("id"));
                user.setUserName(rs.getString("username"));
                user.setSurName(rs.getString("surname"));
                user.seteMail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setNickName(rs.getString("nickname"));
                user.setPermission(rs.getString("permission"));
                return user;
            } else {
                System.out.println("Aradiginiz Kullanici Bulunamadi." + getUserId);
            }

        } catch (Exception ex) {
            Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            rs.close();
            psmt.close();
            con.close();

        }
        return null;
    }

    ///Kullanici  Guncelleme Islemi 
    public void getuserUpdate(User user) throws SQLException {
        Connection con = null;
        PreparedStatement psmt = null;

        try {
            con = getDbConnection();
            String sorgu = "UPDATE PAP.\"Users\" set username=?, surname=? ,  email=? , password=? ,  nickname=?  where id=?";
            psmt = con.prepareStatement(sorgu);
            psmt.setString(1, user.getUserName());
            psmt.setString(2, user.getSurName());
            psmt.setString(3, user.geteMail());
            psmt.setString(4, user.getPassword());
            psmt.setString(5, user.getNickName());
            psmt.setInt(6, user.getUserId());

            psmt.execute();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            psmt.close();
            con.close();

        }

    }

    public void getUserDelete(int getUserId) throws Exception {
        Connection con = null;
        PreparedStatement psmt = null;
        try {
            con = getDbConnection();
            String sorgu = "DELETE  FROM PAP.\"Users\" WHERE ID=?";
            psmt = con.prepareStatement(sorgu);
            psmt.setInt(1, getUserId);
            psmt.execute();
            System.out.println("Silinen Kullanicinin Kimligi:" + getUserId);

        } finally {
            psmt.close();
            con.close();

        }
    }

    //user ekleme 
    public void userAdd(User user) throws SQLException {
        Connection con = null;
        PreparedStatement psmt = null;

        String sorgu = "Insert into PAP.\"Users\" (USERNAME, SURNAME, EMAIL, PASSWORD, NICKNAME, PERMISSION) "
                + " VALUES(?,?,?,?,?,?)";

        try {
            con = getDbConnection();
            psmt = con.prepareStatement(sorgu);
            psmt.setString(1, user.getUserName());
            psmt.setString(2, user.getSurName());
            psmt.setString(3, user.geteMail());
            psmt.setString(4, user.getPassword());
            psmt.setString(5, user.getNickName());
            psmt.setString(6, user.getPermission());
            psmt.execute();

        } catch (SQLException ex) {
            Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
            psmt.close();
            con.close();
        }
    }
    //kayitlimi diye kontrol etme

    public boolean userRegisteredMi(String email) throws SQLException {
        Connection con = null;
        PreparedStatement psmt = null;
        try {
            con = getDbConnection();
            String sorgu = "SELECT EMAIL FROM PAP.\"Users\" WHERE EMAIL=?";
            boolean sonuc;
            psmt = con.prepareStatement(sorgu);
            psmt.setString(1, email);
            sonuc = psmt.execute();
            return sonuc;
        } catch (SQLException ex) {
            Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
            psmt.close();
            con.close();
        }
        return false;
    }

    public boolean checkEmail(String email) {

        List<User> userList = new ArrayList<User>();
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        String sorgu = "SELECT * FROM PAP.\"Users\" WHERE EMAIL=?";
        try {
            con = getDbConnection();
            psmt = con.prepareStatement(sorgu);
            psmt.setString(1, email);
            rs = psmt.executeQuery();

            while (rs.next()) {
                String useremail = rs.getString("email");
                User user = new User(useremail);
                userList.add(user);
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    public List<Recipes> getRecipeList() throws Exception {
        List<Recipes> recipeslist = new ArrayList<Recipes>();
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        try {
            con = getDbConnection();
            String sorgu = "SELECT * FROM PAP.\"Recipes\" ";
            psmt = con.prepareStatement(sorgu);
            rs = psmt.executeQuery();

            while (rs.next()) {

                int recipeId = rs.getInt("recipeId");
                String recipeName = rs.getString("recipeName");
                String chefName = rs.getString("chefName");
                String chefSurname = rs.getString("chefSurname");
                int chefId = rs.getInt("chefId");
                String explanation = rs.getString("explanation");
                String images = rs.getString("images");
                int deliciousCount = rs.getInt("deliciousCount");
                int unsavoryCount = rs.getInt("unsavoryCount");
                String comment = rs.getString("comment");
                String variety = rs.getString("variety");
                String material = rs.getString("material");
                Recipes Simplerecipes = new Recipes(recipeId, recipeName, chefName, chefSurname, chefId, explanation, images, deliciousCount, unsavoryCount, comment, variety, material);

                recipeslist.add(Simplerecipes);

            }
            return recipeslist;

        } finally {
            rs.close();
            psmt.close();
            con.close();

        }

    }

    //Burda kaldim asagisi hic degismedi 
    public Recipes getRecipes(int getRecipeId) throws SQLException, Exception {

        Connection con = null;
        ResultSet rs = null;
        PreparedStatement psmt = null;
        try {
            con = getDbConnection();
            String sorgu = "SELECT * FROM PAP.\"Recipes\"  where RECIPEID=?";
            psmt = con.prepareStatement(sorgu);
            psmt.setInt(1, getRecipeId);
            rs = psmt.executeQuery();
            Recipes recipe = null;
            if (rs.next()) {
                recipe = new Recipes();

                recipe.setRecipeId(rs.getInt("recipeId"));
                recipe.setRecipeName(rs.getString("recipeName"));
                recipe.setChefName(rs.getString("chefName"));
                recipe.setChefSurname(rs.getString("chefSurname"));
                recipe.setChefId(rs.getInt("chefId"));
                recipe.setExplanation(rs.getString("explanation"));
                recipe.setImages(rs.getString("images"));
                recipe.setDeliciousCount(rs.getInt("deliciousCount"));
                recipe.setUnsavoryCount(rs.getInt("unsavoryCount"));
                recipe.setComment(rs.getString("comment"));
                recipe.setVariety(rs.getString("variety"));

            } else {
                System.out.println("Aradiginiz Tarif Bulunamadi." + getRecipeId);
            }
            return recipe;

        } finally {
            rs.close();
            psmt.close();
            con.close();

        }

    }

    //Tarif Guncelleme Islemi 
    public void getRecipesUpdate(Recipes recipe) throws SQLException {
        Connection con = null;
        PreparedStatement psmt = null;

        try {
            con = getDbConnection();
            String sorgu = "UPDATE  PAP.\"Recipes\"  set  chefName=? , chefSurname=? , chefId=? , explanation=? ,  images=? , deliciousCount=?,  unsavoryCount=? , comment=? , variety=?, material=?  where recipeName=?";
            psmt = con.prepareStatement(sorgu);
            psmt.setString(11, recipe.getRecipeName());
            psmt.setString(1, recipe.getChefName());
            psmt.setString(2, recipe.getChefSurname());
            psmt.setInt(3, recipe.getChefId());
            psmt.setString(4, recipe.getExplanation());
            psmt.setString(5, recipe.getImages());
            psmt.setInt(6, recipe.getDeliciousCount());
            psmt.setInt(7, recipe.getUnsavoryCount());
            psmt.setString(8, recipe.getComment());
            psmt.setString(9, recipe.getVariety());
            psmt.setString(10, recipe.getMaterial());

            psmt.execute();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            psmt.close();
            con.close();

        }

    }

    public void getRecipeDelete(String name) throws SQLException, Exception {
        Connection con = null;
        PreparedStatement psmt = null;
        try {
            con = getDbConnection();
            String sorgu = "DELETE FROM PAP.\"Recipes\"  WHERE RECIPENAME=?";
            psmt = con.prepareStatement(sorgu);
            psmt.setString(1, name);
            psmt.execute();

        } finally {
            psmt.close();
            con.close();
        }
    }

   
    public void getRecipeAdd(Recipes recipe) throws Exception {
        Connection con = null;
        PreparedStatement psmt = null;
        try {
            con = getDbConnection();
            String sorgu = "INSERT INTO PAP.\"Recipes\"  (recipeName, chefName,chefSurname, chefId, explanation, images, deliciousCount, unsavoryCount, comment,variety, material ) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
            psmt = con.prepareStatement(sorgu);
            psmt.setString(1, recipe.getRecipeName());
            psmt.setString(2, recipe.getChefName());
            psmt.setString(3, recipe.getChefSurname());
            psmt.setInt(4, recipe.getChefId());
            psmt.setString(5, recipe.getExplanation());
            psmt.setString(6, recipe.getImages());
            psmt.setInt(7, recipe.getDeliciousCount());
            psmt.setInt(8, recipe.getUnsavoryCount());
            psmt.setString(9, recipe.getComment());
            psmt.setString(10, recipe.getVariety());
            psmt.setString(11, recipe.getMaterial());

            psmt.execute();

        } finally {
            con.close();
            psmt.close();
        }

    }

    //Murat ile yaptigimiz
    public boolean insertUser(String username, String surname, String email, String password, String gender, String nickname, String permission) throws SQLException {
        Connection con = null;
        PreparedStatement psmt = null;
        String sorgu = "Insert into Users (USERNAME, SURNAME, EMAIL, PASSWORD, GENDER, NICKNAME, PERMISSION) "
                + " VALUES(?,?,?,?,?,?,?)";

        try {
            con = getDbConnection();
            psmt = con.prepareStatement(sorgu);
            psmt.setString(1, username);
            psmt.setString(2, surname);
            psmt.setString(3, email);
            psmt.setString(4, password);
            psmt.setString(5, gender);
            psmt.setString(6, nickname);
            psmt.setString(7, permission);

            psmt.executeUpdate();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            con.close();
            psmt.close();

        }

    }

    //Tarif Baslik Listeleme
    public List<Recipes> getRecipeListName() throws Exception {
        List<Recipes> recipeslist = new ArrayList<Recipes>();
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        try {
            con = getDbConnection();
            String sorgu = "SELECT * FROM PAP.\"Recipes\"";
            psmt = con.prepareStatement(sorgu);
            rs = psmt.executeQuery();

            while (rs.next()) {

                int recipeId = rs.getInt("recipeId");
                String recipeName = rs.getString("recipeName");
                String chefName = rs.getString("chefName");
                String chefSurname = rs.getString("chefSurname");
                int chefId = rs.getInt("chefId");
                String explanation = rs.getString("explanation");
                String images = rs.getString("images");
                int deliciousCount = rs.getInt("deliciousCount");
                int unsavoryCount = rs.getInt("unsavoryCount");
                String comment = rs.getString("comment");
                String variety = rs.getString("variety");
                String material = rs.getString("material");

                Recipes Simplerecipes = new Recipes(recipeId, recipeName, chefName, chefSurname, chefId, explanation, images, deliciousCount, unsavoryCount, comment, variety, material);

                recipeslist.add(Simplerecipes);

            }
            return recipeslist;

        } finally {
            rs.close();
            psmt.close();
            con.close();

        }

    }

    //Tarif Listeleme
    public List<Recipes> getRecipeListHomePage() throws Exception {
        List<Recipes> recipeslist = new ArrayList<Recipes>();
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        try {
            con = getDbConnection();
            String sorgu = "SELECT * FROM PAP.\"Recipes\" ";
            psmt = con.prepareStatement(sorgu);
            rs = psmt.executeQuery();

            while (rs.next()) {

                int recipeId = rs.getInt("recipeId");
                String recipeName = rs.getString("recipeName");
                String chefName = rs.getString("chefName");
                String chefSurname = rs.getString("chefSurname");
                int chefId = rs.getInt("chefId");
                String explanation = rs.getString("explanation");
                String images = rs.getString("images");
                int deliciousCount = rs.getInt("deliciousCount");
                int unsavoryCount = rs.getInt("unsavoryCount");
                String comment = rs.getString("comment");
                String variety = rs.getString("variety");
                String material = rs.getString("material");

                Recipes Simplerecipes = new Recipes(recipeId, recipeName, chefName, chefSurname, chefId, explanation, images, deliciousCount, unsavoryCount, comment, variety, material);

                recipeslist.add(Simplerecipes);

            }
            return recipeslist;

        } finally {
            rs.close();
            psmt.close();
            con.close();

        }

    }

    public List<Recipes> getRecipeImage() throws Exception {
        List<Recipes> recipeslistImage = new ArrayList<Recipes>();
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        try {
            con = getDbConnection();
            String sorgu = "SELECT * FROM PAP.\"Recipes\" ";
            psmt = con.prepareStatement(sorgu);
            rs = psmt.executeQuery();

            while (rs.next()) {

                int recipeId = rs.getInt("recipeId");
                String recipeName = rs.getString("recipeName");
                String chefName = rs.getString("chefName");
                String chefSurname = rs.getString("chefSurname");
                int chefId = rs.getInt("chefId");
                String explanation = rs.getString("explanation");
                String images = rs.getString("images");
                int deliciousCount = rs.getInt("deliciousCount");
                int unsavoryCount = rs.getInt("unsavoryCount");
                String comment = rs.getString("comment");
                String variety = rs.getString("variety");
                String material = rs.getString("material");
                Recipes Simplerecipes = new Recipes(recipeId, recipeName, chefName, chefSurname, chefId, explanation, images, deliciousCount, unsavoryCount, comment, variety, material);

                recipeslistImage.add(Simplerecipes);

            }
            return recipeslistImage;

        } finally {
            rs.close();
            psmt.close();
            con.close();

        }

    }

    public List<Recipes> getRecipeListZeytinyagli() throws Exception {
        List<Recipes> recipeslist = new ArrayList<Recipes>();
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        try {
            con = getDbConnection();
            String sorgu = "SELECT * FROM PAP.\"Recipes\"  WHERE VARIETY='Zeytinyağlı' ";
            psmt = con.prepareStatement(sorgu);
            rs = psmt.executeQuery();

            while (rs.next()) {

                int recipeId = rs.getInt("recipeId");
                String recipeName = rs.getString("recipeName");
                String chefName = rs.getString("chefName");
                String chefSurname = rs.getString("chefSurname");
                int chefId = rs.getInt("chefId");
                String explanation = rs.getString("explanation");
                String images = rs.getString("images");
                int deliciousCount = rs.getInt("deliciousCount");
                int unsavoryCount = rs.getInt("unsavoryCount");
                String comment = rs.getString("comment");
                String variety = rs.getString("variety");
                String material = rs.getString("material");

                Recipes Simplerecipes = new Recipes(recipeId, recipeName, chefName, chefSurname, chefId, explanation, images, deliciousCount, unsavoryCount, comment, variety, material);

                recipeslist.add(Simplerecipes);

            }
            return recipeslist;

        } finally {
            rs.close();
            psmt.close();
            con.close();

        }

    }

    ///et, corba ,salata, vs olmasi gerekiyor
    public List<Recipes> getRecipeListAnayemek() throws Exception {
        List<Recipes> recipeslist = new ArrayList<Recipes>();
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        try {
            con = getDbConnection();
            String sorgu = "SELECT * FROM PAP.\"Recipes\"  WHERE VARIETY='Ana Yemek' ";
            psmt = con.prepareStatement(sorgu);
            rs = psmt.executeQuery();

            while (rs.next()) {

                int recipeId = rs.getInt("recipeId");
                String recipeName = rs.getString("recipeName");
                String chefName = rs.getString("chefName");
                String chefSurname = rs.getString("chefSurname");
                int chefId = rs.getInt("chefId");
                String explanation = rs.getString("explanation");
                String images = rs.getString("images");
                int deliciousCount = rs.getInt("deliciousCount");
                int unsavoryCount = rs.getInt("unsavoryCount");
                String comment = rs.getString("comment");
                String variety = rs.getString("variety");
                String material = rs.getString("material");

                Recipes Simplerecipes = new Recipes(recipeId, recipeName, chefName, chefSurname, chefId, explanation, images, deliciousCount, unsavoryCount, comment, variety, material);

                recipeslist.add(Simplerecipes);

            }
            return recipeslist;

        } finally {
            rs.close();
            psmt.close();
            con.close();

        }

    }

    public List<Recipes> getRecipeListCorba() throws Exception {
        List<Recipes> recipeslist = new ArrayList<Recipes>();
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        try {
            con = getDbConnection();
            String sorgu = "SELECT * FROM PAP.\"Recipes\"  WHERE VARIETY='Çorba' ";
            psmt = con.prepareStatement(sorgu);
            rs = psmt.executeQuery();

            while (rs.next()) {

                int recipeId = rs.getInt("recipeId");
                String recipeName = rs.getString("recipeName");
                String chefName = rs.getString("chefName");
                String chefSurname = rs.getString("chefSurname");
                int chefId = rs.getInt("chefId");
                String explanation = rs.getString("explanation");
                String images = rs.getString("images");
                int deliciousCount = rs.getInt("deliciousCount");
                int unsavoryCount = rs.getInt("unsavoryCount");
                String comment = rs.getString("comment");
                String variety = rs.getString("variety");
                String material = rs.getString("material");

                Recipes Simplerecipes = new Recipes(recipeId, recipeName, chefName, chefSurname, chefId, explanation, images, deliciousCount, unsavoryCount, comment, variety, material);

                recipeslist.add(Simplerecipes);

            }
            return recipeslist;

        } finally {
            rs.close();
            psmt.close();
            con.close();

        }

    }

    public List<Recipes> getRecipeListSalata() throws Exception {
        List<Recipes> recipeslist = new ArrayList<Recipes>();
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        try {
            con = getDbConnection();
            String sorgu = "SELECT * FROM PAP.\"Recipes\"  WHERE VARIETY='Salata' ";
            psmt = con.prepareStatement(sorgu);
            rs = psmt.executeQuery();

            while (rs.next()) {

                int recipeId = rs.getInt("recipeId");
                String recipeName = rs.getString("recipeName");
                String chefName = rs.getString("chefName");
                String chefSurname = rs.getString("chefSurname");
                int chefId = rs.getInt("chefId");
                String explanation = rs.getString("explanation");
                String images = rs.getString("images");
                int deliciousCount = rs.getInt("deliciousCount");
                int unsavoryCount = rs.getInt("unsavoryCount");
                String comment = rs.getString("comment");
                String variety = rs.getString("variety");
                String material = rs.getString("material");

                Recipes Simplerecipes = new Recipes(recipeId, recipeName, chefName, chefSurname, chefId, explanation, images, deliciousCount, unsavoryCount, comment, variety, material);

                recipeslist.add(Simplerecipes);

            }
            return recipeslist;

        } finally {
            rs.close();
            psmt.close();
            con.close();

        }

    }

    public List<Recipes> getRecipeListTatli() throws Exception {
        List<Recipes> recipeslist = new ArrayList<Recipes>();
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        try {
            con = getDbConnection();
            String sorgu = "SELECT * FROM PAP.\"Recipes\"  WHERE VARIETY='Tatlı'";
            psmt = con.prepareStatement(sorgu);
            rs = psmt.executeQuery();

            while (rs.next()) {

                int recipeId = rs.getInt("recipeId");
                String recipeName = rs.getString("recipeName");
                String chefName = rs.getString("chefName");
                String chefSurname = rs.getString("chefSurname");
                int chefId = rs.getInt("chefId");
                String explanation = rs.getString("explanation");
                String images = rs.getString("images");
                int deliciousCount = rs.getInt("deliciousCount");
                int unsavoryCount = rs.getInt("unsavoryCount");
                String comment = rs.getString("comment");
                String variety = rs.getString("variety");
                String material = rs.getString("material");

                Recipes Simplerecipes = new Recipes(recipeId, recipeName, chefName, chefSurname, chefId, explanation, images, deliciousCount, unsavoryCount, comment, variety, material);

                recipeslist.add(Simplerecipes);

            }
            return recipeslist;

        } finally {
            rs.close();
            psmt.close();
            con.close();

        }

    }

    public Recipes getRecipe(String name) {
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        Recipes simplerecipe = new Recipes();

        String sorgu = "SELECT * FROM PAP.\"Recipes\" WHERE RECIPENAME = ?";
        try {
            con = getDbConnection();
            psmt = con.prepareStatement(sorgu);
            psmt.setString(1, name);
            rs = psmt.executeQuery();

            while (rs.next()) {
                simplerecipe.setRecipeId(rs.getInt("recipeId"));
                simplerecipe.setRecipeName(rs.getString("recipeName"));
                simplerecipe.setChefName(rs.getString("chefName"));
                simplerecipe.setChefSurname(rs.getString("chefSurname"));
                simplerecipe.setChefId(rs.getInt("chefId"));
                simplerecipe.setExplanation(rs.getString("explanation"));
                simplerecipe.setImages(rs.getString("images"));
                simplerecipe.setDeliciousCount(rs.getInt("deliciousCount"));
                simplerecipe.setUnsavoryCount(rs.getInt("unsavoryCount"));
                simplerecipe.setComment(rs.getString("comment"));
                simplerecipe.setVariety(rs.getString("variety"));
                simplerecipe.setMaterial(rs.getString("material"));
            }
            return simplerecipe;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }

    }

    public List<Recipes> getTarifim(int id) throws SQLException, Exception {
        List<Recipes> recipeslist = new ArrayList<Recipes>();
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        try {
            con = getDbConnection();
            String sorgu = "SELECT * FROM PAP.\"Recipes\"  WHERE chefId=? ";
            psmt = con.prepareStatement(sorgu);
            psmt.setInt(1, id);
            rs = psmt.executeQuery();

            while (rs.next()) {

                int recipeId = rs.getInt("recipeId");
                String recipeName = rs.getString("recipeName");
                String chefName = rs.getString("chefName");
                String chefSurname = rs.getString("chefSurname");
                int chefId = rs.getInt("chefId");
                String explanation = rs.getString("explanation");
                String images = rs.getString("images");
                int deliciousCount = rs.getInt("deliciousCount");
                int unsavoryCount = rs.getInt("unsavoryCount");
                String comment = rs.getString("comment");
                String variety = rs.getString("variety");
                String material = rs.getString("material");

                Recipes Simplerecipes = new Recipes(recipeId, recipeName, chefName, chefSurname, chefId, explanation, images, deliciousCount, unsavoryCount, comment, variety, material);

                recipeslist.add(Simplerecipes);

            }
            return recipeslist;

        } finally {
            rs.close();
            psmt.close();
            con.close();

        }

    }

    public List<Recipes> getTrend() throws Exception {
        List<Recipes> recipeslist = new ArrayList<Recipes>();
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        try {
            con = getDbConnection();
            String sorgu = "SELECT * FROM PAP.\"Recipes\"  WHERE DELICIOUSCOUNT>350 ";
            psmt = con.prepareStatement(sorgu);
            rs = psmt.executeQuery();

            while (rs.next()) {

                int recipeId = rs.getInt("recipeId");
                String recipeName = rs.getString("recipeName");
                String chefName = rs.getString("chefName");
                String chefSurname = rs.getString("chefSurname");
                int chefId = rs.getInt("chefId");
                String explanation = rs.getString("explanation");
                String images = rs.getString("images");
                int deliciousCount = rs.getInt("deliciousCount");
                int unsavoryCount = rs.getInt("unsavoryCount");
                String comment = rs.getString("comment");
                String variety = rs.getString("variety");
                String material = rs.getString("material");

                Recipes Simplerecipes = new Recipes(recipeId, recipeName, chefName, chefSurname, chefId, explanation, images, deliciousCount, unsavoryCount, comment, variety, material);

                recipeslist.add(Simplerecipes);

            }
            return recipeslist;

        } finally {
            rs.close();
            psmt.close();
            con.close();

        }

    }

    public List<Recipes> searchRecipe(String aranan) throws SQLException {
        List<Recipes> recipeSearch = new ArrayList<Recipes>();
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        try {
            con = getDbConnection();
            if (aranan != null && aranan.trim().length() > 0) {
                String sorgu = "SELECT * FROM PAP.\"Recipes\" WHERE LOWER(MATERIAL) LIKE? OR LOWER(RECIPENAME) LIKE?";

                psmt = con.prepareStatement(sorgu);
                String arananBenzerler = "%" + aranan.toLowerCase() + "%";
                psmt.setString(1, arananBenzerler);
                psmt.setString(2, arananBenzerler);
            } else {
                String sorgu2 = "SELECT * FROM PAP.\"Recipes\" ORDER BY RECIPENAME";

                psmt = con.prepareStatement(sorgu2);
            }

            rs = psmt.executeQuery();
            while (rs.next()) {

                int recipeId = rs.getInt("recipeId");
                String recipeName = rs.getString("recipeName");
                String chefName = rs.getString("chefName");
                String chefSurname = rs.getString("chefSurname");
                int chefId = rs.getInt("chefId");
                String explanation = rs.getString("explanation");
                String images = rs.getString("images");
                int deliciousCount = rs.getInt("deliciousCount");
                int unsavoryCount = rs.getInt("unsavoryCount");
                String comment = rs.getString("comment");
                String variety = rs.getString("variety");
                String material = rs.getString("material");

                Recipes Simplerecipes = new Recipes(recipeId, recipeName, chefName, chefSurname, chefId, explanation, images, deliciousCount, unsavoryCount, comment, variety, material);

                recipeSearch.add(Simplerecipes);
            }
            return recipeSearch;
        } finally {
            con.close();
            psmt.close();
            rs.close();
        }

    }

}
