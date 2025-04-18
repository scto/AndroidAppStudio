/*
 *  This file is part of Block IDLE.
 *
 *  Block IDLE is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Block IDLE is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *   along with Block IDLE.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.icst.blockidle.bean;

import java.util.ArrayList;

/** A type of BlockElementBean that inputes value from user and store in it. */
public interface ValueInputBlockElementBean<T> extends BlockElementBean<T> {

	String getKey();

	String getValue();

	DatatypeBean getAcceptedReturnType();

	<K extends BeanMetadata> ArrayList<K> getAllMetadata(Class<K> classType);
}
