package ar.com.telecom.shiva.persistencia.util;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.hibernate.type.Type;

import ar.com.telecom.shiva.base.utils.Validaciones;

public class UtilidadCriteria {
	
	/**
	 * METHODS FOR THE CONSTRUCTION OF THE DetachedCriteria
	 */
	/** 
	 * This method adds a less than or equal operation to criteria.
     * @param c
     * @param field
     * @param value
     */
    protected void addRestrictionLessThanOrEquals(DetachedCriteria c, String field, Object value) {
        if (!Validaciones.isObjectNull(value)) {
            c.add(Restrictions.le(field, value));
        }
    }
    
    /** This method adds a greater than or equal operation to criteria.
     * @param c
     * @param field
     * @param value
     */
    protected void addRestrictionGreatherThanOrEquals(DetachedCriteria criteria, String field, Object value) {
        if (!Validaciones.isObjectNull(value)) {
        	criteria.add(Restrictions.ge(field, value));
        }
    }
    
	/**
	 * @param criteria
	 * @param field
	 * @param value
	 */
    protected void addRestrictionEquals(DetachedCriteria criteria,String field,String value){
		if(!Validaciones.isNullOrEmpty(value)){
			criteria.add(Restrictions.eq(field, value));
		}
	}
    
    /**
     * 
     * @param criteria
     * @param field
     * @param value
     */
    protected void addRestrictionLike(DetachedCriteria criteria, String field, String value) {
    	if(!Validaciones.isNullOrEmpty(value)){
    		criteria.add(Restrictions.ilike(field,value));
    	}
    }
	
	/**
	 * @param criteria
	 * @param field
	 * @param value
	 */
    protected void addRestrictionEquals(DetachedCriteria criteria,String field,Object value){
		if(!Validaciones.isObjectNull(value)){
			criteria.add(Restrictions.eq(field, value));
		}
	}

	/**
	 * @param criteria
	 * @param field
	 * @param value
	 */
    protected void addRestrictionNotEquals(DetachedCriteria criteria,String field,Object value){
		if(!Validaciones.isObjectNull(value)){
			criteria.add(Restrictions.ne(field, value));
		}
	}
	
	/**
	 * @param criteria
	 * @param field
	 * @param value
	 */
    protected void addRestrictionGreatherThanOrEquals(DetachedCriteria criteria,String field,String value){
		if(!Validaciones.isNullOrEmpty(value)){
			criteria.add(Restrictions.ge(field, value));
		}
	}	
	/**
	 * @param criteria
	 * @param field
	 * @param value
	 */
    protected void addRestrictionLessThanOrEquals(DetachedCriteria criteria,String field,String value){
		if(!Validaciones.isNullOrEmpty(value)){
			criteria.add(Restrictions.le(field, value));
		}
	}
	/**
	 * @param criteria
	 * @param sqlRestriction
	 * @param field
	 * @param type
	 * @param value
	 */
    protected void addSQLRestriction(DetachedCriteria criteria,String sqlRestriction,String field,Type type,String value){
		if(!Validaciones.isNullOrEmpty(value)){
			criteria.add(Restrictions.sqlRestriction(sqlRestriction,value, type));
		}
	}
    /**
     * @param criteria
     * @param sqlRestriction
     * @param field
     * @param type
     * @param value
     */
    protected void addSQLRestriction(DetachedCriteria criteria,String sqlRestriction,String field,Type type,Object value){
    	if(value!=null){
			criteria.add(Restrictions.sqlRestriction(sqlRestriction,value, type));    		
    	}
    }
    
    /**
     * Add a subquery to be equal to the specified field.
     * @param mainCriteria
     * @param subCriteria
     * @param field
     */
    protected void addSubquery(DetachedCriteria mainCriteria, DetachedCriteria subCriteria,String field){
    	mainCriteria.add(Subqueries.propertyEq(field,subCriteria));
    }
}
