/*
 * $Header: /home/cvs/jakarta-struts/contrib/struts-faces/src/java/org/apache/struts/faces/taglib/HtmlTag.java,v 1.1 2003/03/07 03:22:44 craigmcc Exp $
 * $Revision: 1.1 $
 * $Date: 2003/03/07 03:22:44 $
 *
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 2002-2003 The Apache Software Foundation.  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution, if
 *    any, must include the following acknowlegement:
 *       "This product includes software developed by the
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowlegement may appear in the software itself,
 *    if and wherever such third-party acknowlegements normally appear.
 *
 * 4. The names "The Jakarta Project", "Struts", and "Apache Software
 *    Foundation" must not be used to endorse or promote products derived
 *    from this software without prior written permission. For written
 *    permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache"
 *    nor may "Apache" appear in their names without prior written
 *    permission of the Apache Group.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */

package org.apache.struts.faces.taglib;


import javax.faces.component.UIComponent;
import javax.faces.component.UIOutput;
import javax.faces.webapp.FacesTag;
import javax.servlet.jsp.JspException;
import org.apache.struts.Globals;


/**
 * <p>Render an HTML <code>&lt;html&gt;</code> element for
 * the <em>Struts-Faces Integration Library</em>.</p>
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.1 $ $Date: 2003/03/07 03:22:44 $
 */

public class HtmlTag extends AbstractFacesTag {


    // --------------------------------------------------------- Tag Attributes


    /**
     * <p>Set a locale if not set yet.</p>
     */
    private boolean locale = false;

    public void setLocale(boolean locale) {
        this.locale = locale;
    }


    /**
     * <p>Render Struts HTML tags as xhtml.</p>
     */
    private boolean xhtml = false;

    public void setXhtml(boolean xhtml) {
        this.xhtml = xhtml;
    }


    // --------------------------------------------------------- Public Methods


    /**
     * <p>Create and return a new component to be associated with this tag.</p>
     */
    public UIComponent createComponent() {

        return (new UIOutput());

    }


    /**
     * <p>Override <code>doStartTag()</code> method to also set a page
     * context attribute if <code>xhtml</code> is <code>>true</code>.</p>
     */
    public int doStartTag() throws JspException {

        int result = super.doStartTag();
        if (xhtml) {
            pageContext.setAttribute(Globals.XHTML_KEY, "true",
                                     this.pageContext.PAGE_SCOPE);
        }
        return (result);

    }


    /**
     * <p>Return the <code>rendererType</code> to be used for rendering
     * our component.</p>
     */
    public String getRendererType() {

        return ("StrutsHtml");

    }


    /**
     * <p>Release resources allocated to this tag instance.</p>
     */
    public void release() {

        super.release();
        this.locale = false;
        this.xhtml = false;

    }


    // ------------------------------------------------------ Protected Methods


    /**
     * <p>Override attributes set on this tag instance.</p>
     *
     * @param component Component whose attributes should be overridden
     */
    protected void overrideProperties(UIComponent component) {

        super.overrideProperties(component);
        if (component.getAttribute("locale") == null) {
            component.setAttribute("locale", new Boolean(locale));
        }
        if (component.getAttribute("xhtml") == null) {
            component.setAttribute("xhtml", new Boolean(xhtml));
        }

    }


}
