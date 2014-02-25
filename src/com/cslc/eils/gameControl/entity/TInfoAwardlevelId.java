package com.cslc.eils.gameControl.entity;



/**
 * TInfoAwardlevelId entity. @author MyEclipse Persistence Tools
 */

public class TInfoAwardlevelId  implements java.io.Serializable {


    // Fields    

     private Integer gameId;
     private Integer awardLevel;


    // Constructors

    /** default constructor */
    public TInfoAwardlevelId() {
    }

    
    /** full constructor */
    public TInfoAwardlevelId(Integer gameId, Integer awardLevel) {
        this.gameId = gameId;
        this.awardLevel = awardLevel;
    }

   
    // Property accessors

    public Integer getGameId() {
        return this.gameId;
    }
    
    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public Integer getAwardLevel() {
        return this.awardLevel;
    }
    
    public void setAwardLevel(Integer awardLevel) {
        this.awardLevel = awardLevel;
    }
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof TInfoAwardlevelId) ) return false;
		 TInfoAwardlevelId castOther = ( TInfoAwardlevelId ) other; 
         
		 return ( (this.getGameId()==castOther.getGameId()) || ( this.getGameId()!=null && castOther.getGameId()!=null && this.getGameId().equals(castOther.getGameId()) ) )
 && ( (this.getAwardLevel()==castOther.getAwardLevel()) || ( this.getAwardLevel()!=null && castOther.getAwardLevel()!=null && this.getAwardLevel().equals(castOther.getAwardLevel()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getGameId() == null ? 0 : this.getGameId().hashCode() );
         result = 37 * result + ( getAwardLevel() == null ? 0 : this.getAwardLevel().hashCode() );
         return result;
   }   





}