
<%@ page import="com.foo.sky.Clouds" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Clouds List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New Clouds</g:link></span>
        </div>
        <div class="body">
            <h1>Clouds List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="tag" title="Tag" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${cloudsInstanceList}" status="i" var="cloudsInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${cloudsInstance.id}">${fieldValue(bean:cloudsInstance, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:cloudsInstance, field:'tag')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${cloudsInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
