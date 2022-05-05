/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projectrecipe.bean;

import com.projectrecipe.db.database;
import com.projectrecipe.model.Recipes;
import com.projectrecipe.model.User;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author HÜSEYİN
 */
@ManagedBean
@SessionScoped
public class dbBean {

    /**
     * @return the recipeSearch
     */
    public List<Recipes> getRecipeSearch() {
        return recipeSearch;
    }

    /**
     * @param recipeSearch the recipeSearch to set
     */
    public void setRecipeSearch(List<Recipes> recipeSearch) {
        this.recipeSearch = recipeSearch;
    }

    /**
     * @return the aranan
     */
    public String getAranan() {
        return aranan;
    }

    /**
     * @param aranan the aranan to set
     */
    public void setAranan(String aranan) {
        this.aranan = aranan;
    }

    /**
     * @return the recipeTrend
     */
    public List<Recipes> getRecipeTrend() {
        return recipeTrend;
    }

    /**
     * @param recipeTrend the recipeTrend to set
     */
    public void setRecipeTrend(List<Recipes> recipeTrend) {
        this.recipeTrend = recipeTrend;
    }

    /**
     * @return the recipeslistCorba
     */
    public List<Recipes> getRecipeslistCorba() {
        return recipeslistCorba;
    }

    /**
     * @param recipeslistCorba the recipeslistCorba to set
     */
    public void setRecipeslistCorba(List<Recipes> recipeslistCorba) {
        this.recipeslistCorba = recipeslistCorba;
    }

    /**
     * @return the recipeslistSalata
     */
    public List<Recipes> getRecipeslistSalata() {
        return recipeslistSalata;
    }

    /**
     * @param recipeslistSalata the recipeslistSalata to set
     */
    public void setRecipeslistSalata(List<Recipes> recipeslistSalata) {
        this.recipeslistSalata = recipeslistSalata;
    }

    /**
     * @return the recipeslistTatli
     */
    public List<Recipes> getRecipeslistTatli() {
        return recipeslistTatli;
    }

    /**
     * @param recipeslistTatli the recipeslistTatli to set
     */
    public void setRecipeslistTatli(List<Recipes> recipeslistTatli) {
        this.recipeslistTatli = recipeslistTatli;
    }

    /**
     * @return the varietyList
     */
    /**
     * @return the recipe
     */
    public Recipes getRecipe() {
        return recipe;
    }

    /**
     * @param recipe the recipe to set
     */
    public void setRecipe(Recipes recipe) {
        this.recipe = recipe;
    }

    /**
     * @return the recipeTariflerim
     */
    public List<Recipes> getRecipeTariflerim() {
        return recipeTariflerim;
    }

    /**
     * @param recipeTariflerim the recipeTariflerim to set
     */
    public void setRecipeTariflerim(List<Recipes> recipeTariflerim) {
        this.recipeTariflerim = recipeTariflerim;
    }

    /**
     * @return the recipelistAnaYemek
     */
    public List<Recipes> getRecipelistAnaYemek() {
        return recipelistAnaYemek;
    }

    /**
     * @param recipelistAnaYemek the recipelistAnaYemek to set
     */
    public void setRecipelistAnaYemek(List<Recipes> recipelistAnaYemek) {
        this.recipelistAnaYemek = recipelistAnaYemek;
    }

    /**
     * @return the recipeListSearch
     */
    public List<Recipes> getRecipeListSearch() {
        return recipeListSearch;
    }

    /**
     * @param recipeListSearch the recipeListSearch to set
     */
    public void setRecipeListSearch(List<Recipes> recipeListSearch) {
        this.recipeListSearch = recipeListSearch;
    }

    /**
     * @return the recipelistZeytinyagi
     */
    public List<Recipes> getRecipelistZeytinyagi() {
        return recipelistZeytinyagi;
    }

    /**
     * @param recipelistZeytinyagi the recipelistZeytinyagi to set
     */
    public void setRecipelistZeytinyagi(List<Recipes> recipelistZeytinyagi) {
        this.recipelistZeytinyagi = recipelistZeytinyagi;
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return the recipelistImages
     */
    public List<Recipes> getRecipelistImages() {
        return recipelistImages;
    }

    /**
     * @param recipelistImages the recipelistImages to set
     */
    public void setRecipelistImages(List<Recipes> recipelistImages) {
        this.recipelistImages = recipelistImages;
    }

    /**
     * @return the girisYapUyeOl
     */
    public boolean isGirisYapUyeOl() {
        return girisYapUyeOl;
    }

    /**
     * @param girisYapUyeOl the girisYapUyeOl to set
     */
    public void setGirisYapUyeOl(boolean girisYapUyeOl) {
        this.girisYapUyeOl = girisYapUyeOl;
    }

    /**
     * @return the visibility
     */
    public boolean isVisibility() {
        return visibility;
    }

    /**
     * @param visibility the visibility to set
     */
    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    /**
     * @return the recipelistHomePage
     */
    public List<Recipes> getRecipelistHomePage() {
        return recipelistHomePage;
    }

    /**
     * @param recipelistHomePage the recipelistHomePage to set
     */
    public void setRecipelistHomePage(List<Recipes> recipelistHomePage) {
        this.recipelistHomePage = recipelistHomePage;
    }

    /**
     * @return the userlist
     */
    public List<User> getUserlist() {
        return userlist;
    }

    /**
     * @param userlist the userlist to set
     */
    public void setUserlist(List<User> userlist) {
        this.userlist = userlist;
    }

    /**
     * @return the recipeslist
     */
    public List<Recipes> getRecipeslist() {
        return recipeslist;
    }

    /**
     * @param recipeslist the recipeslist to set
     */
    public void setRecipeslist(List<Recipes> recipeslist) {
        this.recipeslist = recipeslist;
    }

    /**
     * @return the recipelistName
     */
    public List<Recipes> getRecipelistName() {
        return recipelistName;
    }

    /**
     * @param recipelistName the recipelistName to set
     */
    public void setRecipelistName(List<Recipes> recipelistName) {
        this.recipelistName = recipelistName;
    }

    /**
     * @return the permission
     */
    public String getPermission() {
        return permission;
    }

    /**
     * @param permission the permission to set
     */
    public void setPermission(String permission) {
        this.permission = permission;
    }

    /**
     * @return the userEmail
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * @param userEmail the userEmail to set
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    /**
     * @return the userPassword
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * @param userPassword the userPassword to set
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    /**
     * @return the db
     */
    public database getDb() {
        return db;
    }

    /**
     * @param db the db to set
     */
    public void setDb(database db) {
        this.db = db;
    }
    private String userEmail;
    private String userPassword;
    private String permission;
    private database db = new database();

    static final String DB_URL = "jdbc:derby://localhost:1527/Deneme";
    static final String JDBC_DRIVER = "org.apache.derby.jdbc.ClientDriver";
    static final String USERNAME = "PAP";
    static final String PASSWORD = "PAP";
    private String aranan;
    private boolean visibility = false;
    private boolean girisYapUyeOl = true;
    private User user = new User();
    private List<User> userlist;
    private List<Recipes> recipeslist;
    private List<Recipes> recipelistName;
    private List<Recipes> recipelistHomePage;
    private List<Recipes> recipelistImages;
    private List<Recipes> recipeListSearch;
    private List<Recipes> recipelistZeytinyagi;
    private List<Recipes> recipelistAnaYemek;
    private List<Recipes> recipeslistCorba;
    private List<Recipes> recipeslistSalata;
    private List<Recipes> recipeslistTatli;
    private List<Recipes> recipeTariflerim;
    private List<Recipes> recipeTrend;
    private List<Recipes> recipeSearch;
    private Recipes recipe = new Recipes();

    public dbBean() throws Exception {
        db = db.getRefDatabase();
        recipeListSearch = new ArrayList<>();
    }

    public void recipeListLoader(String aranan) throws Exception {
        logger.info("Tarifler Yukleniyor");
        logger.info("aranan=" + aranan);
        try {
            recipeListSearch = db.getRecipeList();
            if (aranan != null && aranan.trim().length() > 0) {
                //  recipeListSearch=db.;
            } else {
                recipeListSearch = db.getRecipeList();
            }
        } catch (Exception hataMesaji) {
            hataMesaji.printStackTrace();
        } finally {
            // aranan null;
        }
    }
    private static final Logger logger = Logger.getLogger(dbBean.class.getName());

    public String login() {

        try {
            setUser(db.userInformation(userEmail, userPassword));
            if (this.getUser() != null) {
                visibility = true;
                girisYapUyeOl = false;
                return "index?redirect=true";
            } else {
                System.out.println("Yonetici yetkiniz yok veya Kullanici adi ve sifresi hatali" + getUser().getUserName());
                logger.info("Admin Girisi esnasinda bir hata olustu.Lutfen tekrar deneyiniz.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String logout() {
        this.user = null;
       
        visibility = false;
        girisYapUyeOl = true;
        return "index?redirect=true";
    }

    public String kayıtlıMı() {

        if (visibility == true) {
            return "addRecipe?redirect=true";
        } else {
            return "login?redirect=true";
        }
    }

    public String userEdit(int getUserId) {
        logger.info("Kullanicinin Id:" + getUserId);

        try {
            User user = db.getUser(getUserId);
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            Map<String, Object> requestMap = externalContext.getRequestMap();

            requestMap.put("user:", user);

        } catch (Exception ex) {
            logger.log(Level.SEVERE, "ARadiginiz kullanici Bulunamadi:" + ex);
        }
        return "kullanciduzenle.jsf?redirect=true";//xhtml konabilir jsf yerine
    }

    public String userUpdate() {
        try {
            if (user != null) {
                db.getuserUpdate(user);
            } else {
                logger.log(Level.SEVERE, "Kulanici Duzenleme Sirasinda Bir Hata Olustu", user);
                return null;
            }

        } catch (SQLException ex) {
            Logger.getLogger(dbBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "ProfilePage?redirect=true";
    }

    public String userDelete(int getUserId) {

        try {
            db.getUserDelete(getUserId);

        } catch (Exception ex) {
            System.out.println("Kullanici silinirken bir hata olustu:" + getUserId);

        }

        return "kullanicilar.jsf?redirect=true";
    }

    public void recipesListPreRenderView() {

        logger.info("Tarifler Yukleniyor");

        try {
            setRecipeslist(db.getRecipeList());
        } catch (Exception ex) {
            System.out.println("Tarifler yuklenirken bir hata olustu:");
            ex.printStackTrace();
        }

    }

    //Tarif duzenle
    public String recipeEdit(int getrecipeId) {
        logger.info("Tarif Id:" + getrecipeId);

        try {
            Recipes recipe = db.getRecipes(getrecipeId);
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            Map<String, Object> requestMap = externalContext.getRequestMap();

            requestMap.put("Tarifler:", recipe);

        } catch (Exception ex) {
            logger.log(Level.SEVERE, "ARadiginiz tarif Bulunamadi:" + ex);
        }
        return "makaleduzenle.jsf?redirect=true";//xhtml konabilir jsf yerine
    }

    //Tarif  Yukleme
    public String recipeUpdate() {
        try {
            if (recipe != null) {
                db.getRecipesUpdate(recipe);
            } else {
                logger.log(Level.SEVERE, "Tarif Duzenleme Sirasinda Bir Hata Olustu", getUser());
                return null;
            }

        } catch (SQLException ex) {
            Logger.getLogger(dbBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Tariflerim?redirect=true";
    }

    //Tarif Silme
    public String recipeDelete(String name) {
        System.out.println("Tarif Silme Islemi");
        try {
            db.getRecipeDelete( name);
        } catch (Exception ex) {
            System.out.println("Tarif silme islemi sirasinda hata olustu " + recipe);
            ex.printStackTrace();
        }

        return "index?redirect=true";
    }

    //Tarif Ekleme
    public String recipeAdd() {
        try {

            if (recipe != null) {
                db.getRecipeAdd(recipe);
                System.out.println("Tarif Basariyla Eklendi.." + recipe);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Tarif Eklenirken hata olustu" + ex.getMessage() + toString());
        }

        return "index?redirect=true";
    }

    //Kullanici kayit email kontrol burada benim nicknamide kontrol ettirmem lazim...
    public String addUser() throws SQLException {///burada nickname yapmam gerek 

        if (!db.checkEmail(user.geteMail().toString())) {
            db.userAdd(user);
            logger.log(Level.SEVERE, "Kullanici Basariyla Kaydedildi.", getUser());
            return "index?redirect=true";
        } else {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            FacesMessage bilgiMesaji = new FacesMessage("Bu  Mail Adresi Kayitlidir");
            facesContext.addMessage(null, bilgiMesaji);
            logger.log(Level.SEVERE, "Kullanici Zaten Kayitli.", getUser());
        }

        /*    try{
           db.userAdd(user);
           logger.log(Level.SEVERE, "Kullanici Kaydedildi: " ,user);
           return "uyegiris?redirect=true";
       }
       catch(Exception ex){
           ex.printStackTrace();
       }
        ///Kullanici varsa o sayfada kalacagiz
         */
        return null;
    }

    ///Tarif Baslik Listeleme
    public void recipesListPreRenderViewName() {

        logger.info("Tarifler Yukleniyor");

        try {
            recipelistName = db.getRecipeListName();
        } catch (Exception ex) {
            System.out.println("Tarifler yuklenirken bir hata olustu:");
            ex.printStackTrace();
        }

    }

    //AnaSayfada Listeleme
    public void recipesListPreRenderViewHomePage() {

        logger.info("Tarifler Yukleniyor");

        try {
            recipelistHomePage = db.getRecipeList();
        } catch (Exception ex) {
            System.out.println("Tarifler yuklenirken bir hata olustu:");
            ex.printStackTrace();
        }

    }

    //Resimleri Getir
    public void recipesListPreRenderImages() {

        logger.info("Tarifler Yukleniyor");

        try {
            recipelistImages = db.getRecipeImage();
        } catch (Exception ex) {
            System.out.println("Tarifler yuklenirken bir hata olustu:");
            ex.printStackTrace();
        }

    }

    //Tarif Getir
    public String getRecipe(String name) throws SQLException, Exception {
        recipe = db.getRecipe(name);
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        //Disarida listelerken burada saklaniyor bilgiler
        Map<String, Object> map = externalContext.getRequestMap();
        map.put("recipe", recipe);

        return "Recipe?redirect=true";
    }

    public String getTarifim(int id) throws SQLException, Exception {
        logger.info("Tarifler Yukleniyor");
        try {
            recipeTariflerim = db.getTarifim(id);

        } catch (Exception ex) {
            System.out.println("Tarifler Yuklenirken bir hata olustu...");
            ex.printStackTrace();
        }

        return "Tariflerim?redirect=true";
    }

    public String getUser(int id) throws SQLException {
        User user = new User();
        user = db.getUser(id);
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();//Disarida listelerken burada saklaniyor bilgiler
        Map<String, Object> map = externalContext.getRequestMap();
        map.put("User", user);

        return "ProfilPage?redirect=true";
    }

    //Zeytinyaglilari Getir
    public void recipeListPreRenderZeytinyagi() {
        logger.info("Tarifler Yukleniyor");
        try {
            recipelistZeytinyagi = db.getRecipeListZeytinyagli();

        } catch (Exception ex) {
            System.out.println("Tarifler Yuklenirken bir hata olustu...");
            ex.printStackTrace();
        }
    }

    //AnaYemekListesi
    public void recipeListPreRenderAnaYemek() {
        logger.info("Tarifler Yukleniyor");
        try {
            recipelistAnaYemek = db.getRecipeListAnayemek();

        } catch (Exception ex) {
            System.out.println("Tarifler Yuklenirken bir hata olustu...");
            ex.printStackTrace();
        }
    }

    //Salata Listesi
    public void recipeListPreRenderSalata() {
        logger.info("Tarifler Yukleniyor");
        try {
            recipeslistSalata = db.getRecipeListSalata();
        } catch (Exception ex) {
            System.out.println("Tarifler Yuklenirken bir hata olustu...");
            ex.printStackTrace();
        }
    }

    public void recipeListPreRenderTatli() {
        logger.info("Tarifler Yukleniyor");
        try {
            recipeslistTatli = db.getRecipeListTatli();
        } catch (Exception ex) {
            System.out.println("Tarifler Yuklenirken bir hata olustu...");
            ex.printStackTrace();
        }
    }

    public void recipeListPreRenderCorba() {
        logger.info("Tarifler Yukleniyor");
        try {
            recipeslistCorba = db.getRecipeListCorba();
        } catch (Exception ex) {
            System.out.println("Tarifler Yuklenirken bir hata olustu...");
            ex.printStackTrace();
        }
    }

    public void recipeListTrend() {
        logger.info("Tarifler Yukleniyor");
        try {
            recipeTrend = db.getTrend();
        } catch (Exception ex) {
            System.out.println("Tarifler Yuklenirken bir hata olustu...");
            ex.printStackTrace();
        }
    }

    public void recipeSearcLoader() {
        logger.info("Tarifler Yukleniyor.");

        // logger.info("Aranan:"+aranan);
        try {
            recipeListSearch = db.getRecipeList();
            if (aranan != null && aranan.trim().length() > 0) {

                recipeListSearch = db.searchRecipe(aranan);
            } else {

                recipeListSearch = db.getRecipeList();

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            setAranan(null);
        }
    }
    
 
}
