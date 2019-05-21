package ar.com.telecom.shiva.presentacion.bean.taglib;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.presentacion.bean.UsuarioSesion;
import ar.com.telecom.shiva.presentacion.bean.dto.menu.MenuDto;
import ar.com.telecom.shiva.presentacion.bean.dto.menu.MenuNivel0Dto;
import ar.com.telecom.shiva.presentacion.bean.dto.menu.MenuNivel1Dto;

public class GeneradorMenu extends TagSupport {

	private static final long serialVersionUID = 1L;
	
	private String titulo;
	
	/**
	 * Cuando el motor JSP encuentre el comienzo de una marca 
	 * implementada por esta clase
	 */
	public int doStartTag() throws JspTagException {
		//Para evaluar el cuerpo de la etiqueta
		return EVAL_BODY_INCLUDE;
	}
	
	/**
	 * Cuando el motor encuentre el final de una marca
	 * implementada por esta clase
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int doEndTag() throws JspException {
		PageContext page = this.pageContext;
		JspWriter out = page.getOut();
		//ServletRequest request = page.getRequest();
		HttpSession sesion = page.getSession();
		
		UsuarioSesion usuarioSesion = (UsuarioSesion) 
				sesion.getAttribute(Constantes.LOGIN);
		
		try {
			out.println("<div class=\"account\">"+titulo+"</div>");
			
			MenuDto menu = usuarioSesion.getMenu();
			
			List list = new LinkedList(menu.getNivel0().entrySet());
			int countNivel0 = 1;
			
			for (Iterator it = list.iterator(); it.hasNext();) {
				MenuNivel0Dto nivel0 = (MenuNivel0Dto) ((Map.Entry) it.next()).getValue();
				
				String menuId = "idMenu_" + countNivel0;
				String menuIdTitulo = nivel0.getDescripcion();
				String accordionId = "accordion-"+countNivel0;
				String collapseId = "collapse_" + countNivel0;
				
				out.println("<div id=\""+accordionId+"\" class=\"accordion-group\">");
				out.println("	<div class=\"accordion-heading mi-menu\">");
				out.println("		<a id=\""+menuId+"\" class=\"accordion-toggle less\""
										+ " onclick=\"javascript:contraer('"+menuId+"')\""
										+ " style=\"padding-top: 4px;padding-bottom: 4px;\">"
										+ "<strong>"+menuIdTitulo+"</strong></a>");
				out.println("		<ul id=\""+collapseId+"\" class=\"collapse in\">");
				
				int countNivel1 = 1;
				List listNivel1 = new LinkedList(nivel0.getNivel1().entrySet());
				for (Iterator it1 = listNivel1.iterator(); it1.hasNext();) {
					MenuNivel1Dto nivel1 = (MenuNivel1Dto) ((Map.Entry) it1.next()).getValue();
					
					String itemId="id_"+countNivel0+"_"+countNivel1;
					String itemTitulo=nivel1.getDescripcion();
					String itemPagina=nivel1.getUrlAcceso();
					out.println("<li class=\"current\"><a id=\""+itemId+"\" class=\"confirmarMenu\" href=\"javascript:viewUrl('"+itemPagina+"');\">"+itemTitulo+"</a></li>");
					out.println("<script>");
					out.println("	$(document).ready(function(){");
					out.println("		$('#"+itemId+"').click(function() {");		
					out.println("			$('#bloqueEspera').trigger('click');");
					out.println("		});");
					out.println("	});");
					out.println("</script>");
					
					countNivel1++;
				}
				
				out.println("		</ul>");
				out.println("	</div>");
				out.println("</div>");
				out.println("<script>");
				out.println("	$(document).ready(function(){");
				out.println("		$(\"#"+menuId+"\").click(function(){");		
				out.println("			$(\"#"+collapseId+"\").slideToggle(\"slow\");");
				out.println("		});");
				out.println("	});");
				out.println("</script>");
				
				countNivel0++;
			}
		} catch (IOException e) {
			throw new JspException(e);
		}
		
		//Para seguir procesando la pagina JSP
		return super.doEndTag();
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
}
