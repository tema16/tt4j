/*******************************************************************************
 * Copyright (c) 2009 Richard Eckart de Castilho.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v2.1
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * 
 * Contributors:
 *     Richard Eckart de Castilho - initial API and implementation
 ******************************************************************************/
package org.annolab.tt4j;

import static java.io.File.separator;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility functions.
 *
 * @author Richard Eckart de Castilho
 */
public final
class Util
{
	private
	Util()
	{
		// No instances
	}

	/**
	 * Get the search paths for a model or executable. Using the {@code aSubPath}
	 * argument, executables and models can be searched for in different
	 * locations, e.g. executables in {@literal executable} and models in
	 * {@literal models}.
	 * <br/>
	 * The returned list contains the additional search paths, the value of the
	 * system property {@literal treetagger.home} and the
	 * environment variables {@literal TREETAGGER_HOME} and {@literal TAGDIR}
	 * in this order.
	 *
	 * @param aAdditionalPaths additional paths to search in.
	 * @param aSubPath search in the given sub-directory of the search paths.
	 * @return a list of search paths.
	 */
	public static
	List<String> getSearchPaths(
			final List<String> aAdditionalPaths,
			final String aSubPath)
	{
		final List<String> paths = new ArrayList<String>();
		paths.addAll(aAdditionalPaths);
		if (System.getProperty("treetagger.home") != null) {
			paths.add(System.getProperty("treetagger.home")+separator+aSubPath);
		}
		if (System.getenv("TREETAGGER_HOME") != null) {
			paths.add(System.getenv("TREETAGGER_HOME")+separator+aSubPath);
		}
		if (System.getenv("TAGDIR") != null) {
			paths.add(System.getenv("TAGDIR")+separator+aSubPath);
		}
//		String path = System.getenv("PATH");
//		if (path != null) {
//			paths.addAll(asList(path.split(File.pathSeparator)));
//		}
		return paths;
	}

    /**
     * Join the given strings into a single string separated by the given
     * separator.
     *
     * @param aStrings strings to join.
     * @param aSeparator a separator.
     * @return the joined string.
     */
    public static
	String join(
			final String[] aStrings,
			final String aSeparator)
	{
		final StringBuilder sb = new StringBuilder();

		for (int i = 0; i < aStrings.length; i++) {
			sb.append(aStrings[i]);
			if (i < aStrings.length - 1) {
				sb.append(aSeparator);
			}
		}

		return sb.toString();
	}

    /**
     * Close the given {@link Closeable}.
     *
     * @param aClosable a closable object.
     */
    public static
    void close(
    		final Closeable aClosable)
    {
    	if (aClosable != null) {
	    	try {
	    		aClosable.close();
	    	}
	    	catch (final IOException e) {
	    		// Ignore
	    	}
    	}
    }
}
