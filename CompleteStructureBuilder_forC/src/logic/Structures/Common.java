/*
 * Copyright (C) 2020 FacuFalcone - CaidevOficial.
 * 
 * Author in C: Santiago Herrera.
 * Adaptation in Java: FacuFalcone - CaidevOficial.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package logic.Structures;

/**
 * @author CaidevOficial
 *
 */
public class Common {

    // Attributes.
    protected int id;
    protected String parameterName; // Size 20
    protected String parameterType; // Size 15

    /**
     * Builds the entity with default params.
     */
    public Common() {
	this.id = 0;
	this.parameterName = "";
	this.parameterType = "";
    }

    /**
     * Builds the entity with 1 param.
     * @param id ID of the entity.
     */
    public Common(int id) {
	if (id > 0) {
	    this.id = id;
	}
    }

    /**
     * Builds the entity with 2 params.
     * @param id ID of the entity.
     * @param pName name of the parameter.
     */
    public Common(int id, String pName) {
	this(id);
	if (pName != null) {
	    this.parameterName = pName;
	}
    }

    /**
     * Builds the entire entity.
     * 
     * @param id ID of the entity.
     * @param pName name of the parameter.
     * @param pType type of the parameter.
     */
    public Common(int id, String pName, String pType) {
	this(id, pName);
	if (pType != null) {
	    this.parameterType = pType;
	}
    }

    /**
     * @return the id
     */
    public int getId() {
	return id;
    }

    /**
     * @return the parameterName
     */
    public String getParameterName() {
	return parameterName;
    }

    /**
     * @return the parameterType
     */
    public String getParameterType() {
	return parameterType;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
	if (id >= 0) {
	    this.id = id;
	}
    }

    /**
     * @param parameterName the parameterName to set
     */
    public void setParameterName(String parameterName) {
	if (parameterName != null) {
	    this.parameterName = parameterName;
	}
    }

    /**
     * @param parameterType the parameterType to set
     */
    public void setParameterType(String parameterType) {
	if (parameterType != null) {
	    this.parameterType = parameterType;
	}
    }
}
