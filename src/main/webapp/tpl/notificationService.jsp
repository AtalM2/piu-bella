<%@ page import="java.util.Collection"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="fr.univnantes.atal.web.trashnao.model.Notification"%>

<% 
Collection<Notification> notifications = (Collection<Notification>) request.getAttribute("mesAlertes"); 

if(notifications.size() == 0) {
%>
	<div class="well">
		Vous n'avez rien.
	</div>
<%
}

Iterator<Notification> iter = notifications.iterator();
while( iter.hasNext() ) {
	Notification item=iter.next();
	String street = item.getAddress().getStreet();
%>
    <jsp:include page="notifications-options.jsp" flush="true">
        <jsp:param name="alertaddr" value="<%= street %>" />
        <jsp:param name="id" value="<%= (int)(Math.random()*10000)%>" />
    </jsp:include>
<%
}
%>