/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projectrecipe.model;

import java.sql.Blob;

/**
 *
 * @author HÜSEYİN
 */
public class Recipes {

    /**
     * @return the material
     */
    public String getMaterial() {
        return material;
    }

    /**
     * @param material the material to set
     */
    public void setMaterial(String material) {
        this.material = material;
    }

    /**
     * @return the images
     */
    public String getImages() {
        return images;
    }

    /**
     * @param images the images to set
     */
    public void setImages(String images) {
        this.images = images;
    }

    /**
     * @return the deliciousCount
     */
    public int getDeliciousCount() {
        return deliciousCount;
    }

    /**
     * @param deliciousCount the deliciousCount to set
     */
    public void setDeliciousCount(int deliciousCount) {
        this.deliciousCount = deliciousCount;
    }

    /**
     * @return the unsavoryCount
     */
    public int getUnsavoryCount() {
        return unsavoryCount;
    }

    /**
     * @param unsavoryCount the unsavoryCount to set
     */
    public void setUnsavoryCount(int unsavoryCount) {
        this.unsavoryCount = unsavoryCount;
    }

  

 
    /**
     * @return the recipeId
     */
    public int getRecipeId() {
        return recipeId;
    }

    /**
     * @param recipeId the recipeId to set
     */
    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    /**
     * @return the recipeName
     */
    public String getRecipeName() {
        return recipeName;
    }

    /**
     * @param recipeName the recipeName to set
     */
    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    /**
     * @return the chefName
     */
    public String getChefName() {
        return chefName;
    }

    /**
     * @param chefName the chefName to set
     */
    public void setChefName(String chefName) {
        this.chefName = chefName;
    }

    /**
     * @return the chefSurname
     */
    public String getChefSurname() {
        return chefSurname;
    }

    /**
     * @param chefSurname the chefSurname to set
     */
    public void setChefSurname(String chefSurname) {
        this.chefSurname = chefSurname;
    }

    /**
     * @return the chefId
     */
    public int getChefId() {
        return chefId;
    }

    /**
     * @param chefId the chefId to set
     */
    public void setChefId(int chefId) {
        this.chefId = chefId;
    }

    /**
     * @return the explanation
     */
    public String getExplanation() {
        return explanation;
    }

    /**
     * @param explanation the explanation to set
     */
    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    /**
     * @return the deliciousCount
     */
 
    /**
     * @return the unsavoryCount
     */
   

    /**
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment the comment to set
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * @return the variety
     */
    public String getVariety() {
        return variety;
    }

    /**
     * @param variety the variety to set
     */
    public void setVariety(String variety) {
        this.variety = variety;
  }
    
    private int recipeId;
    private String recipeName;
    private String chefName;
    private String chefSurname;
    private int chefId;
    private String explanation;
    private int deliciousCount;
    private int unsavoryCount;
    private String comment;
    private String variety;
    private String images;
    private String material;

    public void setdchefId(int id){
        this.chefId=id;
    }
    public void comment(){
        this.comment="Henüz yorum yapılmamıstır.";
    }
    
    
    @Override
    public String toString() {
        return "Recipes{" + "recipeId=" + recipeId + ", recipeName=" + recipeName + ", chefName=" + chefName + ", chefSurname=" + chefSurname + ", chefId=" + chefId + ", explanation=" + explanation + ", deliciousCount=" + deliciousCount + ", unsavoryCount=" + unsavoryCount + ", comment=" + comment + ", variety=" + variety + ", images=" + images + ", material="+ material +'}';
    }

    public Recipes(int recipeId, String recipeName, String chefName, String chefSurname, int chefId, String explanation, String images, int deliciousCount, int unsavoryCount, String comment, String variety,String material) {
        this.recipeId = recipeId;
        this.recipeName = recipeName;
        this.chefName = chefName;
        this.chefSurname = chefSurname;
        this.chefId = chefId;
        this.explanation = explanation;
        this.deliciousCount = deliciousCount;
        this.unsavoryCount = unsavoryCount;
        this.comment = comment;
        this.variety = variety;
        this.images = images;
        this.material=material;
    }

  
   

    public Recipes() {
        super();
    }
    
    
    

     
    
}
